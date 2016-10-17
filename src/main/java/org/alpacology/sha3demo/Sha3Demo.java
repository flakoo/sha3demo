package org.alpacology.sha3demo;

import org.bouncycastle.crypto.digests.SHAKEDigest;

public class Sha3Demo {
    public static final String INPUT ="asDge123FtEBlahNeedsMoreL3tter5";
    public static final String ALPHABET = "AaBbCcDdEeFfHhKkMmOoPpRrTtXxYy34689";
    public static final int OUTPUT_STRING_LENGTH = 7;


    public static void main(String[] args) {
        SHAKEDigest shakeDigest = new SHAKEDigest();
        byte[] outputBytes = new byte[getByteLength()];

        shakeDigest.update(INPUT.getBytes(), 0, INPUT.getBytes().length);
        shakeDigest.doFinal(outputBytes, 0, getByteLength());

        System.out.println("HASH: " + getOutputBytesAsString(outputBytes) + " / length: " + outputBytes.length);
    }

    private static String getOutputBytesAsString(byte[] bytes) {
        return new String(bytes);
    }

    public static int getByteLength() {
        int length = 0;
        do {
            length++;
        } while(getTotalNumberOfCombinationsForByteLength(length) < getTotalNumberOfHashCombinationsForAlphabet());

        return --length;
    }

    private static long getTotalNumberOfCombinationsForByteLength(int length) {
        return Math.round(Math.pow(
                Double.valueOf(256),
                Double.valueOf(length)
        ));
    }

    private static long getTotalNumberOfHashCombinationsForAlphabet() {
        return Math.round(Math.pow(
                Double.valueOf(ALPHABET.length()),
                Double.valueOf(OUTPUT_STRING_LENGTH)
        ));
    }
}
