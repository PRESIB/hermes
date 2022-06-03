package pt.nfriacowboy.presib.hermes.utils

class PresibUtils {
    companion object{
        fun holonIdBuilder(prefix:String, id:UInt) =  Companion.holonIdBuilder(prefix, id.toString())
        fun holonIdBuilder(prefix:String, id:Int) =  Companion.holonIdBuilder(prefix, id.toString())
        fun holonIdBuilder(prefix:String, id:String) =  "$prefix-${id.padStart(3, '0')}"
    }
}