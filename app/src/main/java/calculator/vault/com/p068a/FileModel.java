package calculator.vault.com.p068a;

public class FileModel implements Comparable<FileModel> {
    public String fileAddress;
    public String fileName;
    public boolean f4564c;
    public C1625q f4565d;
    public long f4566e;
    public int f4567f;

    public FileModel(C1625q c1625q) {
        this.f4565d = c1625q;
        this.f4564c = true;
    }

    public FileModel(String str, String str2) {
        this.fileAddress = str;
        this.fileName = str2;
    }

    public int m7240a(FileModel fileModel) {
        return (fileModel.f4566e == this.f4566e || this.f4564c) ? 0 : fileModel.f4566e > this.f4566e ? 1 : -1;
    }

    public int compareTo(FileModel fileModel) {
        return m7240a(fileModel);
    }
}
