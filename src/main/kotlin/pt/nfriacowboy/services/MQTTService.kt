package pt.nfriacowboy.services

import com.natpryce.konfig.Key
import com.natpryce.konfig.intType
import com.natpryce.konfig.stringType
import org.eclipse.paho.mqttv5.client.IMqttToken
import org.eclipse.paho.mqttv5.client.MqttActionListener
import org.eclipse.paho.mqttv5.client.MqttAsyncClient
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence
import org.eclipse.paho.mqttv5.common.MqttException
import org.eclipse.paho.mqttv5.common.MqttMessage
import org.slf4j.Logger
import pt.nfriacowboy.logger.NetLogger
import pt.nfriacowboy.utils.IEnvironment


class MQTTService(_environment: IEnvironment, netID: String) {
    private val environment = _environment
    private val clientId: String = netID
    private val server = Key("mqtt.url", stringType)
    private val port = Key("mqtt.port", intType)
    private val systemTopic = Key("mqtt.systemTopic", stringType)
    private val qos = 2
    private val broker = environment.config[server] + ":" + environment.config[port]
    private  val logger: Logger = NetLogger.getLogger(netID)

    private var persistence = MemoryPersistence()
    private lateinit var mqttClient: MqttAsyncClient
    private lateinit var token: IMqttToken



    fun connect() {
        try {
            val connOpts = MqttConnectionOptions()
            connOpts.isCleanStart = false
            mqttClient = MqttAsyncClient(broker, clientId, persistence)
            println("Connecting to broker: $broker")
            token = mqttClient.connect(connOpts)
            token.waitForCompletion()
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
            token = mqttClient.publish(topic, message)
            token.waitForCompletion()
            logger.info("message sent")
        } catch (ex: MqttException) {
            logger.error(ex.message, ex.printStackTrace())
        }

    }

//    fun subscrive() {
//
//        mqttClient.setCallback(mqttV5Receiver)
//        try {
//            val topic = "esp32/temperature"
//            mqttClient.subscribe(topic, qos, null, object : MqttActionListener {
//                override fun onSuccess(asyncActionToken: IMqttToken) {
//                    logger.info(asyncActionToken.message.toString())
//                }
//
//                override fun onFailure(
//                    asyncActionToken: IMqttToken,
//                    exception: Throwable
//                ) {
//                    logger.error("Error receiving message on topic ${asyncActionToken.topics}", exception)
//                }
//            })
//        } catch (ex: MqttException) {
//            logger.error(ex.message, ex.printStackTrace())
//        }
//    }




    private fun reconnect() {
        if (!mqttClient.isConnected) {
            connect()
        }
    }
}