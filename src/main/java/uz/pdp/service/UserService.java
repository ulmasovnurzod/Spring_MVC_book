package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dao.UserDAO;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    public void SaveUser(User user) {
        userDAO.saveUser(user);
    }

    public Optional<User> login(String userName, String password) {
        Optional<User> optionalUser = userDAO.login(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equalsIgnoreCase(password)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
