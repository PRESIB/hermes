package pt.nfriacowboy

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pt.nfriacowboy.logger.NetLogger
import pt.nfriacowboy.services.DemoServerService
import pt.nfriacowboy.services.MQTTService
import pt.nfriacowboy.utils.SystemEnvironment

class MqttClient(netID: String) {

    private val logger = NetLogger.getLogger(javaClass)

    var environment = SystemEnvironment()
    val mqttService: MQTTService = MQTTService(environment, netID)


   fun connect(){
    mqttService.connect()


   }

    fun sendMessage(topic:String, message: String)=runBlocking{
        launch {
            logger.info("Send \n\t message: '$message' \n\t topic: $topic")
            mqttService.sendMesssage(topic, message)
            return@launch
        }

    }

}