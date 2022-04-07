package pt.nfriacowboy

import pt.nfriacowboy.logger.NetLogger
import pt.nfriacowboy.services.MQTTService
import pt.nfriacowboy.utils.MqttV5Receiver
import pt.nfriacowboy.utils.ReceivedMessage
import pt.nfriacowboy.utils.SystemEnvironment

class MqttClient(netID: String) {

    private val logger = NetLogger.getLogger(javaClass)
    var subscriversObservers = mutableMapOf<String, MutableList<(ReceivedMessage) -> Unit>>()
    val mqttV5Receiver = MqttV5Receiver(subscriversObservers)
    var environment = SystemEnvironment()
    val mqttService: MQTTService = MQTTService(environment, netID, mqttV5Receiver)



   fun connect(){

    mqttService.connect()


   }

    fun sendMessage(topic:String, message: String){

            logger.info("Send \n\t message: '$message' \n\t topic: $topic")
            mqttService.sendMesssage(topic, message)



    }




    fun  subscrive(topic: String, func: (input: String) -> Unit){



        subscriversObservers.getOrPut(topic, ::mutableListOf).add { result: ReceivedMessage ->
            func(result.message.toString())
        }

        mqttService.subscrive(topic)
    }




}