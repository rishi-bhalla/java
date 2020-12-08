package main.java;

import main.java.cryptography.Cryptography;
import main.java.utils.ByteManager;
import main.java.utils.HeaderManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * This class is responsible for reading the embedded file and the path of the output directory and perform the extraction of the data/file from the embedded image.
 * Before finally extracting the data, it decrypts the embedded data to regain the original data.
 */
public class Extractor {

    private File imgFile, trgtDir;
    private Cryptography cryptography;

    public Extractor(String imgFile, String trgtDir, String password) throws Exception {
        this.imgFile = new File(imgFile);
        this.trgtDir = new File(trgtDir);

        if(!this.imgFile.exists())
            throw  new Exception(imgFile + " doesnt exist");
        if(!this.trgtDir.exists())
            this.trgtDir.mkdirs();
        else {
            if(!this.trgtDir.isDirectory())
                throw new Exception(trgtDir + " is not a directory");
        }

        this.cryptography = new Cryptography(password);
    }

    /**
     * This method contains the steps/algorithm to extract the original data/file from the embedded image.
     *
     * @throws Exception
     */
    public void extract() throws Exception {
        //load the image in memory
        BufferedImage vessel = ImageIO.read(imgFile);

        //retrieve the pixel matric
        Raster raster = vessel.getData();

        int w = vessel.getWidth();
        int h = vessel.getHeight();
        long x = 0, need = HeaderManager.HEADER_SIZE;
        int r, g, b, data;
        StringBuilder header = new StringBuilder();
        String fileName = null;
        FileOutputStream fout = null;

        //extract
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
                    header.append((char) data);
                else if(x == HeaderManager.HEADER_SIZE) {
                    //header is ready
                    String arr[] = HeaderManager.extractHeader(header.toString());
                    fileName = arr[0];
                    long fileSize = Long.parseLong(arr[1]);
                    need += fileSize - 1;
                    fout = new FileOutputStream(trgtDir.getAbsolutePath() + "/" + fileName);
                }

                if(fileName != null)
                    fout.write(data);

                //counter update
                x++;
            }//for i
        }//for j

        //buffered data is transffered onto the disk
        fout.flush();

        //close file
        fout.close();
    }
}
