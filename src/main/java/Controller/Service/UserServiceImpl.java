package Controller.Service;

import Controller.DAO.UserDao;
import Model.AccountuserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MiNNiE on 29-Apr-17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<AccountuserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void insertUser(AccountuserEntity user) {
        userDao.insertUser(user);
    }


    @Override
    public boolean findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

    @Override
    public AccountuserEntity getUserProfile(String username) {
        return null;
    }
}
