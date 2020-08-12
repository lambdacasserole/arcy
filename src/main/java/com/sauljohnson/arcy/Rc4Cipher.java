package com.sauljohnson.arcy;

/**
 * Provides RC4 encryption/decryption.
 *
 * @version 1.0 04 June 2016
 * @author Saul Johnson, Alex Mullen, Lee Oliver
 */
public class Rc4Cipher {

    /** Holds the state used for the pseudo-random number generator. */
    private int[] state;

    /** Holds the key used to initialise the state. */
    private int[] key;

    /**
     * Initialises a new instance of an RC4 encryption algorithm.
     */
    public Rc4Cipher(int[] key) {
        state = new int[256];
        this.key = key;
        ksa();
    }

    /**
     * Swaps the integers at two indices in the state permutation.
     *
     * @param i the index of the first integer
     * @param j the index of the second integer
     */
    private void swap(int i, int j) {
        int temp = state[i];
        state[i] = state[j];
        state[j] = temp;
    }

    /**
     * The key-scheduling algorithm initialises the state according to the key.
     */
    private void ksa() {
        // Initialise with bytes 0-255.
        for (int i = 0; i < state.length; i++) {
            state[i] = i;
        }

        // Permute the state, mixing in the key.
        int j = 0;
        for (int i = 0; i < state.length; i++) {
            j = (j + state[i] + key[i % key.length]) % 256;
            swap(i, j);
        }
    }

    private int prga_i = 0;
    private int prga_j = 0;

    /**
     * The pseudo-random generation algorithm fills a buffer array with a
     * pseudo-random key stream.
     *
     * @param buffer    the array to fill with the key stream
     */
    private void prga(int[] buffer) {
        // Fill buffer with pseudo-random key stream.
        for (int c = 0; c < buffer.length; c++) {
            prga_i = (prga_i + 1) % 256;
            prga_j = (prga_j + state[prga_i]) % 256;
            swap(prga_i, prga_j);
            buffer[c] = state[(state[prga_i] + state[prga_j]) % 256];
        }
    }


    /**
     * Gets the key the algorithm is using to encrypt/decrypt data.
     * @return  the key being used to encrypt/decrypt data
     */
    public int[] getKey() {
        return key;
    }

    /**
     * Returns an encrypted version of the specified byte array.
     * @param plaintext the plaintext to encrypt
     * @return  the encrypted data
     */
    public int[] encrypt(int[] plaintext) {
        // Get key stream.
        int[] keystream = new int[plaintext.length];
        prga(keystream);

        // Encrypt data.
        int[] ciphertext = new int[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = plaintext[i] ^ keystream[i];
        }
        return ciphertext;
    }

    /**
     * Returns a decrypted version of the specified byte array.
     * @param ciphertext the ciphertext to decrypt
     * @return  the decrypted data
     */
    public int[] decrypt(int[] ciphertext) {
        // This encryption is invertible.
        return encrypt(ciphertext);
    }
}
