package calculator.vault.com.customgallery.pictures;

public class PictureModel {

    public String f9589a;
    public boolean f9590b;
    public String f9591c;
    public boolean f9592d;
    public boolean f9593e;
    private String f9594f;
    private boolean f9595g;

    public PictureModel(String str, boolean z, String str2, boolean z2) {
        this.f9589a = str;
        this.f9590b = z;
        this.f9591c = str2;
        this.f9593e = z2;
    }

    public String m14502a() {
        return this.f9594f;
    }

    public void m14503a(String str) {
        this.f9594f = str;
    }

    public void m14504a(boolean z) {
        this.f9592d = z;
    }

    public void m14505b(boolean z) {
        this.f9595g = z;
    }

    public boolean m14506b() {
        return this.f9592d;
    }

    public boolean m14507c() {
        return this.f9595g;
    }
}
