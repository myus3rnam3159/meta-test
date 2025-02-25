package io.mesoneer.interview_challenges;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
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

    private final HttpClient client = HttpClient.newHttpClient();
    private static Thread serverThread;

    @BeforeAll
    public static void startServer(){
        serverThread = new Thread(
                () -> {
                    APIServer.main(new String[]{});
                }
        );
        serverThread.setDaemon(true);
        serverThread.start();
    }

    @Test
    public void isClosedRangeAPIOk() throws Exception {

        Map<String, Object> body = new HashMap<>();
        body.put("range", "[0, 100]");
        body.put("value", 50);

        ObjectMapper objectMapper = new ObjectMapper();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(HTTP, null, SERVER, PORT, "/range", null, null))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                .build();

        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals("true", response.body().trim());
    }

    @AfterAll
    public static void stopServer(){
        if(serverThread.isAlive()){
            serverThread.interrupt();
        }
    }
}
