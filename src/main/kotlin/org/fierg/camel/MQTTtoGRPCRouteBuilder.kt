package org.fierg.camel

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.paho.PahoComponent
import org.apache.camel.component.paho.PahoConfiguration
import org.fierg.logger.LogConsumer

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
            //.unmarshal().jacksonXml(org.fierg.model.dto.GameDTO.class)
            .process { e ->
                val body = (e.`in`?.body as? ByteArray)?.let { String(it) }
                LogConsumer.getImpl().info("$body")
            }
            //.to("grpc://localhost:15001/org.fierg.GameServerGrpcKt?method=GameServerCoroutineStub&synchronous=true");
    }
}