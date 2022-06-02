package pt.nfriacowboy.presib.hermes.communication

import org.eclipse.paho.mqttv5.common.MqttMessage

interface IMessageProcessor {

    fun messageReceived(message: MqttMessage)
}