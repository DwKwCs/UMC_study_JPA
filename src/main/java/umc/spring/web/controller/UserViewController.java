package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.web.dto.UserRequest;

@Controller
@RequiredArgsConstructor
public class UserViewController {
    private final UserCommandService userCommandService;

    @PostMapping("/signup")
    public String joinUser(@ModelAttribute("userJoinDto") UserRequest.UserDto request,
                           BindingResult bindingResult,
                           Model model) {

        System.out.println(request.getName());
        System.out.println(request.getEmail());
        System.out.println(request.getLoginId());
        System.out.println(request.getPassword());
        System.out.println(request.getGender());
        System.out.println(request.getBirth());
        System.out.println(request.getAddress());
        System.out.println(request.getRole());

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            userCommandService.joinUser(request);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userJoinDto", new UserRequest.UserDto());
        return "signup";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}