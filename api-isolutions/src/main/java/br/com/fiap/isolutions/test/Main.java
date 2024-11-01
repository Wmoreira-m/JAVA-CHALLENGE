package br.com.fiap.isolutions.test;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

    // URL base do servidor
    public static final String BASE_URI = "http://localhost:8080/isolutions/";

    public static HttpServer startServer() {
        // Configura o pacote onde estão os recursos
        final ResourceConfig rc = new ResourceConfig().packages("br.com.fiap.isolutions.resource");
        rc.register(JsonBindingFeature.class);

        String porta = System.getenv("PORTA");
        if (porta == null || porta.isEmpty()){
            porta = "8080";
        }

        URI baseUri = URI.create("http://0.0.0.0:" + porta + "/");

        return GrizzlyHttpServerFactory.createHttpServer((baseUri), rc);
    }



    public static void main(String[] args) {
        final HttpServer server = startServer();
        System.out.println("Servidor iniciado: " + BASE_URI);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
