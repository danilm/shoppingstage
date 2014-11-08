package com.seglan.shop.sourcecode;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author igaraizabal
 */
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES
{

    private static String key = "HG58YZ3CR9";

    public static void main(String[] args) throws Exception
    {

        String text = "kjR0GVV0sHjq9YicqgkZq4CfI8Cs0OE7";
        String text2="123450009999999";
        byte[] codedtext = encrypt(text2);
        String hexa="";
        for (int i=0;i<codedtext.length;i++)
        {
            hexa+=bytetoHexa(codedtext[i])+",";
        }
        System.out.println("CIFRADO A HEX: "+hexa);
        String codedBase = Base64Coder.encodeByte(codedtext);
        System.out.println("A BASE 64: " + codedtext);
        System.out.println("*****************");
        byte[] decodedBase = Base64Coder.decode(text);

        hexa="";
        for (int i=0;i<decodedBase.length;i++)
        {
            hexa+=bytetoHexa(decodedBase[i])+",";
        }
        System.out.println("SIN B64 hex: "+hexa);
        String decodedtext = decrypt(decodedBase);
        System.out.println("DESCRIFRADO: " + decodedtext);

        String cc=encryptB64(text2);
        System.out.println("Coded text: " + cc);
        System.out.println("Decoded text: " + decryptB64(cc));
//        System.out.println("Decoded base: " + decodedBase);
//        System.out.println("Decoded text: " + decodedtext);
//        System.out.println("****************");
//        String enc2=encryptB64("cadenadeprueba2");
//        System.out.println("Coded text: " + enc2);
//        System.out.println("****************");
    }

    public static String encryptB64(String text)
    {
        String result = "";
        try
        {
            byte[] codedtext = encrypt(text);
            result = Base64Coder.encodeByte(codedtext);

        }
        catch (Exception ex)
        {
        }
        return result;
    }
    public static String decryptB64(String text)
    {
        String result = "";
        try
        {
            byte[] decodedBase = Base64Coder.decode(text);
            result = decrypt(decodedBase);

        }
        catch (Exception ex)
        {
        }
        return result;
        
    }
    private static char bytetoh (byte h)
 {
  String hexa="0123456789ABCDEF";
  return hexa.charAt (h);
 }
 
 public static String bytetoHexa (byte b)
 {
  byte a;
  
  StringBuffer res=new StringBuffer ();
  
  a=(byte)((b>>4)&0x0F);
  res.append (bytetoh (a));
  a=(byte)(b&0x0F);
  res.append (bytetoh (a));
 
  return res.toString ();
 }
    public static byte[] encrypt(String message) throws Exception
    {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        
        for (int j = 0, k = 16; j < 8;)
        {
            keyBytes[k++] = keyBytes[j++];
        }
        String hexa="";
        for (int i=0;i<keyBytes.length;i++)
        {
            hexa+=bytetoHexa(keyBytes[i])+",";
        }
        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);

        return cipherText;
    }

    public static String decrypt(byte[] message) throws Exception
    {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;)
        {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key, iv);

        final byte[] plainText = decipher.doFinal(message);

        return new String(plainText, "UTF-8");
    }
}
