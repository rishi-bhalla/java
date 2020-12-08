This is a project on steganography where one can embed/hide their files (which can be text, audio, video, zip, etc) inside an image and send them to the concerned party where they can retrieve the embedded/hidden file from the received image.

The data of the file being embedded in the image is encrypted before embedding to make sure an additional layer to security is provided. It could have been embedded without encryption as well.

Details of steganography and the approach followed for this project are present under the resources directory explaining the algorithm step by step.

The source file (srcImg.png) and the file to be embedded (fileToEmbed.png) are present in the /resources directory. Once you run the main file i.e. Steganography.java, the embedded image will be created inside the /resources/data directory by the name "trgtImg.png" and the original file will be generated/regained from this "trgtImg.png" and placed  under the /resources/data/output directory with the same name as the original file i.e. fileToEmbed.png.

During embedding, the initial 25 bytes i.e. the header, contain the name of the file being embedded and its size. Accordingly the algorithm extracts the data/file to regain the original file.

You can alter the paths of the images in the main file i.e. Steganography.java.


Rishi Bhalla
