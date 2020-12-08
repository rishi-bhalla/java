package main.java.utils;

/**
 * This class is responsible for manipulating the bits of the data while embedding and extracting the data/file inside/from the image.
 * The approach/algorithm used for the byte manipulation is described in detail under the /resources directory.
 */
public class ByteManager {

    /**
     * This method is used to split the input byte into three parts of 3, 3 and 2 bits respectively.
     * This splitting into three bits is done for each band red, green and blue of each pixel.
     *
     * @param data
     * @return
     */
    public static int[] splitByte(int data) {
        int arr[] = new int[3];
        arr[0] = data >> 5;
        arr[1] = (data & 0x1C) >> 2;    //0x1C = 11100
        arr[2] = data & 0x3;            //0x3 = 11
        return arr;
    }

    /**
     * This method is used to embed the bits of data into the bits of the source file.
     * Depending upon the value of the flag (1 or 0), the embedding is performed for three or two bits respectively.
     *
     * @param data
     * @param toEmbed
     * @param flag
     * @return
     */
    public static int embed(int data, int toEmbed, int flag) {
        return (flag == 1) ? (data & ~0x7) | toEmbed : (data & ~0x3) | toEmbed;
    }

    /**
     * This method used to extract the data bits of the file to be embedded from the data bits of the source image file.
     * Depending upon the value of the flag (1 or 0), the extraction is performed for three or two bits respectively.
     *
     * @param data
     * @param flag
     * @return
     */
    public static int extract(int data, int flag) {
        return (flag == 1) ? data & 0x7 : data & 0x3;
    }

    /**
     * This method is used to merge the data bits obtained from the red, green and blue band of each pixel to form the data byte of the file to be embedded.
     * This is done for each pixel of the embedded image for extraction.
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static int mergeBits(int x, int y, int z) {
        return (((x << 3) | y) << 2) | z;
    }
}
