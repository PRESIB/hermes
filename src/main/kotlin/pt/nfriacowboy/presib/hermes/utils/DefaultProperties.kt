package pt.nfriacowboy.presib.hermes.utils

import com.natpryce.konfig.Key
import com.natpryce.konfig.intType
import com.natpryce.konfig.stringType

class DefaultProperties {
    companion object {
        val MQTT_SERVER = Key("mqtt.url", stringType)
        val MQTT_PORT = Key("mqtt.port", intType)

        val HOLON_DEFAULT_CONFIG_PATH = Key("holon.config.path", stringType)

        val HOLON_MQTT_TOPIC_DEVICE = Key("holon.mqtt.device", stringType)
        val HOLON_MQTT_TOPIC_NET = Key("holon.mqtt.net", stringType)

    }
}