package org.alpacology.sha3demo;

import org.bouncycastle.crypto.digests.SHAKEDigest;

import java.math.BigInteger;
import java.util.Stack;
import java.util.stream.Collectors;

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
        BigInteger number = new BigInteger(1, bytes);

        Stack<Integer> numberWithCorrectRadix = convertNumberToRadix(number, ALPHABET.length());

        return stackToAlphabet(numberWithCorrectRadix);
    }

    private static String stackToAlphabet(Stack<Integer> numberWithCorrectRadix) {
        return numberWithCorrectRadix.stream()
                .map(digit -> ALPHABET.substring(digit, digit + 1))
                .collect(Collectors.joining());
    }

    private static Stack<Integer> convertNumberToRadix(BigInteger number, int radix) {
        Stack<Integer> stack = new Stack<>();
        do {
            BigInteger[] result = number.divideAndRemainder(
                    BigInteger.valueOf(radix)
            );
            stack.push(result[1].intValue()); // remainder
            number = result[0]; // division result
        } while (!number.equals(BigInteger.ZERO));

        return stack;
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
