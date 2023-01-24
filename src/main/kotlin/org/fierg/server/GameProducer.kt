package org.fierg.server

import com.google.gson.Gson
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.fierg.logger.LogConsumer
import org.fierg.logger.impl.MQTTLogger
import org.fierg.model.EncryptedGameInstance
import org.fierg.solver.Generator
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


object GameProducer {

    val server = "TestServer"
    val topic = "GAME"
    var generateRunnable = Runnable { generateAndPublish() }

    private fun generateAndPublish(){
        LogConsumer.getImpl().info("Publishing new game...")
        val game = Generator.generateRandom(3)
        val gameDTO = EncryptedGameInstance.fromGameInstance(game).toGameDTO(server, 0)
        MQTTLogger.getMQTTServer().publish(topic, MqttMessage(Gson().toJson(gameDTO).toByteArray()))
        LogConsumer.getImpl().info("Published game.")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        LogConsumer.getImpl().info("Game Publisher started. Publishing to ${MQTTLogger.getMQTTServer().serverURI} @ $topic once every minute.")

        val executor = Executors.newScheduledThreadPool(1)
        executor.scheduleAtFixedRate(generateRunnable, 0, 10, TimeUnit.SECONDS)    }
}

