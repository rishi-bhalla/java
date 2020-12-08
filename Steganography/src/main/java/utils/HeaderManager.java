package main.java.utils;

/**
 * This class is used to generate and extract the header which forms the initial 25 bytes of the embedded image.
 * This header contains the name of the file being embedded and its size. The name of the file and its size are padded with extra "#" in order to
 * maintain the length specified for the each. File name and its size must be of 15 and 10 bytes respectively.
 */
public class HeaderManager {

    private static final int FILENAME_SIZE = 15;
    private static final int FILESIZE_SIZE = 10;
    public static final int HEADER_SIZE = 25;

    /**
     * This method is used to process the name of the file in order to keep its length to 15 bytes.
     * The extra characters are sliced or additional padding with "#" is added in case the length is less or more than the desired length.
     *
     * @param fileName
     * @return
     */
    private static String processFileName(String fileName) {
        if(fileName.length() == FILENAME_SIZE )
            return fileName;
        else if(fileName.length() > FILENAME_SIZE) {
            int pos = fileName.lastIndexOf(".");
            if(pos == -1)
                return fileName.substring(0, FILENAME_SIZE);
            else {
                String name = fileName.substring(0, pos);
                String extension = fileName.substring(pos);
                name = name.substring(0, FILENAME_SIZE - extension.length());
                return name + extension;
            }
        } else { //fileName.length() < FILENAME_SIZE
            StringBuilder stringBuilder = new StringBuilder(fileName);
            while(stringBuilder.length() < FILENAME_SIZE)
                stringBuilder.append("#");
            return stringBuilder.toString();
        }
    }

    /**
     * This method is used to process the size of the file in order to keep its length to 10 bytes.
     * The extra characters are sliced or additional padding with "#" is added in case the length is less or more than the desired length.
     *
     * @param fileSize
     * @return
     * @throws Exception
     */
    private static String processFileSize(long fileSize) throws Exception {
        String fs = String.valueOf(fileSize);
        if(fs.length() == FILESIZE_SIZE)
            return fs;
        else if (fs.length() > FILESIZE_SIZE) //raise exception
            throw new Exception("File size " + fs + " is beyond embedding capacity");
        else { //fs.length() < FILESIZE_SIZE
            StringBuilder stringBuilder = new StringBuilder(fs);
            while(stringBuilder.length() < FILESIZE_SIZE)
                stringBuilder.append("#");
            return stringBuilder.toString();
        }
    }

    /**
     * This method is used to generate the header of length 25 bytes, containing the name of the file (15 bytes) and its size (10 bytes).
     * This method internally gives call to the respective file name and file size processing methods while embedding the data into the source image.
     *
     * @param fileName
     * @param fileSize
     * @return
     * @throws Exception
     */
    public static String generateHeader(String fileName, long fileSize) throws Exception {
        return processFileName(fileName) + processFileSize(fileSize);
    }

    /**
     * This method is used to extract the header and obtain the name of the file and its size while extraction of the data/file from the embedded image.
     * The additional padding of "#" is removed in case it is present to get the actual name of the file and its size.
     *
     * @param header
     * @return
     */
    public static String[] extractHeader(String header) {
        String arr[] = new String[2];
        String fileName = header.substring(0, FILENAME_SIZE);
        arr[0] = fileName.replaceAll("#", "");
        String fileSize = header.substring(FILENAME_SIZE);
        arr[1] = fileSize.replaceAll("#", "");
        return arr;
    }
}
