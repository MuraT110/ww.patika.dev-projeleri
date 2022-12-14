import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbSifreYenilemeActions extends DatabaseActions {

    private String tcNo = "", guvenlikCevap = "";

    public void bilgileriSorgula(String tcNo) {
        String query = "SELECT tcNo,guvenlikCevap FROM 'musteriler' WHERE tcNo = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tcNo);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.tcNo = rs.getString("tcNo");
                this.guvenlikCevap = rs.getString("guvenlikCevap");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbSifreYenilemeActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean sifreyiDegistir(String tcNo, String guvenlikCevap, int eskiSifre, int yeniSifre) {
        String query = "UPDATE 'musteriler' SET sifre = ? WHERE tcNo = ?";

        bilgileriSorgula(tcNo);
        if (eskiSifre == Actions.getDataController().getSifre()
                && tcNo.equals(this.tcNo) && guvenlikCevap.equals(this.guvenlikCevap)) {

            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, yeniSifre);
                preparedStatement.setString(2, tcNo);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DbSifreYenilemeActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean sifreyiYenile(String tcNo, String guvenlikCevap, int yeniSifre) {
        String query = "UPDATE 'musteriler' SET sifre = ? WHERE tcNo = ?";

        bilgileriSorgula(tcNo);
        if (tcNo.equals(this.tcNo) && guvenlikCevap.equals(this.guvenlikCevap)) {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, yeniSifre);
                preparedStatement.setString(2, tcNo);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DbSifreYenilemeActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}