package pt.nfriacowboy.presib.hermes.utils

import org.eclipse.paho.mqttv5.common.MqttMessage

interface IMessageProcessor {

     fun messageReceived(message:MqttMessage)
}