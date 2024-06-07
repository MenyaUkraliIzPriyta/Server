package org.example.Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SendResponse {
    protected static void sendResponse(SocketChannel clientChannel, String response) throws IOException {
        // Отправляем ответ клиенту
        new Thread(() -> {
            ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
            try {
                clientChannel.write(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            buffer.clear();
        }).start();
    }
}