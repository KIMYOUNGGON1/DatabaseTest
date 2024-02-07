package org.databasetest.controller;

import org.databasetest.exception.LoginFailureException;
import org.databasetest.model.UserInfo;
import org.databasetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "member/login";
    }

    @PostMapping("/login")
    public String performLogin(@RequestParam String username, @RequestParam String password, Model model) {
        UserInfo user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("username", username);
            return "redirect:/board/getList";
        } else {
            throw new LoginFailureException("Invalid username or password.");
        }
    }

    @ExceptionHandler(LoginFailureException.class)
    public String handleLoginFailure(LoginFailureException ex, Model model) {
        model.addAttribute("loginError", ex.getMessage());
        model.addAttribute("userInfo", new UserInfo());
        return "member/login";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "member/register";
    }

    @PostMapping("/register")
    public String performRegistration(@ModelAttribute UserInfo userInfo, Model model) {
        UserInfo existingUser = userService.findByUsername(userInfo.getUsername());
        if (existingUser == null) {
            userService.registerUser(userInfo);
            return "redirect:/auth/login";
        } else {
            model.addAttribute("registrationError", "Username already exists");
            return "member/register";
        }
    }

    @GetMapping ( "/getUserInfo" )
    public String getUserInfo(Model model) {
        List<UserInfo> allUserInfo = userService.findAllUserInfo();
        model.addAttribute("userInfo", allUserInfo);
        return "member/userInfo";
    }
}
