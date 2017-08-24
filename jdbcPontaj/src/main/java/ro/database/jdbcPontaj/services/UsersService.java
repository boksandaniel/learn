package ro.database.jdbcPontaj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ro.database.jdbcPontaj.model.Users;

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

    /*public void addUser(String name, int age) {
        //System.out.println("Name: " + name + ", age " + age);

        String sql = "insert into people(name, age) values(?, ?)";
        template.update(sql, name, age);
    }*/
}
