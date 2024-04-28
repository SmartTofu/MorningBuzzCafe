package org.example.cafe.controllers;


import lombok.AllArgsConstructor;
import org.example.cafe.dto.UserDTO;
import org.example.cafe.entity.AppUser;
import org.example.cafe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration_page")
    public String registration(Model model) {
        return "registration_page";
    }

    @RequestMapping(value = "/registration_page", method = RequestMethod.POST)
    public ResponseEntity<AppUser> create(@RequestParam String email, @RequestParam String name,
                                          @RequestParam String password, Model model) {
        UserDTO dto = new UserDTO(name, password, email, "USER");
        return new ResponseEntity<>(userService.addUser(dto), HttpStatus.OK);
    }
}
