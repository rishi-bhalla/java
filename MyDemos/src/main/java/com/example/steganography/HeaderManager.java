package com.example.steganography;

public class HeaderManager {

    private static final int FILENAME_SIZE = 15;
    private static final int FILESIZE_SIZE = 10;
    public static final int HEADER_SIZE = 25;

    private static String processFileName(String fileName) {
        if(fileName.length() == FILENAME_SIZE)
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
            while (stringBuilder.length() < FILENAME_SIZE)
                stringBuilder.append("#");
            return stringBuilder.toString();
        }
    }


    private static String processFileSize(long fileSize) throws Exception {
        String fs = String.valueOf(fileSize);
        if(fs.length() == FILESIZE_SIZE)
            return fs;
        else if(fs.length() > FILESIZE_SIZE)    //raise an exception
            throw  new Exception("File size " + fs + " is beyond embedding capacity.");
        else { //fs.length() < FILENAME_SIZE
            StringBuilder stringBuilder = new StringBuilder(fs);
            while (stringBuilder.length() < FILESIZE_SIZE)
                stringBuilder.append("#");
            return stringBuilder.toString();
        }
    }

    public static String generateHeader(String fileName, long fileSize) throws Exception {
        return processFileName(fileName) + processFileSize(fileSize);
    }

    public static String[] extract(String header) {
        String arr[] = new String[2];
        String fileName = header.substring(0, FILENAME_SIZE);
        arr[0] = fileName.replaceAll("#", "");
        String fileSize = header.substring(FILENAME_SIZE);
        arr[1] = fileSize.replaceAll("#", "");
        return arr;
    }
}
