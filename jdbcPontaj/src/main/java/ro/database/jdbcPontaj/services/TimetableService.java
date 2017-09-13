package ro.database.jdbcPontaj.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ro.database.jdbcPontaj.model.Timetable;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TimetableService {

    @Autowired
    JdbcTemplate template;

    public List<Timetable> findAll(String username) {
        String sql = " SELECT * FROM timetables INNER join assignments" +
                " on assignments.assignmentId = timetables.assignmentId INNER JOIN users " +
                "on users.userId = assigments.userId where username=" +username;

        RowMapper<Timetable> rm = new RowMapper<Timetable>() {
            @Override
            public Timetable mapRow(ResultSet resultSet, int i) throws SQLException {
                Timetable timetable = new Timetable(resultSet.getInt("timetableId"),
                        resultSet.getInt("assignmentId"),
                        resultSet.getDate("date"),
                        resultSet.getTime("hoursWorked"));

                return timetable;
            }
        };

        return template.query(sql, rm);
    }

    public BigInteger timetableAdd(Timetable timetable){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO timetables ( assignmentId, date, hoursWorked) VALUES ( ?, ?, ?)";
        template.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pst = con.prepareStatement(sql, new String[] {"id"});

                pst.setInt(1, timetable.getAssignmentId());
                pst.setDate(2, new java.sql.Date(timetable.getDate().getTime()));
                pst.setDate(3, new java.sql.Date(timetable.getHoursWorked().getTime()));
                return pst;
            }
        }, keyHolder);
        return (BigInteger) keyHolder.getKey();
    }


}
