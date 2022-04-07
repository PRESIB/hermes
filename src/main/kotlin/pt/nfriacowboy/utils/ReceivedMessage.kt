package pt.nfriacowboy.utils

import org.eclipse.paho.mqttv5.common.MqttMessage

class ReceivedMessage internal constructor(
    /**  */
    var topic: String,
    /**  */
    var message: MqttMessage
) {
    init {
        message = message
    }
}