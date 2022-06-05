package pt.nfriacowboy.presib.hermes.holons


import pt.nfriacowboy.presib.hermes.utils.DefaultProperties
import pt.nfriacowboy.presib.hermes.utils.HolonConfig
import pt.nfriacowboy.presib.hermes.utils.HolonProperties
import pt.nfriacowboy.presib.hermes.utils.SystemConfig


abstract class PresibHolon(val holonId: String) {
    val systemConfig = SystemConfig()
    val holonConfig = HolonConfig(holonId, systemConfig)

    fun services() = holonConfig.config[HolonProperties.HOLON_SERVICES]

    fun toWarehouseService() = systemConfig.config[DefaultProperties.SYSTEM_TO_WAREHOUSE_TRANSPORTATION_SERVICE]
    open fun transportService() = systemConfig.config[DefaultProperties.SYSTEM_TRANSPORTATION_SERVICE]
    fun currentId() = holonId


}