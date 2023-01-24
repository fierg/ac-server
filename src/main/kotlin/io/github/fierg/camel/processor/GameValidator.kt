package io.github.fierg.camel.processor

import io.github.fierg.model.dto.GameDTO
import org.apache.camel.Exchange
import org.apache.camel.Processor

class GameValidator :Processor {
    @Throws(Exception::class)
    override fun process(exchange: Exchange) {
        val game: GameDTO = exchange.getIn().getBody(GameDTO::class.java)
        println("Game id: ${game.gameID}")
        //gameStringFromDTO(game)
    }
}

