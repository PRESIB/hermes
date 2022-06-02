package pt.nfriacowboy.presib.hermes.executables

import pt.nfriacowboy.presib.hermes.holons.ResourceHolon

val resourceHolon:ResourceHolon =  ResourceHolon("rht-001")

fun main() {
println(resourceHolon.currentId())
    println(resourceHolon.topicDevice())
    println(resourceHolon.topicNet())
resourceHolon.reserveService("ssadasd", "sadas");
    resourceHolon.reserveService("ssadasd", "sadasw");
    resourceHolon.freeService("ssadasd", "sadas")
}