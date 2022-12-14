import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbOdemelerActions extends DatabaseActions {

    public boolean faturayiOde(String faturaCesidi, double faturaTutari) {
        String query = "UPDATE 'musteriler' SET " + faturaCesidi + " = 0 , bakiye = bakiye - ? WHERE tcNo = ?";

        if (faturaTutari <= Actions.getDataController().getBakiye())
        {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDouble(1, faturaTutari);
                preparedStatement.setString(2, Actions.getDataController().getTcNo());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DbOdemelerActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}