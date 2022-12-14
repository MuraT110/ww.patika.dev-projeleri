public class SingletonMusteri {

    private static SingletonMusteri singleton;
    private String musteriNo_Tc;

    public String getMusteriNo_Tc() {
        return musteriNo_Tc;
    }

    public void setMusteriNo_Tc(String musteriNo_Tc) {
        this.musteriNo_Tc = musteriNo_Tc;
    }

    public static SingletonMusteri getInstance() {
        if (singleton == null) {
            singleton = new SingletonMusteri();
        }
        return singleton;
    }
}