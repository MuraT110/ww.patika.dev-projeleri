import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class AyarlarEkraniActions extends Actions {

    private DbTelNoYenileme dbTelNoYenileme = null;
    private boolean onaylaIcon = false;
    private String yeniTelNo;

    public AyarlarEkraniActions(AyarlarEkrani ayarlarEkrani) {
        setAyarlarEkrani(ayarlarEkrani);
    }

    public void setAyarlarEkrani(AyarlarEkrani ayarlarEkrani) {
        this.ayarlarEkrani = ayarlarEkrani;
    }

    public DbTelNoYenileme getDbTelNoYenileme() {
        if (dbTelNoYenileme == null) {
            dbTelNoYenileme = new DbTelNoYenileme();
        }
        return dbTelNoYenileme;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if (sourceController().labelSource(ayarlarEkrani().getGeriLabel())) {
            ayarlarEkrani().getAyarlarEkraniFrame().setVisible(false);
            kullaniciHesapEkrani();
        }

        if (sourceController().labelSource(ayarlarEkrani().getTelNoDegistirIcon())) {
            if (!onaylaIcon) {
                ayarlarEkrani().getTelNoText().setEditable(true);
                ayarlarEkrani().getTelNoDegistirIcon().setIcon(new ImageIcon(getClass().getResource()));
                this.onaylaIcon = true;
            } else {
                if (!telNoTextIsEmpty() && JTextFieldLimit.isMinLimit(ayarlarEkrani().getTelNoText())) {
                    telNoYenile();
                    ayarlarEkrani().getTelNoText().setEditable(false);
                    ayarlarEkrani().getTelNoDegistirIcon().setIcon(new ImageIcon(getClass().getResource()));
                    this.onaylaIcon = false;
                } else {
                    uyariMesajlari().uyariMesajiGoster(ayarlarEkrani().getAyarlarEkraniFrame(), "Telefon No 11 Haneli Olmalı!");
                }
            }
        }
    /
        if (sourceController().labelSource(ayarlarEkrani().getSifreDegistirIcon())) {
            ayarlarEkrani().getAyarlarEkraniFrame().setVisible(false);
            SifreYenilemeEkrani sifreYenileme = new SifreYenilemeEkrani();
        }
    }

    public boolean telNoTextIsEmpty() {
        return ayarlarEkrani().getTelNoText().getText().equals("");
    }

    public void telNoYenile() {
        yeniTelNo = ayarlarEkrani().getTelNoText().getText().trim();
        getDbTelNoYenileme().telNoYenile(this.yeniTelNo);
        Actions.getDataController().setTelNo(yeniTelNo);
        uyariMesajlari().uyariMesajiGoster(ayarlarEkrani().getAyarlarEkraniFrame(), "Telefon numaranız " + yeniTelNo + " olarak değiştirilmiştir");
    }
}