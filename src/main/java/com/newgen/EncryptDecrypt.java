package com.newgen;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.spec.MGF1ParameterSpec;
import java.util.Base64;

public class EncryptDecrypt {
    PublicKeyStore publicKeyStore;
    PrivatekeyStore privatekeyStore;

    public EncryptDecrypt(){
        publicKeyStore = new PublicKeyStore(CryptoConstant.PUBLIC_KEY_PATH);
        privatekeyStore = new PrivatekeyStore(CryptoConstant.PRIVATE_KEYSTORE_PATH, CryptoConstant.PRIVATE_KEYSTORE_PASSWORD);
    }

    public String encrypt(String original) throws Exception{
        Cipher cipher = null;
        cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");

        if (cipher != null)
            cipher.init(Cipher.ENCRYPT_MODE, publicKeyStore.getPublicKey());

        byte[] encryptedBytes = null;
        if (cipher != null)
            encryptedBytes = cipher.doFinal(original.getBytes());

        return new String(Base64.getEncoder().encode(encryptedBytes));
    }

    public String decrypt(String original) throws Exception {
        Cipher cipher = null;
            cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");

        OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT);
        if(cipher!=null)
            cipher.init(Cipher.DECRYPT_MODE, privatekeyStore.getPrivateKeyEntry().getPrivateKey(), oaepParams);

        byte[] decryptedBytes = null;
        if(cipher!=null){
            decryptedBytes  = cipher.doFinal(Base64.getDecoder().decode(original.getBytes()));;
        }

        String decrypted = null;
        if(decryptedBytes != null){
            decrypted = new String(decryptedBytes);
        }
        return decrypted;
    }
}
