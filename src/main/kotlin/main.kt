import ch.qos.logback.classic.util.ContextInitializer
import org.slf4j.LoggerFactory
import pt.nfriacowboy.CallMock
import pt.nfriacowboy.logger.NetLogger


 val logger = NetLogger.getLogger("main")
var callMock: CallMock = CallMock()

fun main() {
    callMock.sayHello()
    logger.info ( "What is your name?")
    val name = readLine()
    logger.info ( "Hello, $name")


}