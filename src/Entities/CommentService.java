/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.comment;
import Entities.event;
import Utils.Connexion;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ASUS
 */
public class CommentService implements Iservice <comment> {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public CommentService(){
        cnx= Connexion.getInstance().getConnection();
    }

    /**
     *
     * @param
     * @throws SQLException
     */


    @Override
    public void insert(comment c){
        Date dc=Date.valueOf(LocalDate.now());
        String req="INSERT INTO comment(name,email,phone,message,created_at,idEvent) values (?,?,?,?,?,?)";
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setString(1,c.getName());
            pst.setString(2,c.getEmail());
            pst.setLong(3,c.getPhone());

            pst.setString(4,c.getMessage());
            pst.setDate(5,dc);
            pst.setInt(6,c.getIdEvent());

            pst.executeUpdate();

//            st=cnx.createStatement();
  //          st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public boolean update(comment t,int id){
        if(chercher(id)){
            Date dc=Date.valueOf(LocalDate.now());
            try {
                pre=cnx.prepareStatement("UPDATE comment SET name = ? ,email =? ,phone =?,message =?,created_at =?,idEvent =? WHERE id=?");
                pre.setString(1,t.getName());
                pre.setString(2, t.getEmail());
                pre.setLong(3, t.getPhone());
                pre.setString(4, t.getMessage());
                pre.setDate(5,dc);
                pre.setInt(6, t.getIdEvent());
                pre.setInt(7,id);
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("update valide");
            return true;}
        System.out.println("update invalid: commentaire n existe pas");
        return false;
    }

    @Override
    public boolean delete(int id) {
        if(chercher(id)){
            try {

                pre=cnx.prepareStatement("delete from comment where id= ?;");
                pre.setInt(1, id);
                pre.execute();
            } catch (SQLException ex) {
                Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("delete valide");
            return true;
        }
        System.out.println("commentaire nexiste pas");
        return false;
    }

    @Override
    public List<comment> displayAll() {
        String req ="select * from comment";
        List<comment> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next())
            {      Date dc=Date.valueOf(LocalDate.now());
                list.add(new comment(rs.getString("name"),rs.getString("email"),rs.getLong("phone"),rs.getString("message") ,dc.toLocalDate(),rs.getInt("idEvent")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getcommentId(String value) {
        int result = 0;
        String rq = "select id from Comment where Name = '" + value + "'";
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(rq);
            while (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }
        return result;
    }


    public List<comment> searchEventComments(String idEvent) throws SQLException {

        String requete = "Select * from comment where idEvent ='" + idEvent + "';";
        List<comment> rec = new ArrayList<>();

        Statement ste = cnx.createStatement();
        ResultSet res = ste.executeQuery(requete);

        while (res.next()) {
            comment cmnt = new comment();
            int id = res.getInt("id");
            String name = res.getString("name");
            String msg = res.getString("message");
            Date created_at=res.getDate("created_at");
            GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
            gregorianCalendar.setTime(created_at);
            ZonedDateTime zonedDateTime = gregorianCalendar.toZonedDateTime();

            cmnt.setId(id);
            cmnt.setName(name);
            cmnt.setMessage(msg);
            cmnt.setCreated_at(zonedDateTime.toLocalDate());
            //cmnt.setDescription(description);

            rec.add(cmnt);
        }

        return rec;
    }

    public List<comment> trieParID() {
        String req ="select * from comment ORDER BY id";
        List<comment> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next())
            {      Date dc=Date.valueOf(LocalDate.now());
                list.add(new comment(rs.getString("name"),rs.getString("email"),rs.getLong("phone"),rs.getString("message"), dc.toLocalDate(),rs.getInt("idEvent")));


            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<comment> trieParDate() {
        String req ="select * from comment ORDER BY date";
        List<comment> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next())
            {       Date dc=Date.valueOf(LocalDate.now());
                list.add(new comment(rs.getString("name"),rs.getString("email"),rs.getLong("phone"),rs.getString("message"), dc.toLocalDate(),rs.getInt("idEvent")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Boolean chercher(int id) {
        String req ="select * from comment";
        List<Integer> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next())
            {     Date dc=Date.valueOf(LocalDate.now());
                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.contains(id);
    }
    public List<comment> recherche_comment(int id) {
        List<comment> arr=new ArrayList<>();
        PreparedStatement pre;
        try {
            pre = cnx.prepareStatement("SELECT * FROM comment WHERE  id LIKE ? ;");
            pre.setInt(1,id);
            ResultSet rs=pre.executeQuery();
            while (rs.next()) {
                Date dc=Date.valueOf(LocalDate.now());

                arr.add(new comment(rs.getString("name"),rs.getString("email"),rs.getLong("phone"),rs.getString("message"), dc.toLocalDate(),rs.getInt("idEvent")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }



        return arr;

    }


}

