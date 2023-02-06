# ac-server
Apache camel server for sending and receiving gRPC calls / MQTT Messages.
GameProducer publishes a game to the MQTT server every 30 sek.

### Build

Project depends on [fierg/gRPC-demo](github.com/fierg/gRPC-demo), as they share DTO classes and utilities.
Build other project first with `maven install`. Project will be published to the local maven repository.

Path to local maven home has to be added to the properties in the `pom.xml`.
Build with `mvn clean install` to create a runnable jar in `target/ac-server-1.0-SNAPSHOT-jar-with-dependencies.jar`

Run with your preferred JVM.