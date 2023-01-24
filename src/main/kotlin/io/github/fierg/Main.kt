package org.fierg

import org.apache.camel.main.Main
import org.apache.log4j.BasicConfigurator
import io.github.fierg.camel.routes.MQTTtoGRPCRouteBuilder

object Main {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        BasicConfigurator.configure();
        val main = Main()
        main.configure().addRoutesBuilder(MQTTtoGRPCRouteBuilder())
        main.run(args)
    }
}