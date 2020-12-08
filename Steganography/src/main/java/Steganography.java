package main.java;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is the main class responsible for triggering the embedding and the extraction process of the steganography project.
 */
public class Steganography {

    public static void main(String args[]) {
        try {
            String password = "This is a strong password";

            Path resourcesDir = Paths.get("src", "main", "resources", "data");
            String resourcesDirPath = resourcesDir.toFile().getPath();

            Embedder emb = new Embedder(resourcesDirPath + "/srcImg.png", resourcesDirPath + "/fileToEmbed.png",
                    resourcesDirPath + "/trgtImg.png", password);
            emb.embed();
            System.out.println("Embedding successful");

            Extractor ext = new Extractor(resourcesDirPath + "/trgtImg.png", resourcesDirPath + "/output", password);
            ext.extract();
            System.out.println("Extraction successful");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
