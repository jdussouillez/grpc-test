package com.github.jdussouillez;

import com.google.protobuf.ByteString;
import java.io.InputStream;

public class Main {

    private static final int CHUNK_SIZE = 4096;

    public static void main(String[] args) throws Exception {
        try (InputStream is = Main.class.getResourceAsStream("image.jpg")) {
            int nbChunks = (int) Math.ceil(is.available() / CHUNK_SIZE);
            System.out.println(
                "Bytes available in stream: " + is.available()
                + ", so there should be " + nbChunks + " chunks"
            );
            ByteString byteString;
            int cpt = 0;
            do {
                byteString = ByteString.readFrom(is, CHUNK_SIZE);
                if (byteString != null) {
                    System.out.println("byteString size: " + byteString.size());
                    System.out.println("Bytes available in stream: " + is.available());
                    // TODO: do nothing with byteString
                }
                cpt++;
                if (cpt > nbChunks + 1) {
                    System.err.println("Stop before infinite loop");
                    System.exit(1);
                }
            } while (byteString != null);
        }
    }
}
