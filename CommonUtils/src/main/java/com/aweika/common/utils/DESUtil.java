package com.aweika.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author: zh
 * @date: 2020/3/28
 * @description: DES加密解密
 */
public class DESUtil {
    /**
     * 加密解密方式
     */
    private static final String DES = "DES";
    /**
     * 密钥key
     */
    public static final String DES_KEY = "XS2020YS";

    public static String doEncrypt(String plainMessage, String hexDesKey) {
        try {
            byte desKey[] = hexDesKey.getBytes();
            byte desPlainMsg[] = plainMessage.getBytes();
            return Base64.encodeBase64URLSafeString(desCrypt(desKey, desPlainMsg, Cipher.ENCRYPT_MODE));
        } catch (Exception e) {
            return null;
        }

    }

    public static String doDecrypt(String hexEncryptMessage, String hexDesKey) {
        try {
            if (hexEncryptMessage == null) {
                return null;
            }
            byte desKey[] = hexDesKey.getBytes();
            byte desPlainMsg[] = Base64.decodeBase64(hexEncryptMessage);
            return new String(desCrypt(desKey, desPlainMsg, Cipher.DECRYPT_MODE));
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] desCrypt(byte[] desKey, byte[] desPlainMsg, int CipherMode) throws Exception {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(desKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            javax.crypto.SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(CipherMode, key, sr);

            return cipher.doFinal(desPlainMsg);
        } catch (Exception e) {
            String message = "";
            if (CipherMode == Cipher.ENCRYPT_MODE) {
                message = "DES\u52A0\u5BC6\u5931\u8D25";
            } else {
                message = "DES\u89E3\u5BC6\u5931\u8D25";
            }
            throw new Exception(message, e);
        }
    }

    public static void main(String[] args) {
        String value = "91331082683120051T";
        System.out.println("未加密前的明文:" + value);
        String encryption = doEncrypt(value, DES_KEY);
        System.out.println("加密后的密文是:" + encryption);
        String decrypt = doDecrypt(encryption, DES_KEY);
        System.out.println("解密后的明文是:" + decrypt);

    }

}
