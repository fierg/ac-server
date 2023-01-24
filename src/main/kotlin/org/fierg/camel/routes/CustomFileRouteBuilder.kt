package org.fierg.camel.routes

import org.apache.camel.builder.RouteBuilder

class CustomFileRouteBuilder : RouteBuilder() {
    override fun configure() {
        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
        from("file:src/data?noop=true")
            .choice()
            .`when`(xpath("/person/city = 'London'"))
            .log("UK message")
            .to("file:target/messages/uk")
            .otherwise()
            .log("Other message")
            .to("file:target/messages/others")
    }
}