package calculator.vault.com.calculator;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.os.CancellationSignal;

@TargetApi(23)
public class FingerprintHandler extends AuthenticationCallback {
    private CancellationSignal cancellationSignal;
    public onFingerScanListener mListener;
    private boolean mSelfCancelled;

    public interface onFingerScanListener {
        void onAuthFailed();

        void onAuthSucceed();
    }

    public FingerprintHandler(Context context) {
    }

    public void onAuthenticationError(int i, CharSequence charSequence) {
        if (!this.mSelfCancelled) {
        }
    }

    public void onAuthenticationFailed() {
        this.mListener.onAuthFailed();
    }

    public void onAuthenticationHelp(int i, CharSequence charSequence) {
    }

    public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        if (!this.mSelfCancelled) {
            this.mListener.onAuthSucceed();
        }
    }

    public void resumeListening() {
        this.cancellationSignal = new CancellationSignal();
        this.mSelfCancelled = false;
    }

    public void startAuth(FingerprintManager fingerprintManager, CryptoObject cryptoObject, onFingerScanListener listner) {
        this.mListener = listner;
        this.cancellationSignal = new CancellationSignal();
        this.mSelfCancelled = false;
        fingerprintManager.authenticate(cryptoObject, this.cancellationSignal, 0, this, null);
    }

    public void stopListening() {
        if (this.cancellationSignal != null) {
            this.mSelfCancelled = true;
            this.cancellationSignal.cancel();
            this.cancellationSignal = null;
        }
    }
}
