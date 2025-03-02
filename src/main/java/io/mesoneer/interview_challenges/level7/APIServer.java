package io.mesoneer.interview_challenges.level7;

import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class APIServer {
    public static void main(String[] args) {

        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", System.getenv("SSL_CERT_BASE64"));
        System.setProperty("javax.net.ssl.keyStorePassword", System.getenv("SSL_CERT_PW"));
        System.setProperty("javax.net.debug", "ssl");

        SSLServerSocketFactory sslFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try (SSLServerSocket s = (SSLServerSocket) sslFactory.createServerSocket(8189);) {

            int i = 1;
            while (true) {

                Socket incoming = s.accept();
                System.out.println("Spawning " + i);

                Runnable r = new ThreadEchoHandler(incoming);
                Thread t = new Thread(r);

                t.start();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
