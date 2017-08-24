package ro.database.jdbcPontaj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ro.database.jdbcPontaj.model.Projects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    JdbcTemplate template;

    public List<Projects> findAll() {
        String sql = "select * from projects";
        RowMapper<Projects> rm = new RowMapper<Projects>() {
            @Override
            public Projects mapRow(ResultSet resultSet, int i) throws SQLException {
                Projects project = new Projects(resultSet.getInt("projectId"),
                        resultSet.getString("projectName"),
                        resultSet.getString("startDate"),
                        resultSet.getString("endDate"));

                return project;
            }
        };

        return template.query(sql, rm);
    }
}
