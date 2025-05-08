import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

class ClockServer {
    private static final int PORT = 8080;
    private static final Map<String, Long> clientOffsets = new HashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Clock server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> handleClient(clientSocket)).start();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String clientTimeStr;
            while ((clientTimeStr = in.readLine()) != null) {
                long clientTime = dateFormat.parse(clientTimeStr).getTime();
                long offset = System.currentTimeMillis() - clientTime;
                clientOffsets.put(clientSocket.getRemoteSocketAddress().toString(), offset);
                long avgOffset = clientOffsets.values().stream().mapToLong(Long::longValue).sum() / clientOffsets.size();
                String syncTime = dateFormat.format(new Date(System.currentTimeMillis() + avgOffset));
                out.println(syncTime);
            }
        } catch (Exception e) {
            System.out.println("Client disconnected.");
        }
    }
}

