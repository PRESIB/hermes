package pt.nfriacowboy

import pt.nfriacowboy.logger.NetLogger
import pt.nfriacowboy.services.MQTTService
import pt.nfriacowboy.utils.MqttV5Receiver
import pt.nfriacowboy.utils.ReceivedMessage
import pt.nfriacowboy.utils.SystemEnvironment
import de.renew.net.TransitionInstance


class MqttClient(netID: String) {

    private val logger = NetLogger.getLogger(javaClass)
    var subscriversObservers = mutableMapOf<String, MutableList<(ReceivedMessage) -> Unit>>()
    var topicMessages = mutableMapOf<String, String>()
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

    fun  subscrive(topic: String, transition: TransitionInstance){


        subscriversObservers.getOrPut(topic, ::mutableListOf).add { result: ReceivedMessage ->
            transition.fireOneBinding(true)
            logger.info("transition should be trigger now and process this" + result.message.toString())
        }

        mqttService.subscrive(topic)
    }

    fun  subscrive(topic: String){


        subscriversObservers.getOrPut(topic, ::mutableListOf).add { result: ReceivedMessage ->
            logger.info(" Simple subscrive transition should be trigger now and process this" + result.message.toString())
        }

        mqttService.subscrive(topic)
    }
    fun  subscrive(topic: String, status: String){


        subscriversObservers.getOrPut(topic, ::mutableListOf).add { result: ReceivedMessage ->
            logger.info(" Simple subscrive transition should be trigger now and process this" + result.message.toString())
            topicMessages.set(result.topic, result.message.toString())
        }

        mqttService.subscrive(topic)
    }

    fun topicState(topic:String, state: String): Boolean {
        val message = topicMessages.getOrDefault(topic, "")
        logger.info("this is the message in guard : " + message)
        return message == state
    }




}


