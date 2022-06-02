package pt.nfriacowboy.presib.hermes

import pt.nfriacowboy.presib.hermes.communication.*
import pt.nfriacowboy.presib.hermes.logger.NetLogger
import pt.nfriacowboy.presib.hermes.services.MQTTService
import pt.nfriacowboy.presib.hermes.utils.*


class MqttClient(netID: String) : ICommunicationClient {

    private val logger = NetLogger.getLogger(javaClass)
    var subscriversObservers = mutableMapOf<String, MutableList<(ReceivedMessage) -> Unit>>()
    var topicMessages = mutableMapOf<String, String>()
    val mqttV5Receiver = MqttV5Receiver(subscriversObservers)
    var environment = SystemConfig()
    val mqttService: MQTTService = MQTTService(environment, netID, mqttV5Receiver)
    var processor: IMessageProcessor = DefaultMessageProcessor()


    override fun connect() {

        mqttService.connect()


    }

    override fun sendMessage(topic: String, message: String) {

        logger.info("Send \n\t message: '$message' \n\t topic: $topic")
        mqttService.sendMesssage(topic, message)


    }

    override fun subscribe(topic: String) {


        subscriversObservers.getOrPut(topic, ::mutableListOf).add { result: ReceivedMessage ->
            logger.info(" Simple subscribe transition should be trigger now and process this " + result.message.toString())
try {
    logger.info("sending message to processor")
    this.processor.messageReceived(result.message)
    logger.info("message sent")
}catch (error:Exception){
    logger.error(error.message, error)
}



        }

        mqttService.subscrive(topic)

    }
    override fun messageProcessor(processor: IMessageProcessor) {
        logger.info("adding processor", processor)
        this.processor = processor
    }


}



