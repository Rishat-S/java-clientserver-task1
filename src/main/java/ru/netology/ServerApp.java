package ru.netology;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(Constants.HOSTNAME, Constants.PORT));

        while (true) {
            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

                while (socketChannel.isConnected()) {
                    int byteCount = socketChannel.read(inputBuffer);
                    if (byteCount == -1) {
                        break;
                    }
                    final String msg = new String(inputBuffer.array(), 0, byteCount,
                            StandardCharsets.UTF_8);
                    inputBuffer.clear();

                    int number = Integer.parseInt(msg);
                    System.out.println("Integer received from client: " + number);
                    BigInteger fibonacciNumber = Fibonacci.get(number);
                    System.out.println("Fibonacci Number value: " + fibonacciNumber);
                    socketChannel.write(ByteBuffer.wrap(("Fibonacci Number value: " + fibonacciNumber).getBytes(StandardCharsets.UTF_8)));
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
