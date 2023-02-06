package io.github.fierg.camel.routes

import io.github.fierg.model.dto.GameDTO
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.paho.PahoComponent
import org.apache.camel.component.paho.PahoConfiguration
import org.apache.camel.model.dataformat.JsonLibrary
import io.github.fierg.camel.processor.GameDTOtoGameStringRequestProcessor
import io.github.fierg.camel.processor.GameValidator

class MQTTtoGRPCRouteBuilder : RouteBuilder() {
    override fun configure() {
        val recieveMQTT = PahoComponent()
        recieveMQTT.configuration = PahoConfiguration().apply {
            brokerUrl = "tcp://marvin.syssoft.uni-trier.de:5042"
            clientId = "s4svfier-ac"
            willTopic = "Zahlenraetsel"
            userName = "sa4e"
            password = "wynrgcHUXL9UHwfkc4-K"
        }
        context.addComponent("paho", recieveMQTT)

        from("paho:Zahlenraetsel")
            .log("Receiving game from MQTT Broker: ${body()}")
            .unmarshal().json(JsonLibrary.Jackson, GameDTO::class.java)
            .process(GameDTOtoGameStringRequestProcessor())
            .log("Processing done. calling gRPC ...")
            .to("grpc://localhost:15001/io.github.fierg.GameServer?method=solve&synchronous=true")
            .log("GRPC done. preparing solution for MQTT ...")
            .process(GameValidator())
            .marshal().json(JsonLibrary.Jackson, true)
            .log("marshaling done. sending to MQTT ...")
            //.to("paho2:Loesung")
    }
}