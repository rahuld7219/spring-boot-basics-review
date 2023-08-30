package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.model.User;
import com.udacity.jwdnd.c1.review.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView() {
        return "signup";
    }

    /*
        @ModelAttribute is to receive the data, while model is to send the data to the view.
        The User class must have same field name as name in the signup form
        otherwise values will not bind (if  not using th:object and th:field in the form).
     */
    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model) {
        String signupError = null;

        if (!this.userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowId = this.userService.createUser(user);
            if (rowId < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
