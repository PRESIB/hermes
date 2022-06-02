package pt.nfriacowboy.presib.hermes.utils

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.overriding
import java.io.File

class HolonConfig(
    holonId: String,
    systemConfig: SystemConfig
) : IEnvironment {

    val propertiesPath = "${systemConfig.config[DefaultProperties.HOLON_DEFAULT_CONFIG_PATH]}$holonId.properties"

    override var config = ConfigurationProperties.systemProperties() overriding
            EnvironmentVariables() overriding
            ConfigurationProperties.fromFile(File(propertiesPath))

}