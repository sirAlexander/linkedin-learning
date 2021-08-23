package com.shopping.server;

import com.shopping.service.UserServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServer {

    private static final Logger logger = Logger.getLogger(UserServer.class.getName());
    private Server server;

    public static void main(String[] args) throws InterruptedException {
        UserServer userServer = new UserServer();
        userServer.startServer();
        userServer.blockUntilShutdown();

        // Steps needed to get this running from the commandline as an executable jar
        // i.e. java -jar <path to jar>
        // 1. need to make changes to pom.xml file
        // 2. add a plugin to create a fat jar
        // 3. add the main method class in the pom.xml file
        // 4. add resources folder to the jar
    }

    public void startServer() {
        int port = 50051;
        try {
            server = ServerBuilder.forPort(port)
                    .addService(new UserServiceImpl())
                    .build()
                    .start();
            logger.info("Server started on port: " + port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("Clean server shutdown in case JVM was shutdown");
                try {
                    UserServer.this.stopServer();
                } catch (InterruptedException exception) {
                    logger.log(Level.SEVERE, "Server shutdown interrupted", exception);
                }
            }));
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Server did not start", exception);
        }
    }

    public void stopServer() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


}
