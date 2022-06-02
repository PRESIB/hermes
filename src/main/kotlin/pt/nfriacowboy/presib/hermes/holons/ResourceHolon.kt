package pt.nfriacowboy.presib.hermes.holons

import pt.nfriacowboy.presib.hermes.utils.DefaultProperties
import pt.nfriacowboy.presib.hermes.utils.HolonConfig
import pt.nfriacowboy.presib.hermes.utils.HolonProperties
import pt.nfriacowboy.presib.hermes.utils.SystemConfig

class ResourceHolon(holonId: String) : PresibHolon(holonId) {

  val services:List<String> =holonConfig.config[HolonProperties.HOLON_SERVICES].split(",")
    val topicNet:String = systemConfig.config[DefaultProperties.HOLON_MQTT_TOPIC_NET]
    val topicDevice:String =systemConfig.config[DefaultProperties.HOLON_MQTT_TOPIC_DEVICE]


}