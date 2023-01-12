package org.fierg

fun main(args: Array<String>) {
    from("direct:grpc-sync")
        .to("grpc://remotehost:1101/org.apache.camel.component.grpc.PingPong?method=sendPing&synchronous=true");
}