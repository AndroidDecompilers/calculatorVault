package calculator.vault.com;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import calculator.vault.com.applock.AuthticationCallBackResults;
import calculator.vault.com.calculator.FingerprintHandler;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;

/**
 * Created by Kundan Singh on 12-04-2018.
 */

public class FingerPrintHelper {

    private static final String KEY_NAME = "kkinfosis";
    private KeyStore keyStore;
    private Cipher cipher;
    AuthticationCallBackResults.OnResultReciver onResultReciver;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void InitHelper(Context context, AuthticationCallBackResults.OnResultReciver onResultReciver) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(FINGERPRINT_SERVICE);


        // Check whether the device has a Fingerprint sensor.
        if (!fingerprintManager.isHardwareDetected()) {
            /**
             * An error message will be displayed if the device does not contain the fingerprint hardware.
             * However if you plan to implement a default authentication method,
             * you can redirect the user to a default authentication activity from here.
             * Example:
             * Intent intent = new Intent(this, DefaultAuthenticationActivity.class);
             * startActivity(intent);
             */
            Toast.makeText(context, "Finger Print Not Avialable", Toast.LENGTH_SHORT).show();
        } else {
            // Checks whether fingerprint permission is set on manifest
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Fingerprint authentication permission not enabled", Toast.LENGTH_SHORT).show();
            } else {
                // Check whether at least one fingerprint is registered
                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    Toast.makeText(context, "Register at least one fingerprint in Settings", Toast.LENGTH_LONG);
                } else {
                    // Checks whether lock screen security is enabled or not
                    if (!keyguardManager.isKeyguardSecure()) {
                        Toast.makeText(context, "Lock screen security not enabled in Settings", Toast.LENGTH_LONG);
                    } else {
                        generateKey();
                        if (cipherInit()) {
                            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                            AuthticationCallBackResults helper = new AuthticationCallBackResults(context);
                            helper.m14842a(fingerprintManager, cryptoObject, onResultReciver);
                        }
                    }
                }
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }


        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }


        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | java.security.cert.CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }


        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | java.security.cert.CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }
}
