package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class handleSavedData{
    public static String retrieveFile(String path){
        try {
            // Read file content into a string
            String content = new String(Files.readAllBytes(Paths.get(path)));
            return decrypt(content);
        }
        catch(Exception e){
            /*blank*/ return "";
        }
    }
    public static boolean fileMake(String path, String info) {
        try {
            FileWriter fw = new FileWriter(path);
            String newInfo = encrypt(info);
            fw.write(newInfo);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    /// Does just after retrieving from file
    public static String decrypt(String txt){
        String key = "ssonsezent";
        String newtxt = VigenereDec(txt, key);
        String newtxt2 = new String(Base64.getDecoder().decode(newtxt.getBytes()));
        String key2 = "isisitissonsissezissent";
        return VigenereDec(newtxt2, key2);
    }
    /// Does just before writing to file
    public static String encrypt(String txt){
        String key = "isisitissonsissezissent";
        String newtxt = VigenereEnc(txt, key);
        String newtxt2 = new String(Base64.getEncoder().encode(newtxt.getBytes()));
        String key2 = "ssonsezent";
        return VigenereEnc(newtxt2, key2);
    }
    /// Encode
    public static String VigenereEnc(String plaintext, String keyword) {StringBuilder ciphertext = new StringBuilder();
        keyword = keyword.toUpperCase(); plaintext = plaintext.toUpperCase();
        for (int i = 0, j = 0; i < plaintext.length(); i++) {char letter = plaintext.charAt(i);
            if (!(letter < 'A' || letter > 'Z')) {char shift = keyword.charAt(j % keyword.length());
                char encryptedLetter = (char)((letter + shift - 2 * 'A') % 26 + 'A');
                ciphertext.append(encryptedLetter);
                j++;}
            else ciphertext.append(letter);
        } return ciphertext.toString();
    }
    /// Decode
    public static String VigenereDec(String ciphertext, String keyword) {StringBuilder plaintext = new StringBuilder();
        keyword = keyword.toUpperCase(); ciphertext = ciphertext.toUpperCase();
        for (int i = 0, j = 0; i < ciphertext.length(); i++) {char letter = ciphertext.charAt(i);
            if (!(letter < 'A' || letter > 'Z')) {char shift = keyword.charAt(j % keyword.length());
                char decryptedLetter = (char)((letter - shift + 26) % 26 + 'A');
                plaintext.append(decryptedLetter); j++;}
            else plaintext.append(letter);
        } return plaintext.toString();
    }
}
