package com.sauljohnson.arcy;

import junit.framework.TestCase;

/**
 * Unit tests for the {@link Rc4Cipher} class.
 *
 * @version 1.0 04 June 2016
 * @author  Saul Johnson
 */
public class Rc4CipherTests extends TestCase {

    /** An array of keys to test. */
    private int[][] keys;

    /** An array of expected corresponding key streams. */
    private int[][] keyStreams;

    public Rc4CipherTests() {
        // Array of key strings.
        String[] keyStrings = new String[]
                {
                        "00000000000000000000000000000000",
                        "ffffffffffffffffffffffffffffffff",
                        "01010101010101010101010101010101",
                        "d764c8cce93255c4478d7aa05d83f3ea",
                        "2a83e82681a22df7a04329387f7f2cd5",
                        "e38248bb72dd1350b2994e61ad0f9509",
                        "b540a80fbb1c787c244e0e189ee9deff",
                        "993a6e57d103ef8affc253c841689fd2"
                };

        // Turn key strings into bytes.
        keys = new int[keyStrings.length][];
        for (int i = 0; i < keyStrings.length; i++) {
            keys[i] = toByteArray(keyStrings[i]);
        }

        // Array of expected key streams (first 16 bytes).
        String[] keyStreamStrings = new String[]
                {
                        "de188941a3375d3a8a061e67576e926d",
                        "6d252f2470531bb0394b93b4c46fdd9c",
                        "06080e0e182029293933495766768783",
                        "c86e2d580e675554423642f33a6468e9",
                        "960c0b913786a18411b0e9e4a7499bbc",
                        "6cca71a62bb276d9d9ebc853970fe9fe",
                        "50a75801240ece2030f43a90e55319d6",
                        "45b1ddd8eb16da566c435742f3370d43"
                };

        // Turn key stream strings into bytes.
        keyStreams = new int[keyStreamStrings.length][];
        for (int i = 0; i < keyStreamStrings.length; i++) {
            keyStreams[i] = toByteArray(keyStreamStrings[i]);
        }
    }

    /**
     * Converts a hexadecimal string to a byte array.
     * @param hex   the hexadecimal string to convert
     * @return  the resulting byte array
     */
    private static int[] toByteArray(String hex) {
        int len = hex.length();
        int[] data = new int[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i+1), 16);
        }
        return data;
    }

    /**
     * Gets an array of zeroes of a given length.
     * @param len   the length of the array to generate
     * @return  an array of zeroes of the specified length
     */
    private static int[] getZeroes(int len) {
        int[] res = new int[len];
        for (int i = 0; i < res.length; i++) {
            res[i] = 0;
        }
        return res;
    }

    public void testGetKey() {
        // Initialize cipher with key.
        int[] key = new int[] {255, 255, 255};
        Rc4Cipher subject = new Rc4Cipher(key);

        // Cipher instance should pass back original key.
        assertEquals(key, subject.getKey());
    }

    public void testEncrypt() {
        for (int i = 0; i < keys.length; i++) {
            // Initialize cipher with key.
            int[] key = keys[i];
            Rc4Cipher subject = new Rc4Cipher(key);

            // Encrypt array of zeroes.
            int[] actual = subject.encrypt(getZeroes(16));
            int[] expected = keyStreams[i];

            // Length of plain text and cipher text should be identical.
            assertEquals(expected.length, actual.length);

            // Cipher text should match expected.
            for (int j = 0; j < actual.length; j++) {
                assertEquals(expected[j], actual[j]);
            }
        }
    }

    public void testDecrypt() {
        for (int i = 0; i < keys.length; i++) {
            // Initialize cipher with key.
            int[] key = keys[i];
            Rc4Cipher subject = new Rc4Cipher(key);

            // Decrypt encrypted array of zeroes.
            int[] actual = subject.decrypt(keyStreams[i]);
            int[] expected = getZeroes(16);

            // Length of plain text and cipher text should be identical.
            assertEquals(expected.length, actual.length);

            // Plain text should match expected.
            for (int j = 0; j < actual.length; j++) {
                assertEquals(expected[j], actual[j]);
            }
        }
    }
}
