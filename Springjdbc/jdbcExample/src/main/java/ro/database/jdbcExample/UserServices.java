package ro.database.jdbcExample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServices{

    @Autowired
    JdbcTemplate temp;

    public List<User> findAll(){
        String sql="select * from people";
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user=new User(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"));
                String email=resultSet.getString("email");
                if(email!=null){
                    user.setEmail(email);
                }

                return user;
            }
        };

        return temp.query(sql, rm);
    }

    public void addUser(String name, int age){
        System.out.println("Name: " + name + ", age " + age);

        String sql = "insert into people(name, age) values(?, ?)";
        temp.update(sql, name, age);
    }

}
