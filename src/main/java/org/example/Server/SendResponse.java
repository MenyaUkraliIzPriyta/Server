package org.example.Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SendResponse {
    protected static void sendResponse(SocketChannel clientChannel, String response) throws IOException {
        // Отправляем ответ клиенту
        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
        clientChannel.write(buffer);
        buffer.clear();
    }
}