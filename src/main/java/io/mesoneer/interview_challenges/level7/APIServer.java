package io.mesoneer.interview_challenges.level7;

import java.net.ServerSocket;
import java.net.Socket;

public class APIServer {
    public static void main(String[] args ) {
        try (var s = new ServerSocket(8189)){
            int i = 1;

            while(true){
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
