package Dao;

import Po.UserPo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class UserDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(){
        String sql = "insert into t_userinfo values(?,?)";
        jdbcTemplate.update(sql,"ddd","159263");
    }

    public void addUser(UserPo user) {
        // TODO Auto-generated method stub
        String sql = "insert into t_userinfo values(?,?,?)";
        jdbcTemplate.update(sql,null, user.getUserName(),user.getUserPwd());
    }

    public void deleteUser(int id) {
        // TODO Auto-generated method stub
        String sql = "delete from t_userinfo where userID = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateUser(UserPo user) {
        // TODO Auto-generated method stub
        String sql = "update t_userinfo set userName =?,userPwd=? where userID = ?";
        jdbcTemplate.update(sql, user.getUserName(),user.getUserPwd(),user.getUserID());
    }

    public String searchUserName(int id) {
        // TODO Auto-generated method stub
        String sql = "select userName from t_userinfo where userID = ?";
        return  jdbcTemplate.queryForObject(sql, String.class, id);
    }

    public UserPo searchUser(int id) {
        // TODO Auto-generated method stub
        String sql="select * from t_userinfo where userID=?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    public UserPo VerifyUser(String username,String userpwd) {
        // TODO Auto-generated method stub
        String sql="select * from t_userinfo where userName=? and userPwd=?";
        return (UserPo)jdbcTemplate.queryForMap(sql, username, userpwd);
    }

    public List<UserPo> findAll() {
        // TODO Auto-generated method stub
        String sql = "select * from t_userinfo";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    class UserRowMapper implements RowMapper<UserPo> {
        //rs为返回结果集，以每行为单位封装着
        public UserPo mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserPo user = new UserPo();
            user.setUserID(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setUserPwd(rs.getString("userPwd"));
            return user;
        }
    }
}
