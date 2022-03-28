package pt.nfriacowboy.logger

import ch.qos.logback.classic.util.ContextInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class NetLogger {

    companion object {
        private const val fileLocation = "/helix/hermes/logback.xml"
        fun getLogger(forClass: Class<*>): Logger {
            System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, fileLocation)
            return LoggerFactory.getLogger(forClass)
        }

        fun getLogger(name: String): Logger {
            System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, fileLocation)
            return LoggerFactory.getLogger(name)
        }
    }

    fun getLogger(name: String):Logger = NetLogger.getLogger(name)


}