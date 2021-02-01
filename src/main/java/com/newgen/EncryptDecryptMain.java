package com.newgen;

import java.util.Scanner;

public class EncryptDecryptMain {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter the text to encrypt");
        Scanner sc = new Scanner(System.in);

        String rawText = sc.nextLine();
        System.out.println("input text --> '" + rawText + "'");

        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
        String encryptedText = encryptDecrypt.encrypt(rawText);

        System.out.println("encryptedText --> \n" + encryptedText + "\n");

        System.out.println("press a key to view decrypted text");

        sc.nextLine();

        String decryptedText = encryptDecrypt.decrypt(encryptedText);
        System.out.println("decryptedText --> \n" + decryptedText + "\n");
    }
}
