package ro.database.jdbcPontaj.config;

import javax.sql.DataSource;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("SELECT users.username, jobs.role from users " +
                        "inner join jobs on jobs.jobId=users.jobId where users.username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/Home", "/Timetable").permitAll()
                .antMatchers("/Admin", "/Users", "/Projects", "/Jobs", "/Assignments").hasRole("ADMIN")
                .antMatchers("/Users/AddUser", "/Jobs/AddJob", "/Projects/AddProject", "/Assignments/AddAssignment").hasRole("ADMIN")
                .antMatchers("/Users/{userId}/EditUser", "/Jobs/{jobId}/EditJob", "/Projects/{projectId}/EditProject", "/Assignments/{assignmentId}EditAssignment").hasRole("ADMIN")
                .anyRequest().authenticated().and().formLogin().loginPage("/Login").permitAll().and().logout()
                .permitAll();
        http.exceptionHandling().accessDeniedPage("/LoginError");
    }

}