package pt.nfriacowboy.presib.hermes.communication

import org.eclipse.paho.mqttv5.client.IMqttToken
import org.eclipse.paho.mqttv5.client.MqttCallback
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse
import org.eclipse.paho.mqttv5.common.MqttException
import org.eclipse.paho.mqttv5.common.MqttMessage
import org.eclipse.paho.mqttv5.common.packet.MqttProperties
import pt.nfriacowboy.presib.hermes.logger.NetLogger
import java.lang.*
import java.util.*
import kotlin.properties.Delegates

class MqttV5Receiver(observers: MutableMap<String, MutableList<(ReceivedMessage) -> Unit>>) : MqttCallback {
    private val log = NetLogger.getLogger(javaClass)



    /**
     * For the in bound message.
     */


    var receivedMessages: MutableList<ReceivedMessage> = ArrayList()

    var newestArticleUrl: ReceivedMessage by Delegates.observable(ReceivedMessage("", MqttMessage())) { _, _, newValue ->
        observers[newValue.topic]?.forEach { it(newValue) }
    }


    override fun disconnected(disconnectResponse: MqttDisconnectResponse?) {
        TODO("Not yet implemented")
    }

    override fun mqttErrorOccurred(exception: MqttException?) {
        TODO("Not yet implemented")
    }

    /**
     * @param topic
     * @param message
     * @throws Exception
     */
    override fun messageArrived(topic: String, message: MqttMessage) {


        receivedMessages.add(ReceivedMessage(topic, message))
        newestArticleUrl = ReceivedMessage(topic, message)
        //log.info("message received '" + message + "' on topic " + topic)

    }

    override fun deliveryComplete(token: IMqttToken?) {
        TODO("Not yet implemented")
    }

    override fun connectComplete(reconnect: Boolean, serverURI: String?) {
        TODO("Not yet implemented")
    }

    override fun authPacketArrived(reasonCode: Int, properties: MqttProperties?) {
        TODO("Not yet implemented")
    }


}