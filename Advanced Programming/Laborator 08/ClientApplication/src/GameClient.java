import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Victor Manoliu on 17-Apr-18
 */
public class GameClient {

    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8100;
    private static Socket clientSocket = null;

    PrintWriter printWriter = null;
    BufferedReader bufferedReader = null;

    public static void main(String[] args) throws IOException {

        GameClient client = new GameClient();
        clientSocket = new Socket(SERVER_ADDRESS, PORT);

        System.out.println("Client connected to server");
        System.out.println("Enter your command to play the game");

        while (true) {
            String request = client.readFromKeyboard();
            if (request.equalsIgnoreCase("exit")) {
                break;
            } else {
                client.sendRequestToServer(request);
                client.receiveResponseFromServer();
            }
        }
    }

    private void sendRequestToServer(String request) {
        try {
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            printWriter.println(request);
            printWriter.flush();
            System.out.println("The following message was sent with success to server: " + request);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveResponseFromServer() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String serverResponse = bufferedReader.readLine();
//            bufferedReader.close();
            System.out.println("I received the following message from the server: " + serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
//        } finally {
//            try {
////                clientSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    private String readFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.next();
        message += scanner.nextLine();
        return message;
    }
}
