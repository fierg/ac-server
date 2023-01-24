package io.github.fierg.camel.routes

import io.github.fierg.model.dto.GameDTO
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.paho.PahoComponent
import org.apache.camel.component.paho.PahoConfiguration
import org.apache.camel.model.dataformat.JsonLibrary
import io.github.fierg.camel.format.GameStringRequestDataFormat
import io.github.fierg.camel.processor.GameValidator

class MQTTtoGRPCRouteBuilder : RouteBuilder() {
    override fun configure() {
        val mqttComponent = PahoComponent()
        mqttComponent.configuration = PahoConfiguration().apply {
            brokerUrl = "tcp://localhost:8883"
            clientId = "camel"
            willTopic = "GAME"
        }
        context.addComponent("paho", mqttComponent)

        from("paho:GAME")
            .log("Receiving game: ${body()}")
            .unmarshal().json(JsonLibrary.Jackson, GameDTO::class.java)
            .process(GameValidator())
            .log("Sending GameRequestString to grpc ...")
            .marshal(GameStringRequestDataFormat())
            .to("grpc://localhost:15001/org.fierg.GameServer?method=solve&synchronous=true");
    }
}