package main.java.cryptography;

/**
 * This class is responsible to encryption and decryption of the data just before embedding and extraction to add another layer of security.
 * The encryption and decryption uses the simple XOR mechanism.
 *
 * TODO: This class could be made static or singleton for efficiency. Accordingly the Embedder and the Extractor need to be updated to accommodate the changes.
 */
public class Cryptography {

    private String password;
    private int index;
    private int passwordLength;

    public Cryptography(String password) {
        this.password = password;
        this.index =  -1;
        this.passwordLength = this.password.length();
    }

    /**
     * This method is used to encrypt the data before embedding.
     * Encryption used the XOR mechanism.
     *
     * @param data
     * @return
     */
    public int encrypt(int data) {
        index = (index + 1) % passwordLength;
        return data ^ password.charAt(index);
    }

    /**
     * This method is used to decrypt the data before final extraction.
     * Extraction uses the XOR mechanism.
     *
     * @param data
     * @return
     */
    public int decrypt(int data) {
        index = (index + 1) % passwordLength;
        return data ^ password.charAt(index);
    }
}
