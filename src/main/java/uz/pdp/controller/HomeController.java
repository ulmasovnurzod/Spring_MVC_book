package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.model.User;

@Controller
public class HomeController {
    @GetMapping("/home")
    public ModelAndView home(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");

        ModelAndView mv = new ModelAndView();
        if (currentUser == null) {
            mv.setViewName("redirect:/login");
            return mv;
        }

        mv.setViewName("home");
        mv.addObject("fullName", currentUser.getFullName());
        mv.addObject("age", currentUser.getAge());
        mv.addObject("userName", currentUser.getUserName());
        return mv;
    }
}
