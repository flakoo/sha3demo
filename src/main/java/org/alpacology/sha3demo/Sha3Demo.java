package org.alpacology.sha3demo;

import org.bouncycastle.crypto.digests.SHAKEDigest;

public class Sha3Demo {
    public static final String INPUT ="asDge123FtEBlahNeedsMoreL3tter5";
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
       return OUTPUT_STRING_LENGTH;
    }
}
