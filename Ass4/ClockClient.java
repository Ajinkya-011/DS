import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockClient {
    public static void main(String[] args) {
        String server = "localhost"; // Change if needed
        int port = 8080;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (
            Socket socket = new Socket(server, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Connected to Clock Server.");
            while (true) {
                String currentTime = dateFormat.format(new Date());
                out.println(currentTime);
                System.out.println("Sent time: " + currentTime);
                String syncTime = in.readLine();
                System.out.println("Received sync time: " + syncTime);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*
RUN Commandss##

javac *.java
java ClockServer

---------------------------------------------
New Terminal
-------------------------------------------
java ClockClient
*/
