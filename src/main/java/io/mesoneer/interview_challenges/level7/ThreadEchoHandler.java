package io.mesoneer.interview_challenges.level7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mesoneer.interview_challenges.level3.Range;

public class ThreadEchoHandler implements Runnable {
    private Socket incoming;

    public ThreadEchoHandler(Socket incomingSocket) {
        this.incoming = incomingSocket;
    }

    public void run() {
        try (
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                BufferedReader in = new BufferedReader(new java.io.InputStreamReader(inStream, StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true)) {

            // Process headers
            boolean outOfHeaders = false;
            int contentLength = 0;
            String line;

            while (!outOfHeaders && (line = in.readLine()) != null) {
                if (line.isEmpty()) {
                    outOfHeaders = true;
                    continue;
                }
                System.out.println(line);
                //validate route not yet here
                if (line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.substring("Content-Length: ".length()));
                }
            }

            // Process body
            StringBuilder body = new StringBuilder();
            char[] buffer = new char[1024];
            int bytesRead;

            while (contentLength > 0
                    && (bytesRead = in.read(buffer, 0, Math.min(buffer.length, contentLength))) != -1) {
                body.append(buffer, 0, bytesRead);
                contentLength -= bytesRead;
            }

            System.out.println(body.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            RangeValue obj = objectMapper.readValue(body.toString(), RangeValue.class);

            System.out.println(obj.getRange());
            System.out.println(obj.getValue());

            Range<Integer> range = Range.parse(obj.getRange(), "\\[(\\d+),\\s*(\\d+)\\]");


            out.println("HTTP/1.1 201 Created");
            out.println();

            if(range.contains(obj.getValue())){
                out.println("true");
            }
            else{
                out.println("false");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}