package by.babanin.scc.controller;

import by.babanin.scc.domain.User;
import by.babanin.scc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.money.Monetary;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/singup")
    private String getEmptyUser(Model model) {
        model.addAttribute("user", new User());
        return "singUp";
    }

    @PostMapping("/singup")
    private String singUp(
            @ModelAttribute @Valid User user,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "singUp";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            return revertWithMessage(user, model, "Password not confirmed");
        }
        if (!userService.saveUser(user, Monetary.getCurrency(request.getLocale()))) {
            return revertWithMessage(user, model, user.getUsername() + " username exist!");
        }
        return "redirect:/login";
    }

    private String revertWithMessage(User user, Model model, String message) {
        model.addAttribute("user", user);
        model.addAttribute("message", message);
        return "singUp";
    }
}
