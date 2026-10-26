package com.mun.mundelegation.contoller;

import jakarta.servlet.http.HttpSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final JdbcTemplate jdbcTemplate;

    public LoginController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM app_users WHERE username = ? AND password = ?",
                Integer.class, username.trim(), password);

        if (count != null && count > 0) {
            session.setAttribute("loggedInUser", username.trim());
            return "redirect:/";
        }

        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
