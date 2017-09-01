package ro.database.jdbcTest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ro.database.jdbcTest.model.Users;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    JdbcTemplate template;

    public List<Users> findAll() {
        String sql = "select * from people";
        RowMapper<Users> rm = new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                Users user = new Users(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"));
                String email = resultSet.getString("email");
                if (email != null) {
                    user.setEmail(email);
                }

                return user;
            }
        };

        return template.query(sql, rm);
    }



    public Users findOne(Long id)
    {
        String sql = "select * from people where id=" +id;
        return template.query(sql, new ResultSetExtractor<Users>() {
            @Override
            public Users extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    Users user = new Users(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"));
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
        if (user.getId() > 0) {
            String sql = "UPDATE people SET name=?, age =?, email=? WHERE id=" + user.getId();
            System.out.println(sql);
            return template.update(sql, user.getName(), user.getAge(), user.getEmail());

        } else {
            return 0;
        }
    }

    public BigInteger userAdd(Users user){
                KeyHolder keyHolder = new GeneratedKeyHolder();
                String sql = "INSERT INTO people ( name, age, email) VALUES ( ?, ?, ?)";
                System.out.println(sql);
                //return template.update(sql, user.getId(), user.getName(), user.getAge(), user.getEmail());

                template.update(new PreparedStatementCreator() {
                            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                                PreparedStatement pst = con.prepareStatement(sql, new String[] {"id"});
                                pst.setString(1, user.getName());
                                pst.setInt(2, user.getAge());
                                pst.setString(3, user.getEmail());
                                return pst;
                            }
                        }, keyHolder);
                return (BigInteger) keyHolder.getKey();

            }




    public int delete(Long id)
    {
        String sql = "delete FROM people where id=?";
        return template.update(sql, id);
    }

}
