import pt.nfriacowboy.presib.hermes.CallMock
import pt.nfriacowboy.presib.hermes.logger.NetLogger


private val logger = NetLogger.getLogger("main")
private var callMock: CallMock = CallMock()

fun main() {
    callMock.sayHello()
    logger.info("What is your name?")
    val name = readLine()
    logger.info("Hello, $name")


}