package com.newgen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;

public class PrivatekeyStore {

    private PrivateKeyEntry privateKeyEntry = null;
    private String filePath;
    private String password;

    public PrivatekeyStore(String file, String pass) {
        this.filePath = file;
        this.password = pass;
    }

    public PrivateKeyEntry getPrivateKeyEntry() {
        InputStream keyStoreInputStream = null;
        KeyStore ks = null;

        try {
            if (this.filePath.toLowerCase().endsWith("jks")) {
                System.out.println("JKS Key Store");
                ks = KeyStore.getInstance("JKS");
            } else {
                ks = KeyStore.getInstance("PKCS12");
            }

            keyStoreInputStream = new FileInputStream(this.filePath);

            ks.load((InputStream)keyStoreInputStream, this.password.toCharArray());
            String alias = null;
            alias = (String)ks.aliases().nextElement();
            this.privateKeyEntry = (PrivateKeyEntry)ks.getEntry(alias, new PasswordProtection(this.password.toCharArray()));
        } catch (Exception var12) {
            System.err.println("Error While Loading Private Key::" + var12.getMessage());
        } finally {
            if (keyStoreInputStream != null) {
                try {
                    ((InputStream)keyStoreInputStream).close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }

        }

        return this.privateKeyEntry;
    }
}
