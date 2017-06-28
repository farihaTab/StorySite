package Controller.DAO;

import Database.DataConnection;
import Model.AccountuserEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MiNNiE on 28-Apr-17.
 */
@Repository
@Transactional(readOnly = false)
public class UserDaoImpl implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;

    Connection conn = DataConnection.getConnection();

    public List<AccountuserEntity> getAllUsers() {
        try {

            String query = "select username,EMAIL from ACCOUNTUSER";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<AccountuserEntity> accountUserArrayList=new ArrayList<AccountuserEntity>();
            while(rs.next()) {
                accountUserArrayList.add(new AccountuserEntity(rs.getString("Username"),rs.getString("Email")));
            }

            rs.close();
            stmt.close();
           return accountUserArrayList;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }


    @Override
    public void insertUser(AccountuserEntity user) {

        try {

            String query = "INSERT INTO ACCOUNTUSER(USERNAME,EMAIL,PASSWORD,FIRSTNAME,LASTNAME) " +
                            "VALUES(?,?,?,?,?) ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,user.getUsername()); stmt.setString(2,user.getEmail());
            stmt.setString(3,user.getPassword()); stmt.setString(4,user.getFirstname());stmt.setString(5,user.getLastname());
            ResultSet rs = stmt.executeQuery();


            rs.close();
            stmt.close();
        } catch (Exception e) {e.printStackTrace();}


    }



 //   @SuppressWarnings("unchecked")
    @Override
    public boolean findByUsername(String username) {

        try {

            String query = "SELECT USERNAME FROM ACCOUNTUSER WHERE USERNAME=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,username);
            int rs = stmt.executeUpdate();
            if(rs==0) {stmt.close();return false;}
            stmt.close();
        } catch (Exception e) {e.printStackTrace();return false;}
        return true;
    }

    @Override
    public boolean findByEmail(String email) {
        try {
            String query = "SELECT EMAIL FROM ACCOUNTUSER WHERE EMAIL=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,email);
            int rs = stmt.executeUpdate();
            if(rs==0) {stmt.close();return false;}
            stmt.close();
        } catch (Exception e) {e.printStackTrace();return false;}
        return true;
    }



    @Override
    public boolean findUser(String username, String password) {
        try {

            String query = "SELECT USERNAME FROM ACCOUNTUSER WHERE USERNAME=? and PASSWORD=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,username);stmt.setString(2,password);
            int rs = stmt.executeUpdate();
            if(rs==0) {stmt.close();return false;}
            stmt.close();
        } catch (Exception e) {e.printStackTrace();return false;}
        return true;
    }

    @Override
    public AccountuserEntity getUserProfile(String username) {

        try {

            String query = "SELECT * FROM ACCOUNTUSER WHERE USERNAME=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                AccountuserEntity accountuserEntity=new AccountuserEntity(rs.getTime("CREATEDAT"),rs.getString("EMAIL"),rs.getString("Firstname"),
                        rs.getString("Lastname"),rs.getString("USERNAME"));
                return accountuserEntity;
            }
            stmt.close();
        } catch (Exception e) {e.printStackTrace();return null;}


        return null;
    }
}
