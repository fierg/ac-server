package org.fierg.server

import com.google.gson.Gson
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.fierg.logger.impl.MQTTLogger
import org.fierg.model.EncryptedGameInstance
import org.fierg.solver.Generator

object GameProducer {

    val server = "TestServer"
    private fun generateAndPublish(){
        val game = Generator.generateRandom(3)
        val gameDTO = EncryptedGameInstance.fromGameInstance(game).toGameDTO(server, 0)
        MQTTLogger.getMQTTServer().publish("GAME", MqttMessage( Gson().toJson(gameDTO).toByteArray()))
    }
    @JvmStatic
    fun main(args: Array<String>) {
        println("Running")
        generateAndPublish()
    }
}

