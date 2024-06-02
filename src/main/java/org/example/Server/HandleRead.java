package org.example.Server;



import org.example.Basic_comm.Execute;
import org.example.CitiesPackage.CityManager;
import org.example.ProcessingRequests.Response;
import org.example.ProcessingRequests.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import static org.example.Server.SendResponse.sendResponse;

public class HandleRead {
    protected static void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(clientChannel.socket().getReceiveBufferSize());
        buffer.clear();
        int bytesRead = clientChannel.read(buffer);
        if (bytesRead == -1) {
            clientChannel.close();
            System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
            return;
        }
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);

        try {
            // Пытаемся десериализовать объект Response из полученных данных
            Response response = Serializer.deserializeResponse(data);

            // Обрабатываем объект Response
            if (response.getCity() != null & response.getMessage().equals("add")) {
                CityManager.getCollection().add(response.getCity());
                sendResponse(clientChannel, "Город " + response.getCity().getName() + " успешно обработан.");
            }
            if (response.getMessage().equals("insert_at")) {
                CityManager.getCollection().add(response.getnum(), response.getCity());
                sendResponse(clientChannel, "Элемент добавлен");

            }
            else {
                // Если объект City не был получен, обрабатываем текстовое сообщение
                System.out.println("Received: " + response.getMessage());
                sendResponse(clientChannel, new Execute().executeCommand(response.getMessage()));
            }
        } catch (ClassNotFoundException e) {
            // В случае ошибки десериализации выводим сообщение об ошибке
            e.printStackTrace();
        }
    }
}
