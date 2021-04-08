package Services;

import Entities.Offre_Emploi;
import Interfaces.IService;
import Utiles.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Offre_Emploi_Service
 */
public class Offre_Emploi_Service implements IService<Offre_Emploi> {

  Connection cnx = Connexion.getInstance().getConnection();

  @Override
  public ArrayList<Offre_Emploi> getAll() {
    ArrayList<Offre_Emploi> offres = new ArrayList<>();
    String request = "select * from Offre_Emploi";
    try {
      Statement statement = cnx.createStatement();
      ResultSet rs = statement.executeQuery(request);
      while (rs.next()) {
        Offre_Emploi offre = new Offre_Emploi();
        offre.setTitre(rs.getString("titre"));
        offres.add(offre);
      }
    } catch (SQLException e) {
      Logger
        .getLogger(Offre_Emploi_Service.class.getName())
        .log(Level.SEVERE, null, e);
    }
    return offres;
  }

  @Override
  public void add(Offre_Emploi entity) {
    try {
      String request =
        "INSERT INTO OFFRE_EMPLOI(titre,poste, description, location, file, email" +
        ",date_debut, date_expiration,max_salary, min_salary) VALUES(?,?,?,?,?,?,?,?,?,?)";
      PreparedStatement st = cnx.prepareStatement(request);
      st.setString(1, entity.getTitre());
      st.setString(2, entity.getPoste());
      st.setString(3, entity.getDescription());
      st.setString(4, entity.getLocation());
      st.setString(5, entity.getFile());
      st.setString(6, entity.getEmail());
      st.setDate(7, (Date) entity.getDate_debut());
      st.setDate(8, (Date) entity.getDate_expiration());
      st.setFloat(9, entity.getMax_salary());
      st.setFloat(9, entity.getMin_salary());
    } catch (SQLException e) {
      Logger
        .getLogger(Offre_Emploi_Service.class.getName())
        .log(Level.SEVERE, null, e);
    }
  }
}
