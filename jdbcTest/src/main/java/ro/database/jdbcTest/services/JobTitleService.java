package ro.database.jdbcTest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ro.database.jdbcTest.model.JobTitle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobTitleService {

    @Autowired
    JdbcTemplate template;

    public List<JobTitle> getAll(){
        String sql = "select * from jobtitles";
        RowMapper<JobTitle> rm = new RowMapper<JobTitle>() {
            @Override
            public JobTitle mapRow(ResultSet resultSet, int i) throws SQLException {
                JobTitle title = new JobTitle(resultSet.getInt("id"), resultSet.getString("name"));
                return title;
            }
        };
        List<JobTitle> list = new ArrayList<>();
        list.add(new JobTitle(1, "admin"));
        list.add(new JobTitle(2, "user"));

        return template.query(sql, rm);
    }
}
