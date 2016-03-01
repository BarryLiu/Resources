package com.example.soft;

import android.content.Context;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;


/**
 * Created by aya4 on 2016/2/25.
 */
public class HttpsUrlSSLHelper {
    private static final String KEY_STORE_TYPE_BKS = "bks";
    private static final String KEY_STORE_TYPE_P12 = "PKCS12";
    private static final String SCHEME_HTTPS = "https";
    private static final int HTTPS_PORT = 8443;

    private static final String KEY_STORE_CLIENT_PATH = "client.p12";
    private static final String KEY_STORE_TRUST_PATH = "client.truststore";
    private static final String KEY_STORE_PASSWORD = "12345677";
    private static final String KEY_STORE_TRUST_PASSWORD = "123456";
    private static KeyStore keyStore;
    private static KeyStore trustStore;


    /**
     * 获得KeyStore.
     * @param keyStorePath
     *            密钥库路径
     * @param password
     *            密码
     * @return 密钥库
     * @throws Exception
     */
    public static KeyStore getTRUSTKeyStore(String password, String keyStorePath,Context context)
            throws Exception {
        // 实例化密钥库
        KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE_BKS);
        // 获得密钥库文件流
        InputStream is = context.getAssets().open(keyStorePath);
        // 加载密钥库
        ks.load(is, password.toCharArray());
        // 关闭密钥库文件流
        is.close();
        return ks;
    }
    public static KeyStore getKeyStore(String password, String keyStorePath,Context context)
            throws Exception {
        // 实例化密钥库
        KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE_P12);
        // 获得密钥库文件流
        InputStream is = context.getAssets().open(keyStorePath);
        // 加载密钥库

        ks.load(is, password.toCharArray());
        // 关闭密钥库文件流
        is.close();
        return ks;
    }
    /**
     * 获得SSLSocketFactory.
     * @param password
     *            密码
     * @param keyStorePath
     *            密钥库路径
     * @param trustStorePath
     *            信任库路径
     * @return SSLSocketFactory
     * @throws Exception
     */
    public static SSLContext getSSLContext(String password,
                                           String keyStorePath, String trustStorePath,Context context) throws Exception {
        // 实例化密钥库
        KeyManagerFactory keyManagerFactory = KeyManagerFactory
                .getInstance(KeyManagerFactory.getDefaultAlgorithm());
        // 获得密钥库
        KeyStore keyStore = getKeyStore(password, keyStorePath, context);
        // 初始化密钥工厂
        keyManagerFactory.init(keyStore, password.toCharArray());

        // 实例化信任库
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        // 获得信任库
        KeyStore trustStore = getTRUSTKeyStore(password, trustStorePath,context);
        // 初始化信任库
        trustManagerFactory.init(trustStore);
        // 实例化SSL上下文
        SSLContext ctx = SSLContext.getInstance("TLS");
        // 初始化SSL上下文

        ctx.init(keyManagerFactory.getKeyManagers(),
                trustManagerFactory.getTrustManagers(), null);


        // 获得SSLSocketFactory

        ctx.getSocketFactory();

        return ctx;
    }

    public static SSLContext getSSLContext(Context context) throws Exception {
         return  getSSLContext(KEY_STORE_PASSWORD,KEY_STORE_CLIENT_PATH,KEY_STORE_TRUST_PATH,context);
    }





}
