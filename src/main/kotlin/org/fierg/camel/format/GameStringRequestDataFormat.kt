package org.fierg.camel.format

import org.apache.camel.Exchange
import org.apache.camel.spi.DataFormat
import org.fierg.GameStringRequest
import org.fierg.camel.utils.Utils.gameStringFromDTO
import org.fierg.model.dto.GameDTO
import java.io.InputStream
import java.io.OutputStream

class GameStringRequestDataFormat : DataFormat {
    override fun start() {
    }

    override fun stop() {
    }

    override fun marshal(exchange: Exchange?, graph: Any?, stream: OutputStream?) {
        if (graph is GameDTO){
            //val game = exchange!!.context.typeConverter.mandatoryConvertTo(ByteArray::class.java, graph)
            val game = GameStringRequest.newBuilder().setName(gameStringFromDTO(graph)).build()
            stream!!.write(game.toByteString().toByteArray())
        } else {
            throw IllegalStateException()
        }
    }

    override fun unmarshal(exchange: Exchange?, stream: InputStream?): Any {
        TODO("Not yet implemented")
    }


}