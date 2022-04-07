package pt.nfriacowboy

import pt.nfriacowboy.logger.NetLogger
import pt.nfriacowboy.services.DemoServerService
import pt.nfriacowboy.services.MQTTService
import pt.nfriacowboy.utils.SystemEnvironment

class CallMock {

    private val logger = NetLogger.getLogger(javaClass)

    var environment = SystemEnvironment()
    var demoService: DemoServerService = DemoServerService(environment)

    fun sayHello() {
        logger.info("Hello World!")
        val serverResult = demoService.demoCall()
        logger.info(serverResult)


    }

    fun startMqqtServer(){
        logger.info("send mqtt message")

    }

}