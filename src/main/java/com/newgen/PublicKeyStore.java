package com.newgen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class PublicKeyStore {
    private PublicKey publickey = null;
    private String publicCertPath;

    public PublicKeyStore(String cerPath) {
        this.publicCertPath = cerPath;
    }

    public PublicKey getPublicKey() throws CertificateException, IOException {
        InputStream fi = null;
        fi = new FileInputStream(this.publicCertPath);

        CertificateFactory certFact = CertificateFactory.getInstance("x.509");
        X509Certificate certificate = (X509Certificate)certFact.generateCertificate((InputStream)fi);
        this.publickey = certificate.getPublicKey();
        return this.publickey;
    }
}

