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
import ro.database.jdbcPontaj.model.Jobs;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
                        resultSet.getString("role"));
                return job;
            }
        };

        return template.query(sql, rm);
    }

    public Jobs findOne(int jobId)
    {
        String sql = "select * from jobs where jobId=" +jobId;
        return template.query(sql, new ResultSetExtractor<Jobs>() {
            @Override
            public Jobs extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    Jobs job = new Jobs(resultSet.getInt("jobId"),
                            resultSet.getString("title"),
                            resultSet.getString("role"));
                    return job;
                }
                return null;
            }
        });
    }

    public List<Jobs> findRole()
    {
        String sql = "select role from jobs group by role;";
        RowMapper<Jobs> rm = new RowMapper<Jobs>() {
            @Override
            public Jobs mapRow(ResultSet resultSet, int i) throws SQLException {
                Jobs job = new Jobs(resultSet.getString("role"));
                return job;
            }
        };

        return template.query(sql, rm);
    }

    public int jobUpdate (Jobs job) {
        if (job.getJobId() > 0) {
            String sql = "UPDATE jobs SET title=?, role=? WHERE jobId=" + job.getJobId();
            return template.update(sql, job.getTitle(), job.getRole());
        } else {
            return 0;
        }
    }

    public BigInteger jobAdd(Jobs job){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO jobs ( title, role) VALUES ( ?, ?)";
        template.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pst = con.prepareStatement(sql, new String[] {"id"});
                pst.setString(1, job.getTitle());
                pst.setString(2, job.getRole());
                return pst;
            }
        }, keyHolder);
        return (BigInteger) keyHolder.getKey();

    }

    public int delete(int jobId)
    {
        String sql = "delete FROM jobs where jobId=?";
        return template.update(sql, jobId);
    }
}
