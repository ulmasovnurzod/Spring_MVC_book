package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.model.User;
import uz.pdp.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("user") User user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login");
        userService.SaveUser(user);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("loginUser", new User());
        return mv;
    }


    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("loginUser") User loginUser, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        return userService.login(loginUser.getUserName(), loginUser.getPassword())
                .map(user -> {
                    session.setAttribute("currentUser", user);
                    modelAndView.setViewName("redirect:/books");
                    return modelAndView;
                })
                .orElseGet(() -> {
                    modelAndView.setViewName("books");
                    modelAndView.addObject("error", "Username yoki parol noto‘g‘ri!");
                    return modelAndView;
                });
    }

    // 🔹 Logout
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }
}

