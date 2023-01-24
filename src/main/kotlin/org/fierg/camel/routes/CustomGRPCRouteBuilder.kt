package org.fierg.camel.routes

import org.apache.camel.builder.RouteBuilder

class CustomGRPCRouteBuilder : RouteBuilder() {
    override fun configure() {
        from("direct:grpc-sync")
            .to("grpc://localhost:15001/org.fierg.server.GameServer?method=serveGRPCRPC&synchronous=true");
    }
}
