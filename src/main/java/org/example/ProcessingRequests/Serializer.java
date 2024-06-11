package org.example.ProcessingRequests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public final class Serializer {

    private Serializer() {
        // Prevent instantiation
    }

    public static InputData deserializeInputData(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return (InputData) objectInputStream.readObject();
        }
    }

    public static ByteBuffer serializeInputData(InputData response) throws IOException {
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bytes)) {
            oos.writeObject(response);
            oos.flush();
            return ByteBuffer.wrap(bytes.toByteArray());
        }
    }
}
