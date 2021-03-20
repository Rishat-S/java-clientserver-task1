package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Constants.PORT);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {
                String msg;
                while ((msg = in.readLine()) != null) {
                    if (msg.equals("end")) {
                        break;
                    }
                    int number = Integer.parseInt(msg);
                    System.out.println("Integer received from client: " + number);
                    BigInteger fibonacciNumber = Fibonacci.get(number);
                    out.println(fibonacciNumber);
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
