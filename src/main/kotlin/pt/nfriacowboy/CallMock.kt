package pt.nfriacowboy
import ch.qos.logback.classic.util.ContextInitializer
import org.slf4j.LoggerFactory
import pt.nfriacowboy.logger.NetLogger
import pt.nfriacowboy.services.DemoServerService
import pt.nfriacowboy.utils.SystemEnvironment

class CallMock {

    private val logger = NetLogger.getLogger(javaClass)

    var environment = SystemEnvironment()
    var demoService: DemoServerService = DemoServerService(environment)

    fun sayHello() {
        logger.info("Hello World!")
        val serverResult = demoService.demoCall()
       logger.info(serverResult )
    }


}