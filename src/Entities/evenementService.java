/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.event;
import Utils.Connexion;

import java.sql.*;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import javax.swing.plaf.nimbus.State;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ASUS
 */
public class evenementService implements Iservice <event> {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public evenementService(){
        cnx= Connexion.getInstance().getConnection();
    }

    /**
     *
     * @param e
     * @throws SQLException
     */


    @Override
    public void insert(event e){
        Date de=Date.valueOf(LocalDate.now());
        String req="INSERT INTO `projet`.`event` (`nom`, `date`, `description`, `prix`, `adresse`, `nbre_place`) VALUES ('"+e.getNom()+"','"+de+",'"+e.getDescription()+"','"+e.getPrix()+"','"+e.getAdresse()+"','"+e.getImage()+"','"+e.getNbre_place()+"');";
        try {

            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void add(event entity) {
        try {
            String request
                    = "INSERT INTO event(nom, date, description, prix, adresse, image"
                    + ",nbre_place) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setString(1, entity.getNom());
            st.setDate(2, Date.valueOf(entity.getDate()));
            st.setString(3, entity.getDescription());
            st.setString(4, String.valueOf(entity.getPrix()));
            st.setString(5, entity.getAdresse());
            st.setString(6, entity.getImage());
            st.setString(7, String.valueOf(entity.getNbre_place()));
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }


    public void UpdateEvent(event e) throws SQLException {

        String requete = "Update event set nom=?,description=?,prix=?,adresse=?,image=?,nbre_place=?,date=? where id= ?";
        PreparedStatement st = cnx.prepareStatement(requete);
        st.setString(1, e.getNom());
        st.setString(2, e.getDescription());
        st.setInt(3, e.getPrix());
        st.setString(4, e.getAdresse());
        st.setString(5, e.getImage());
        st.setInt(6, e.getNbre_place());
        java.sql.Date date = java.sql.Date.valueOf(e.getDate());
        st.setDate(7, date);
        st.setInt(8, e.getId());

        st.executeUpdate();

    }
    @Override
    public boolean update(event t,int id){
    return true;
    }

    @Override
    public boolean delete(int id) {
        if(search(id)){
            try {

                pre=cnx.prepareStatement("delete from event where id= ?;");
                pre.setInt(1, id);
                pre.execute();
            } catch (SQLException ex) {
                Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("delete valide");
            return true;
        }
        System.out.println("event nexiste pas");
        return false;
    }


    public String[] getData() throws SQLException {
        ArrayList<String> a = new ArrayList<String>();
        String requete = "SELECT * FROM event";

        Statement ste = cnx.createStatement();
        ResultSet res = ste.executeQuery(requete);

        while (res.next()) {
            a.add(res.getString(2));
            a.add(res.getString(4));
            a.add(String.valueOf(res.getInt(5)));
            a.add(res.getString(6));
        }

        return (String[]) a.toArray(new String[a.size()]);

    }


    public void DeleteEvent(int iD) throws SQLException {
        String requete = "Delete from comment where idEvent =" + iD;
        PreparedStatement st = cnx.prepareStatement(requete);
        st.executeUpdate(requete);

        String requete2 = "Delete from event where id =" + iD;
        PreparedStatement st2 = cnx.prepareStatement(requete2);
        st2.executeUpdate(requete2);
    }


    public List<event> filterEventList(String n) throws SQLException {

        String requete = "Select * from event where nom like '%" + n + "%' or adresse like '%'+n+'%' ";
        PreparedStatement pst = cnx.prepareStatement("SELECT * FROM  event WHERE nom LIKE ? OR adresse LIKE ?");
        pst.setString(1, '%' + n + '%');
        pst.setString(2, '%' + n + '%');
        List<event> rec = new ArrayList<>();
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
            gregorianCalendar.setTime(rs.getDate("date"));
            ZonedDateTime zonedDateTime = gregorianCalendar.toZonedDateTime();
            rec.add(new event(rs.getInt("id"),rs.getString("nom"), zonedDateTime.toLocalDate(),rs.getString("description"),rs.getInt("prix"),rs.getString("adresse"),rs.getString("image"),rs.getInt("nbre_place")));

        }
        return rec;
    }

    public List<event> allEventList() throws SQLException {
        String requete = "select * from event";
        List<event> rec = new ArrayList<>();
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(requete);

        while(rs.next())
        {      Date de=Date.valueOf(LocalDate.now());

            rec.add(new event(rs.getInt("id"),rs.getString("nom"), de.toLocalDate(),rs.getString("description"),rs.getInt("prix"),rs.getString("adresse"),rs.getString("image"),rs.getInt("nbre_place")));

        }
        return rec;
    }


    public event searchByName(String nom) throws SQLException {

        String requete = "Select * from event where nom='" + nom + "';";
        event prod = new event();
       Statement ste = cnx.createStatement();
        ResultSet res = ste.executeQuery(requete);

        while (res.next()) {

            int id = res.getInt("id");
            String name = res.getString("nom");
            int price = res.getInt("prix");
            String image = res.getString("image");
            String description=res.getString("description");

            prod.setId(id);
            prod.setNom(name);
            prod.setImage(image);
            prod.setPrix(price);
            prod.setDescription(description);
        }

        return prod;
    }


    public event fetchMyEvent(int id) throws SQLException {
        System.out.println(id);
        String requete = "Select * from event where id='" + id + "';";
        event event = new event();
        Statement ste = cnx.createStatement();
        ResultSet res = ste.executeQuery(requete);

        while (res.next()) {

            int idEvent = res.getInt("id");
            String name = res.getString("nom");
            int price = res.getInt("prix");
            String image = res.getString("image");
            int nbre_place = res.getInt("nbre_place");
            String description = res.getString("description");
            String address = res.getString("adresse");

            GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance();
            gregorianCalendar.setTime(res.getDate("date"));
            ZonedDateTime zonedDateTime = gregorianCalendar.toZonedDateTime();
            event.setId(idEvent);
            event.setNom(name);
            event.setImage(image);
            event.setPrix(price);
            event.setDescription(description);
            event.setAdresse(address);
            event.setNbre_place(nbre_place);
            event.setDate(zonedDateTime.toLocalDate());
        }

        return event;
    }




    @Override
    public ObservableList<event> displayAll() {
        String req ="select * from event";
        ObservableList<event> list =new ObservableList<event>() {
            @Override
            public void addListener(ListChangeListener<? super event> listChangeListener) {

            }

            @Override
            public void removeListener(ListChangeListener<? super event> listChangeListener) {

            }

            @Override
            public boolean addAll(event... events) {
                return false;
            }

            @Override
            public boolean setAll(event... events) {
                return false;
            }

            @Override
            public boolean setAll(Collection<? extends event> collection) {
                return false;
            }

            @Override
            public boolean removeAll(event... events) {
                return false;
            }

            @Override
            public boolean retainAll(event... events) {
                return false;
            }

            @Override
            public void remove(int i, int i1) {

            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<event> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(event event) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends event> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends event> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public event get(int index) {
                return null;
            }

            @Override
            public event set(int index, event element) {
                return null;
            }

            @Override
            public void add(int index, event element) {

            }

            @Override
            public event remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<event> listIterator() {
                return null;
            }

            @Override
            public ListIterator<event> listIterator(int index) {
                return null;
            }

            @Override
            public List<event> subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }
        };
        try {
            st=cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next())
            {      Date de=Date.valueOf(LocalDate.now());

                list.add(new event(rs.getString("nom"), de.toLocalDate(),rs.getString("description"),rs.getInt("prix"),rs.getString("adresse"),rs.getString("image"),rs.getInt("nbre_place")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int geteventId(String value) {
        int result = 0;
        String rq = "select id from Event where Name = '" + value + "'";
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

    public List<event> trieParID() {
        String req ="select * from event ORDER BY id";
        List<event> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next())
            {      Date de=Date.valueOf(LocalDate.now());
                list.add(new event(rs.getString("nom"), de.toLocalDate(),rs.getString("description"),rs.getInt("prix"),rs.getString("adresse"),rs.getString("image"),rs.getInt("nbre_place")));


            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<event> trieParDate() {
        String req ="select * from event ORDER BY date";
        List<event> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next())
            {       Date de=Date.valueOf(LocalDate.now());
                list.add(new event(rs.getString("nom"), de.toLocalDate(),rs.getString("description"),rs.getInt("prix"),rs.getString("adresse"),rs.getString("image"),rs.getInt("nbre_place")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }



    public Boolean search(int id) {
        String req ="select * from event";
        List<Integer> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next())
            {       java.util.Date de=new java.util.Date(rs.getDate(2).getTime());
                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.contains(id);
    }
    public List<event> recherche_event(int id) {
        List<event> arr=new ArrayList<>();
        PreparedStatement pre;
        try {
            pre = cnx.prepareStatement("SELECT * FROM event WHERE  id LIKE ? ;");
            pre.setInt(1,id);
            ResultSet rs=pre.executeQuery();
            while (rs.next()) {
                Date de=Date.valueOf(LocalDate.now());

                arr.add(new event(rs.getString("nom"), de.toLocalDate(),rs.getString("description"),rs.getInt("prix"),rs.getString("adresse"),rs.getString("image"),rs.getInt("nbre_place")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }



        return arr;

    }


}

