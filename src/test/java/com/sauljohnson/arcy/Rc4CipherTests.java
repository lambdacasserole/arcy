package com.sauljohnson.arcy;

import junit.framework.TestCase;

/**
 * Unit tests for the {@link Rc4Cipher} class.
 *
 * @version 1.0 04 June 2016
 * @author  Saul Johnson
 */
public class Rc4CipherTests extends TestCase {

    public void testGetKey() {
        // Initialize cipher with key.
        int[] key = new int[] {255, 255, 255};
        Rc4Cipher subject = new Rc4Cipher(key);

        // Cipher instance should pass back original key.
        assertEquals(key, subject.getKey());
    }

    public void testEncrypt() {
        // Initialize cipher with key.
        int[] key = new int[] {1, 2, 3};
        Rc4Cipher subject = new Rc4Cipher(key);

        // Encrypt array of zeroes.
        int[] actual = subject.encrypt(new int[] {0, 0, 0, 0, 0});
        int[] expected = new int[] {151, 54, 143, 104, 66};

        // Length of plain text and cipher text should be identical.
        assertEquals(expected.length, actual.length);

        // Cipher text should match expected.
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    public void testDecrypt() {
        // Initialize cipher with key.
        int[] key = new int[] {1, 2, 3};
        Rc4Cipher subject = new Rc4Cipher(key);

        // Decrypt encrypted array of zeroes.
        int[] actual = subject.decrypt(new int[] {151, 54, 143, 104, 66});
        int[] expected = new int[] {0, 0, 0, 0, 0};

        // Length of plain text and cipher text should be identical.
        assertEquals(expected.length, actual.length);

        // Plain text should match expected.
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
