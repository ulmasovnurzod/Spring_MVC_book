package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();


    public void Save(User user) {
        users.add(user);
    }

    public boolean existsByUserName(String userName) {
        return users.stream().anyMatch(user -> user.getUserName().equalsIgnoreCase(userName));
    }

    public Optional<User> findByUserName(String userName) {
        return users.stream()
                .filter(user -> user.getUserName().equalsIgnoreCase(userName))
                .findFirst();
    }

    public Optional<User> login(String userName,String password) {
        return users.stream()
                .filter(user -> user.getUserName().equalsIgnoreCase(userName)
                        && user.getPassword().equalsIgnoreCase(password)).findFirst();
    }
}
