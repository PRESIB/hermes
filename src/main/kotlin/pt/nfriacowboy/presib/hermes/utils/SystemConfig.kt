package pt.nfriacowboy.presib.hermes.utils

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.overriding
import java.io.File

class SystemConfig(filePath: String = "/presib/hermes/default.properties") : IEnvironment {


    override var config = systemProperties() overriding
            EnvironmentVariables() overriding
            ConfigurationProperties.fromFile(File(filePath))


}

