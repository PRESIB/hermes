import org.slf4j.Logger
import pt.nfriacowboy.CallMock
import pt.nfriacowboy.logger.NetLogger
import sun.misc.Signal
import kotlin.system.exitProcess


private val logger: Logger = NetLogger.getLogger("main")
private var callMock: CallMock = CallMock()



fun main() {
    Signal.handle(Signal("INT")) { stopApplication() }
    Signal.handle(Signal("TERM")) { stopApplication() }
    callMock.startMqqtServer()
    while (true) { }
}
fun stopApplication(){
    logger.info("I'll be back")
    exitProcess(0)
}

