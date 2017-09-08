package ro.database.jdbcPontaj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ro.database.jdbcPontaj.model.Users;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    JdbcTemplate template;

    public List<Users> findAll() {
        String sql = "select * from users";
        RowMapper<Users> rm = new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                Users user = new Users(resultSet.getInt("userId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("enabled"),
                        resultSet.getInt("jobId"));
                String email = resultSet.getString("email");
                if (email != null) {
                    user.setEmail(email);
                }

                return user;
            }
        };

        return template.query(sql, rm);
    }

    public Users findOne(Long userId)
    {
        String sql = "select * from users where userId=" +userId;
        return template.query(sql, new ResultSetExtractor<Users>() {
            @Override
            public Users extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    Users user = new Users(resultSet.getInt("userId"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getBoolean("enabled"),
                            resultSet.getInt("jobId"));
                    String email = resultSet.getString("email");
                    if (email != null) {
                        user.setEmail(email);
                    }
                    return user;
                }
                return null;
            }
        });


    }

    public int userUpdate(Users user) {
        if (user.getUserId() > 0) {
            String sql = "UPDATE users SET firstName=?, lastName=?, username=?, password =?, enabled=?, " +
                    "jobId=?, email=? WHERE userId=" + user.getUserId();
            return template.update(sql, user.getFirstName(), user.getLastName(), user.getUsername(),
                    user.getPassword(), user.getEnabled(), user.getJobId(), user.getEmail());

        } else {
            return 0;
        }
    }

    public BigInteger userAdd(Users user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO users ( firstName, lastName, username, password, enabled, email, jobId) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(sql);
        template.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pst = con.prepareStatement(sql, new String[] {"userId"});
                pst.setString(1, user.getFirstName());
                pst.setString(2, user.getLastName());
                pst.setString(3, user.getUsername());
                pst.setString(4, user.getPassword());
                pst.setBoolean(5, user.getEnabled());
                pst.setString(6, user.getEmail());
                pst.setInt(7, user.getJobId());
                return pst;
            }
        }, keyHolder);
        return (BigInteger) keyHolder.getKey();
    }

    public int delete(int userId)
    {
        String sql = "delete FROM users where userId=?";
        return template.update(sql, userId);
    }

}
