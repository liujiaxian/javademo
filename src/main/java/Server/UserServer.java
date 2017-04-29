package Server;

import Dao.UserDao;
import Po.UserPo;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public class UserServer {
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    private UserDao userDao;

    public void add(){
        userDao.add();
    }

    public void addUser(UserPo user) {
        userDao.addUser(user);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public void updateUser(UserPo user) {
        userDao.updateUser(user);
    }

    public String searchUserName(int id) {
        return  userDao.searchUserName(id);
    }

    public UserPo searchUser(int id) {
        return userDao.searchUser(id);
    }

    public UserPo VerifyUser(String username,String userpwd) {
        return userDao.VerifyUser(username,userpwd);
    }

    public List<UserPo> findAll() {
        return userDao.findAll();
    }
}
