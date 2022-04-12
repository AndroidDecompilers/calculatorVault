package calculator.vault.com.applock;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.os.CancellationSignal;

@TargetApi(23)
public class AuthticationCallBackResults extends AuthenticationCallback {

    public OnResultReciver f9992a;
    private CancellationSignal f9993b;
    private boolean f9994c;

    public interface OnResultReciver {
        void OnSucess();

        void OnFail();
    }

    public AuthticationCallBackResults(Context context) {
    }

    public void m14841a() {
        if (this.f9993b != null) {
            this.f9994c = true;
            this.f9993b.cancel();
            this.f9993b = null;
        }
    }

    public void m14842a(FingerprintManager fingerprintManager, CryptoObject cryptoObject, OnResultReciver onResultReciver) {
        this.f9992a = onResultReciver;
        this.f9993b = new CancellationSignal();
        this.f9994c = false;
        fingerprintManager.authenticate(cryptoObject, this.f9993b, 0, this, null);
    }

    public void m14843b() {
        this.f9993b = new CancellationSignal();
        this.f9994c = false;
    }

    public void onAuthenticationError(int i, CharSequence charSequence) {
        if (!this.f9994c) {
        }
    }

    public void onAuthenticationFailed() {
        this.f9992a.OnFail();
    }

    public void onAuthenticationHelp(int i, CharSequence charSequence) {
    }

    public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        this.f9992a.OnSucess();
    }
}
