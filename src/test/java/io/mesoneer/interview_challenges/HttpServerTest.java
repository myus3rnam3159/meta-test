package io.mesoneer.interview_challenges;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.net.http.HttpRequest.BodyPublishers;

import io.mesoneer.interview_challenges.level7.APIServer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static io.mesoneer.interview_challenges.Constants.SERVER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.mesoneer.interview_challenges.Constants.PORT;
import static io.mesoneer.interview_challenges.Constants.HTTP;

public class HttpServerTest {

    private static Thread serverThread;

    @BeforeAll
    public static void startServer(){

        serverThread = new Thread(
                () -> {
                    APIServer.main(new String[] {});
                });
        serverThread.setDaemon(true);
        serverThread.start();
    }

    @Test
    public void isClosedRangeAPIOk() throws Exception {
        System.setProperty("jdk.internal.httpclient.disableHostnameVerification", Boolean.TRUE.toString());

        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());

        HttpResponse<String> response;
        try (HttpClient httpClient = HttpClient.newBuilder()
                .sslContext(sc)
                .build()) {

            Map<String, Object> body = new HashMap<>();
            body.put("range", "[0, 100]");
            body.put("value", 50);

            ObjectMapper objectMapper = new ObjectMapper();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(HTTP, null, SERVER, PORT, "/range", null, null))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        assertEquals("true", response.body().trim());
    }

    @AfterAll
    public static void stopServer() {
        if (serverThread.isAlive()) {
            serverThread.interrupt();
        }
    }
}
