package pt.nfriacowboy.presib.hermes.utils

import com.natpryce.konfig.Key
import com.natpryce.konfig.stringType

class HolonProperties {
    companion object{
        val HOLON_SERVICES = Key("holon.services", stringType)
    }

}