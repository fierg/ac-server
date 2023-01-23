package org.fierg.camel

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.paho.PahoComponent
import org.apache.camel.component.paho.PahoConfiguration

class MQTTRouteBuilder : RouteBuilder() {
    override fun configure() {
        val mqttComponent = PahoComponent()
        mqttComponent.configuration = PahoConfiguration().apply {
            brokerUrl = "tcp://localhost:8883"
            clientId = "camel"
            willTopic = "GAME"
        }
        context.addComponent("paho", mqttComponent)

        from("timer:test?period=1000").setBody(constant("Testing timer2")).to("paho:GAME")

        from("paho:GAME").process { e ->
            val body = (e.`in`?.body as? ByteArray)?.let { String(it) }
            println("test body 1 => $body")
        }

    }
}