package com.example.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileOutputStream;

public class Extractor {

    private File imgFile, trgtDir;
    private Cryptography cryptography;

    public Extractor(String imgFile, String trgtDir, String password) throws Exception {
        this.imgFile = new File(imgFile);
        this.trgtDir = new File(trgtDir);

        if(!this.imgFile.exists())
            throw new Exception(imgFile + " doesn't exist");

        if(!this.trgtDir.exists())
            this.trgtDir.mkdirs();
        else {
            if(!this.trgtDir.isDirectory())
                throw new Exception(trgtDir + " is not a directory");
        }

        this.cryptography = new Cryptography(password);
    }

    public void extract() throws Exception {
        //load the image in memory
        BufferedImage vessel = ImageIO.read(imgFile);

        //retrieve the pixel matrix
        Raster raster = vessel.getData();

        int w = raster.getWidth();
        int h = raster.getHeight();
        int r, g, b, data;
        long x = 0, need = HeaderManager.HEADER_SIZE;
        StringBuilder header = new StringBuilder();
        FileOutputStream fout = null;
        String fileName = null;

        for(int j=0; j<h && x <=need; j++) {
            for(int i=0; i<w && x<=need; i++) {
                //per pixel, extract the bands
                r = raster.getSample(i, j, 0);  //get red band of pixel i,j
                g = raster.getSample(i, j, 1);  //get green band of pixel i,j
                b = raster.getSample(i, j, 2);  //get blue band of pixel i,j

                //extract bits and merge to form the byte
                r = ByteManager.extract(r, 1);
                g = ByteManager.extract(g, 1);
                b = ByteManager.extract(b, 0);
                data = ByteManager.mergeBits(r, g, b);

                //decrypt
                data = cryptography.decrypt(data);

                if(x < HeaderManager.HEADER_SIZE)
                    header.append((char)data);
                else if(x == HeaderManager.HEADER_SIZE) {
                    //header is ready
                    String arr[] = HeaderManager.extract(header.toString());
                    fileName = arr[0];
                    long fileSize = Long.parseLong(arr[1]);
                    need += fileSize - 1;
                    fout = new FileOutputStream(trgtDir.getAbsolutePath() + "/" + fileName);
                }

                if(fileName != null)
                    fout.write(data);

                x++;
            }//for i
        }//for j

        fout.flush();   //buffered data is transferred onto the disk
        fout.close();
    }
}
