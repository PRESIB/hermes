package pt.nfriacowboy.presib.hermes.services

import com.natpryce.konfig.Key
import com.natpryce.konfig.stringType
import org.eclipse.paho.mqttv5.client.IMqttToken
import org.eclipse.paho.mqttv5.client.MqttAsyncClient
import org.eclipse.paho.mqttv5.client.MqttCallback
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence
import org.eclipse.paho.mqttv5.common.MqttException
import org.eclipse.paho.mqttv5.common.MqttMessage
import org.eclipse.paho.mqttv5.common.MqttSubscription
import org.slf4j.Logger
import pt.nfriacowboy.presib.hermes.logger.NetLogger
import pt.nfriacowboy.presib.hermes.utils.DefaultProperties
import pt.nfriacowboy.presib.hermes.utils.IEnvironment


class MQTTService(_environment: IEnvironment, netID: String, private val receiver: MqttCallback) {
    private val timeout: Long = 5000
    private val environment = _environment
    private val clientId: String = netID

    private val systemTopic = Key("mqtt.systemTopic", stringType)
    private val qos = 2
    private val broker = environment.config[DefaultProperties.MQTT_SERVER] + ":" + environment.config[DefaultProperties.MQTT_PORT]
    private val logger: Logger = NetLogger.getLogger(netID)

    private var persistence = MemoryPersistence()
    private lateinit var mqttClient: MqttAsyncClient


    fun connect() {
        try {
            val connOpts = MqttConnectionOptions()
            connOpts.isCleanStart = false
            mqttClient = MqttAsyncClient(broker, clientId, persistence)

            mqttClient.setCallback(receiver)
            println("Connecting to broker: $broker")
            val token = mqttClient.connect(connOpts)
            token.waitForCompletion(timeout)
            println("Connected")
        } catch (ex: MqttException) {
            logger.error(ex.message, ex.printStackTrace())
        }
    }

    fun sendSystemMessage(message: String) {
        sendMesssage(environment.config[systemTopic], message)
    }

    fun sendMesssage(topic: String, message: String) {
        try {
            reconnect()
            logger.info("Publishing message: $message")
            val message = MqttMessage(message.toByteArray())
            message.qos = qos
            val token = mqttClient.publish(topic, message)
            token.waitForCompletion(timeout)
            logger.info("message sent")
        } catch (ex: MqttException) {
            logger.error(ex.message, ex.printStackTrace())
        }

    }

    fun subscrive(topic: String) {


        // Subscribe to a topic
        logger.info("Subscribing to: " + topic)
        val subscription = MqttSubscription(topic)
        val subscribeToken: IMqttToken = mqttClient.subscribe(arrayOf(subscription))
        subscribeToken.waitForCompletion(timeout)
        logger.info("Subscrived on : " + topic)
    }


    private fun reconnect() {
        if (!mqttClient.isConnected) {
            connect()
        }
    }
}