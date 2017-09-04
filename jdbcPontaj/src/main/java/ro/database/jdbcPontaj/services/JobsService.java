package ro.database.jdbcPontaj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ro.database.jdbcPontaj.model.Jobs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class JobsService {

    @Autowired
    JdbcTemplate template;

    public List<Jobs> findAll() {
        String sql = "select * from jobs";
        RowMapper<Jobs> rm = new RowMapper<Jobs>() {
            @Override
            public Jobs mapRow(ResultSet resultSet, int i) throws SQLException {
                Jobs job = new Jobs(resultSet.getInt("jobId"),
                        resultSet.getString("title"),
                        resultSet.getString("accessRights"));
                return job;
            }
        };

        return template.query(sql, rm);
    }
}
