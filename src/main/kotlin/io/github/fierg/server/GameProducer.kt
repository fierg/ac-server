package io.github.fierg.server

import com.google.gson.Gson
import io.github.fierg.logger.LogConsumer
import io.github.fierg.logger.impl.MQTTLogger
import io.github.fierg.model.EncryptedGameInstance
import io.github.fierg.solver.Generator
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


object GameProducer {

    private const val serverName = "GameProducer"
    private const val topic = "GAME"
    private val generateRunnable = Runnable { generateAndPublish() }

    private fun generateAndPublish(){
        LogConsumer.getImpl().info("Publishing new game...")
        val game = Generator.generateRandom(3)
        val gameDTO = EncryptedGameInstance.fromGameInstance(game).toGameDTO(serverName, 0)
        MQTTLogger.getMQTTServer().publish(topic, MqttMessage(Gson().toJson(gameDTO).toByteArray()))
        LogConsumer.getImpl().info("Published game.")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        LogConsumer.getImpl().info("Game Publisher started. Publishing to ${MQTTLogger.getMQTTServer().serverURI} @ $topic once every minute.")
        val executor = Executors.newScheduledThreadPool(1)
        executor.scheduleAtFixedRate(generateRunnable, 0, 30, TimeUnit.SECONDS)
    }
}

