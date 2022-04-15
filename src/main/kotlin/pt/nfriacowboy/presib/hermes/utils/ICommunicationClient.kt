package pt.nfriacowboy.presib.hermes.utils

interface ICommunicationClient {

    fun messageProcessor(processor: IMessageProcessor)
    fun subscribe(topic: String)
    fun sendMessage(topic: String, message: String)
    fun connect()
}