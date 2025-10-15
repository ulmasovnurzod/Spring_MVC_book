package uz.pdp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.model.User;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    BeanPropertyRowMapper<User> beanPropertyRowMapper = BeanPropertyRowMapper.newInstance(User.class);

    public void saveUser(User user) {

        String sql = "insert into users(userName, password, fullName,age) values(?,?,?,?)";
        jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getFullName(), user.getAge());
    }

    public Optional<User> login(String userName) {
        String sql = "select * from users where userName = ?";
        List<User> users = jdbcTemplate.query(sql, beanPropertyRowMapper, userName);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));

    }


}
