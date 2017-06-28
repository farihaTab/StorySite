package Controller.Service;

import Model.AccountuserEntity;

import java.util.List;

/**
 * Created by MiNNiE on 29-Apr-17.
 */
public interface UserService {

    List<AccountuserEntity> getAllUsers();
    void insertUser(AccountuserEntity user);
    boolean findByUsername(String username);
    boolean findByEmail(String email);
    boolean findUser(String username, String password);

    AccountuserEntity getUserProfile(String username);
}
