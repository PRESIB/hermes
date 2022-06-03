package pt.nfriacowboy.presib.hermes.executables

import pt.nfriacowboy.presib.hermes.holons.ProductHolon
import pt.nfriacowboy.presib.hermes.utils.PresibUtils

val productHolon: ProductHolon = ProductHolon("ph-001")

fun main() {
    println(PresibUtils.holonIdBuilder("rht", 1))
    println(productHolon.currentId())
    println(productHolon.services())

    println(productHolon.nextService())
    println(productHolon.nextService())
    println(productHolon.nextService())
    println(productHolon.nextService())
    println(productHolon.nextService())

    println(productHolon.nextService())
    println(productHolon.nextService())
    println(productHolon.nextService())
    println(productHolon.nextService())

}