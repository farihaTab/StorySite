package Controller.DAO;

import Model.AccountuserEntity;

import java.util.List;

/**
 * Created by MiNNiE on 28-Apr-17.
 */
public interface UserDao {

    List<AccountuserEntity> getAllUsers();
    void insertUser(AccountuserEntity user);
    boolean findByUsername(String username);
    boolean findByEmail(String email);
    boolean findUser(String username, String password);
    AccountuserEntity getUserProfile(String username);
}
