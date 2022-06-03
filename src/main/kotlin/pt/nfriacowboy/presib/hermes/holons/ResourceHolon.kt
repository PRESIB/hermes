package pt.nfriacowboy.presib.hermes.holons

import pt.nfriacowboy.presib.hermes.utils.DefaultProperties

class ResourceHolon(holonId: String) : PresibHolon(holonId) {

    private val reserverServices: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun topicNet() = "${systemConfig.config[DefaultProperties.HOLON_MQTT_TOPIC_NET]}$holonId"

    fun topicDevice() = "${systemConfig.config[DefaultProperties.HOLON_MQTT_TOPIC_DEVICE]}$holonId"

    fun reserveService(service: String, requesterHolonId: String) {
        if (reserverServices[service] == null) {
            reserverServices[service] = mutableListOf(requesterHolonId)
        } else {
            reserverServices[service]?.add(requesterHolonId)
        }
    }

    fun freeService(service: String, requesterHolonId: String) {
        reserverServices[service]?.remove(requesterHolonId)
    }


}

//}IEC 61499