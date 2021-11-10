package ru.matmex.animalshelter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.matmex.animalshelter.model.RoleName;
import ru.matmex.animalshelter.model.User;
import ru.matmex.animalshelter.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
                          HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userForm.getUsername().length() < 5){
            model.addAttribute("usernameError", "Логин должен быть не менее 5 символов");
            return "registration";
        }
        if (userForm.getPassword().length() < 5){
            model.addAttribute("passwordError", "Пароль должен быть не менее 5 символов");
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm, RoleName.ROLE_USER)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        authWithHttpServletRequest(request, userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/";
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error login");
        }
    }
}
