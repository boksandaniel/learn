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
import ro.database.jdbcPontaj.model.Assignments;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AssignmentsService {

    @Autowired
    JdbcTemplate template;

    public List<Assignments> findAll() {
        String sql = "select * from assignments";
        RowMapper<Assignments> rm = new RowMapper<Assignments>() {
            @Override
            public Assignments mapRow(ResultSet resultSet, int i) throws SQLException {
                Assignments assign = new Assignments(resultSet.getInt("assignmentId"),
                        resultSet.getInt("userId"),
                        resultSet.getInt("projectId"));
                return assign;
            }
        };

        return template.query(sql, rm);
    }

    public Assignments findOne(int assignmentId)
    {
        String sql = "select * from assignments where assignmentId=" +assignmentId;
        return template.query(sql, new ResultSetExtractor<Assignments>() {
            @Override
            public Assignments extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    Assignments assign = new Assignments(resultSet.getInt("assignmentId"),
                            resultSet.getInt("userId"),
                            resultSet.getInt("projectId"));
                    return assign;
                }
                return null;
            }
        });
    }

    public int assignUpdate (Assignments assign) {
        if (assign.getAssignmentId() > 0) {
            String sql = "UPDATE assignments SET userId=?, projectId=? WHERE projectId=" + assign.getProjectId();
            return template.update(sql, assign.getUserId(), assign.getProjectId());
        } else {
            return 0;
        }
    }

    public BigInteger assignAdd(Assignments assign){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO assignments ( userId, projectId) VALUES ( ?, ?)";
        template.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pst = con.prepareStatement(sql, new String[] {"id"});
                pst.setInt(1, assign.getUserId());
                pst.setInt(2, assign.getProjectId());
                return pst;
            }
        }, keyHolder);
        return (BigInteger) keyHolder.getKey();

    }

    public int delete(int assignmentId)
    {
        String sql = "delete FROM assignments where assignmentId=?";
        return template.update(sql, assignmentId);
    }
}
