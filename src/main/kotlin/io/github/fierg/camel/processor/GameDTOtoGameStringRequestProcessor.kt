package io.github.fierg.camel.processor

import io.github.fierg.GameStringRequest
import io.github.fierg.camel.utils.Utils
import io.github.fierg.logger.impl.ConsoleLogger
import io.github.fierg.model.dto.GameDTO
import org.apache.camel.Exchange
import org.apache.camel.Processor

class GameDTOtoGameStringRequestProcessor :Processor {
    @Throws(Exception::class)
    override fun process(exchange: Exchange) {
        val game: GameDTO = exchange.getIn().getBody(GameDTO::class.java)
        ConsoleLogger.info("Game id: ${game.gameID} is valid.")
        val builder = GameStringRequest.newBuilder()
        builder.payload = Utils.gameStringFromDTO(game)
        exchange.message.body = builder.build()
    }
}
