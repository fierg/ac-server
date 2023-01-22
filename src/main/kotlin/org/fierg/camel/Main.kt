package org.fierg.camel

import org.apache.camel.main.Main
import org.apache.log4j.BasicConfigurator

object Main {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        BasicConfigurator.configure();
        val main = Main()
        main.configure().addRoutesBuilder(CustomGRPCRouteBuilder())
        main.run(args)
    }
}