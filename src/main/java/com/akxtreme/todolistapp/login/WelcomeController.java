package com.akxtreme.todolistapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    /**
     * Redirect the root URL to the welcome page
     * (optional—remove if you prefer "/" to list todos directly)
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/welcome";
    }

    /**
     * Show the welcome page with the logged-in username.
     */
    @GetMapping("/welcome")
    public String gotoWelcomePage(ModelMap model, Authentication auth) {
        String username = auth.getName();
        model.addAttribute("username", username);
        return "welcome";
    }
}
