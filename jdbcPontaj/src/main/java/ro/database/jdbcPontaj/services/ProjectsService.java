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
import ro.database.jdbcPontaj.model.Projects;

import java.math.BigInteger;
import java.sql.*;
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
                        resultSet.getDate("startDate"),
                        resultSet.getDate("endDate"));

                return project;
            }
        };

        return template.query(sql, rm);
    }

    public Projects findOne(int projectId)
    {
        String sql = "select * from projects where projectId=" +projectId;
        return template.query(sql, new ResultSetExtractor<Projects>() {
            @Override
            public Projects extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    Projects project = new Projects(resultSet.getInt("projectId"),
                            resultSet.getString("projectName"),
                            resultSet.getDate("startDate"),
                            resultSet.getDate("endDate"));
                    return project;
                }
                return null;
            }
        });
    }

    public int projectUpdate (Projects project) {
        if (project.getProjectId() > 0) {
            String sql = "UPDATE projects SET projectName=?, startDate =?, endDate=? WHERE projectId="
                    + project.getProjectId();
            return template.update(sql, project.getProjectName(), project.getStartDate(), project.getEndDate());
        } else {
            return 0;
        }
    }

    public BigInteger projectAdd(Projects project){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO projects ( projectName, startDate, endDate) VALUES ( ?, ?, ?)";
        template.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pst = con.prepareStatement(sql, new String[] {"id"});

                pst.setString(1, project.getProjectName());
                pst.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
                pst.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
                return pst;
            }
        }, keyHolder);
        return (BigInteger) keyHolder.getKey();

    }

    public int delete(int projectId)
    {
        String sql = "delete FROM projects where projectId=?";
        return template.update(sql, projectId);
    }
}
