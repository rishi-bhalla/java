package com.example.steganography;

public class Steganography {
    public static void main(String args[]) {
        try {
            String password = "This is a strong password";
            Embedder emb = new Embedder("/home/balla/tomtom/myworkspace/java/MyDemos/src/main/java/com/example/steganography/srcImg.png",
                    "/home/balla/tomtom/myworkspace/java/MyDemos/src/main/java/com/example/steganography/fileToEmbed.png",
                    "/home/balla/tomtom/myworkspace/java/MyDemos/src/main/java/com/example/steganography/trgtImg.png",
                    password);
            emb.embed();
            System.out.println("Embedding success");

            Extractor ext = new Extractor("/home/balla/tomtom/myworkspace/java/MyDemos/src/main/java/com/example/steganography/trgtImg.png",
                    "/home/balla/tomtom/myworkspace",
                    password);
            ext.extract();
            System.out.println("Extraction success");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
