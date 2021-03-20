package ru.netology;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(Constants.HOSTNAME, Constants.PORT);
        final SocketChannel socketChannel = SocketChannel.open();

        try (socketChannel; Scanner scanner = new Scanner(System.in)) {
            socketChannel.connect(socketAddress);
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

            while (true) {
                System.out.println("Enter integer number:");
                String  msg = scanner.nextLine();
                try {
                    int number = Integer.parseInt(msg);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    continue;
                }
                if ("end".equals(msg)) {
                    break;
                }

                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                TimeUnit.SECONDS.sleep(Constants.TIMEOUT);

                int bytesCount = socketChannel.read(inputBuffer);
                System.out.println(new String(inputBuffer.array(), 0, bytesCount,
                        StandardCharsets.UTF_8).trim());
                inputBuffer.clear();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

