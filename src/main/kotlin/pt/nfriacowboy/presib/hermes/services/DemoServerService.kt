package pt.nfriacowboy.presib.hermes.services

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.natpryce.konfig.Key
import com.natpryce.konfig.stringType
import pt.nfriacowboy.presib.hermes.utils.IEnvironment

class DemoServerService(_environment: IEnvironment) {
    private val environment = _environment
    private val server = Key("server.url", stringType)


    fun demoCall(): String {
        val (request, response, result) = environment.config[server]
            .httpGet()
            .responseString()

        return when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                println(ex)
                ex.message.toString()
            }
            is Result.Success -> {
                val data = result.get()
                println(data)
                data
            }
        }
    }

}