package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(Constants.HOSTNAME, Constants.PORT);
        try (BufferedReader in = new BufferedReader((
                new InputStreamReader(socket.getInputStream())));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            while (true) {
                System.out.println("Enter integer number:");
                msg = scanner.nextLine();
                if ("end".equals(msg)) {
                    break;
                }
                try {
                    int number = Integer.parseInt(msg);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    continue;
                }
                out.println(msg);
                System.out.println("Fibonacci Number value: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

