package com.example.steganography;

public class ByteManager {

    public static int[] splitByte(int data) {
        int arr[] = new int[3];
        arr[0] = data >> 5;
        arr[1] = (data & 0x1C) >> 2;    //0x1C : 11100
        arr[2] = data & 0x3;            //0x3 : 11
        return arr;
    }

    public static int embed(int data, int toEmbed, int flag) {
        return (flag == 1) ? (data & ~0x7) | toEmbed : (data & ~0x3) | toEmbed;
    }

    public static int extract(int data, int flag) {
        return (flag == 1) ? data & 0x7 : data & 0x3;
    }

    public static int mergeBits(int x, int y, int z) {
        return (((x << 3) | y) << 2) | z;
    }
}
