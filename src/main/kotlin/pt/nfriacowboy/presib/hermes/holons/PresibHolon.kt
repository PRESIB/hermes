package pt.nfriacowboy.presib.hermes.holons

import pt.nfriacowboy.presib.hermes.utils.HolonConfig
import pt.nfriacowboy.presib.hermes.utils.SystemConfig

abstract class PresibHolon(private val holonId:String) {
    val systemConfig = SystemConfig()
    val holonConfig=HolonConfig(holonId, systemConfig)

}