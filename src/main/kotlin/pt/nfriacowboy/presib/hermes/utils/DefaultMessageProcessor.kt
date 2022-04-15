package pt.nfriacowboy.presib.hermes.utils

import org.eclipse.paho.mqttv5.common.MqttMessage
import pt.nfriacowboy.presib.hermes.logger.NetLogger


class DefaultMessageProcessor : IMessageProcessor {
    private val logger = NetLogger.getLogger(javaClass)
    override fun messageReceived(message: MqttMessage) {
        logger.info("Message received: $message")
    }
}