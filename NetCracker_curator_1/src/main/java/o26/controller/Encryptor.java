package o26.controller;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class Encryptor {

    private static final byte[] KEY = "MyDifficultPassw".getBytes();
    private static final String TRANSFORMATION = "AES";

    private Encryptor() {

    }

    public static void encrypt(Serializable object, OutputStream outStream) throws IOException,
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        try {
            SecretKeySpec sks = new SecretKeySpec(KEY, TRANSFORMATION);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            SealedObject sealedObject = new SealedObject(object, cipher);

            CipherOutputStream cos = new CipherOutputStream(outStream, cipher);
            ObjectOutputStream outputStream = new ObjectOutputStream(cos);
            outputStream.writeObject(sealedObject);
            outputStream.close();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
