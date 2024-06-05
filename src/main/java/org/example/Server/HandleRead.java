package org.example.Server;



import org.example.Basic_comm.CheckRegistration;
import org.example.Basic_comm.Execute;
import org.example.Basic_comm.Registration;
import org.example.CitiesPackage.CityManager;
import org.example.ProcessingRequests.Response;
import org.example.ProcessingRequests.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static org.example.Server.SendResponse.sendResponse;

public class HandleRead {
    private static final ExecutorService readerPool = Executors.newFixedThreadPool(10);
    private static final ForkJoinPool requestPool = new ForkJoinPool(); // Пул потоков для обработки запросов
    protected static void handleRead(SelectionKey key) throws IOException {
//        readerPool.submit(() -> {
            SocketChannel clientChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            try {
                int bytesRead = clientChannel.read(buffer);
                if (bytesRead == -1) {
                    clientChannel.close();
                    System.out.println("Client disconnected");
                    return;
                }
                buffer.flip();
                byte[] data = new byte[buffer.limit()];
                buffer.get(data);
                // Отправляем задачу на обработку в ForkJoinPool
//                requestPool.submit(() -> {
                    try {
                        // Пытаемся десериализовать объект Response из полученных данных
                        Response response = Serializer.deserializeResponse(data);
                        if (CheckRegistration.getReg() == true) {

                            // Обрабатываем объект Response
                            if (response.getCity() != null & response.getMessage().equals("add")) {
                                CityManager.getCollection().add(response.getCity());
                                sendResponse(clientChannel, "Город " + response.getCity().getName() + " успешно обработан.");
                            }
                            if (response.getMessage().equals("insert_at")) {
                                CityManager.getCollection().add(response.getnum(), response.getCity());
                                sendResponse(clientChannel, "Элемент добавлен");

                            }
                            if (!response.getUsername().equals("")) {

                            } else {
                                // Если объект City не был получен, обрабатываем текстовое сообщение
                                System.out.println("Received: " + response.getMessage());
                                sendResponse(clientChannel, new Execute().executeCommand(response.getMessage()));
                            }
                        }
                        else {
                            sendResponse(clientChannel, new Registration().get(response.getUsername(), response.getPassword(), response.getMessage()));
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
//                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//        });
    }
}
