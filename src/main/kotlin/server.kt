import org.slf4j.Logger
import pt.nfriacowboy.CallMock
import pt.nfriacowboy.MqttClient
import pt.nfriacowboy.logger.NetLogger
import sun.misc.Signal
import kotlin.system.exitProcess


private val logger: Logger = NetLogger.getLogger("main")
private val mqttClient = MqttClient("demoServer")


fun main() {
    Signal.handle(Signal("INT")) { stopApplication() }
    Signal.handle(Signal("TERM")) { stopApplication() }
    mqttClient.connect()
    mqttClient.sendMessage("esp32/output", "on")
    mqttClient.sendMessage("esp32/output", "off")
    mqttClient.subscrive( "esp32/temperature", ::sayHi)
    mqttClient.subscrive( "esp32/humidity", ::sayHi1)
    mqttClient.subscrive( "esp32/humidity", ::sayHi2)

    //while (true) { }
}
fun stopApplication(){
    logger.info("I'll be back")
    exitProcess(0)
}

fun sayHi(value: String){
    logger.info("say Hi " +value)
    mqttClient.sendMessage("esp32/output", "on")
}

fun sayHi1(value: String){
    logger.info("say Hi1 " +value)
}

fun sayHi2(value: String){
    logger.info("say Hi2 " +value)

    mqttClient.sendMessage("esp32/output", "off")
}
