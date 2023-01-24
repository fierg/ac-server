package io.github.fierg.camel.format

import org.apache.camel.Exchange
import org.apache.camel.spi.DataFormat
import io.github.fierg.GameStringRequest
import io.github.fierg.model.dto.GameDTO
import io.github.fierg.camel.utils.Utils.gameStringFromDTO
import java.io.InputStream
import java.io.ObjectOutputStream
import java.io.OutputStream

class GameStringRequestDataFormat : DataFormat {
    override fun start() {
    }

    override fun stop() {
    }

    override fun marshal(exchange: Exchange?, graph: Any?, stream: OutputStream?) {
        if (graph is GameDTO){
            //val game = exchange!!.context.typeConverter.mandatoryConvertTo(ByteArray::class.java, graph)
            val game = GameStringRequest.newBuilder().setPayload(gameStringFromDTO(graph)).build()
            ObjectOutputStream(stream).write(game.toByteArray())
        } else {
            throw IllegalStateException()
        }
    }

    override fun unmarshal(exchange: Exchange?, stream: InputStream?): Any {
        TODO("Not yet implemented")
    }


}