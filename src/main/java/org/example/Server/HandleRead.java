package org.example.Server;

import org.example.Basic_comm.CheckRegistration;
import org.example.Basic_comm.Execute;
import org.example.Basic_comm.Registration;
import org.example.CitiesPackage.CityManager;
import org.example.Examination.IsInt;
import org.example.ProcessingRequests.InputData;
import org.example.ProcessingRequests.Serializer;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static org.example.Server.SendResponse.sendResponse;

public class HandleRead {
    private static final ExecutorService readerPool = Executors.newFixedThreadPool(10);
    private static final ForkJoinPool requestPool = new ForkJoinPool(); // Пул потоков для обработки запросов

    protected static void handleRead(SelectionKey key) throws IOException {
        readerPool.submit(() -> {
            SocketChannel clientChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = null;
            try {
                buffer = ByteBuffer.allocate(clientChannel.socket().getReceiveBufferSize());
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            try {
                int bytesRead = clientChannel.read(buffer);
                if (bytesRead == -1) {
                    clientChannel.close();
                    System.out.println("Client disconnected");
                    return;
                }

                // Ensure the buffer is fully read
                while (bytesRead > 0) {
                    buffer.flip();
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);

                    // Отправляем задачу на обработку в ForkJoinPool
                    requestPool.submit(() -> {
                        try {
                            // Пытаемся десериализовать объект Response из полученных данных
                            InputData response = Serializer.deserializeInputData(data);
                            if (response.getregistration()) {
                                // Обрабатываем объект Response
                                if (response.getCity() != null && response.getMessage().equals("add")) {
                                    response.getCity().setClinet_id(Registration.getId());
                                    CityManager.getCollection().add(response.getCity());
                                    System.out.println("Received: " + response.getMessage());
                                    sendResponse(clientChannel, "Город " + response.getCity().getName() + " успешно  обработан.");
                                } else if (response.getMessage().equals("insert_at")) {
                                    if (response.getnum() > -1) {
                                        if (response.getnum() <= CityManager.getCollection().size()) {
                                            CityManager.getCollection().add(response.getnum(), response.getCity());
                                            System.out.println("Received: " + response.getMessage());
                                            sendResponse(clientChannel, "Элемент добавлен");
                                        } else {
                                            System.out.println("Received: " + response.getMessage());
                                            sendResponse(clientChannel, "Превышен размер коллекции");
                                        }
                                    } else {
                                        sendResponse(clientChannel, "Введены некоректные данные");
                                    }
                                } else {
                                    // Если объект City не был получен, обрабатываем текстовое сообщение
                                    System.out.println("Received: " + response.getMessage());
                                    sendResponse(clientChannel, new Execute().executeCommand(response.getMessage()));
                                }
                            } else {
                                System.out.println("Received: " + response.getMessage());
                                sendResponse(clientChannel, Registration.get(response.getUsername(), response.getPassword(), response.getMessage()));
                                Registration.setID(response.getUsername());

                            }
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    });

                    buffer.clear();
                    bytesRead = clientChannel.read(buffer);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
