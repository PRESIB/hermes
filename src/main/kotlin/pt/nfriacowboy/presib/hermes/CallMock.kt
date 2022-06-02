package pt.nfriacowboy.presib.hermes

import pt.nfriacowboy.presib.hermes.logger.NetLogger
import pt.nfriacowboy.presib.hermes.services.DemoServerService
import pt.nfriacowboy.presib.hermes.utils.SystemConfig

class CallMock {

    private val logger = NetLogger.getLogger(javaClass)

    var environment = SystemConfig()
    var demoService: DemoServerService = DemoServerService(environment)

    fun sayHello() {
        logger.info("Hello World!")
        val serverResult = demoService.demoCall()
        logger.info(serverResult)


    }

    fun startMqqtServer() {
        logger.info("send mqtt message")

    }

}