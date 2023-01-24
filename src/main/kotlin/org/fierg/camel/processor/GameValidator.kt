package org.fierg.camel.processor

import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.fierg.model.dto.GameDTO

class GameValidator :Processor {
    @Throws(Exception::class)
    override fun process(exchange: Exchange) {
        val game: GameDTO = exchange.getIn().getBody(GameDTO::class.java)
        //gameStringFromDTO(game)
    }
}

