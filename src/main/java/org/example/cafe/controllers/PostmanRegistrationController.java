package org.example.cafe.controllers;

import lombok.AllArgsConstructor;
import org.example.cafe.dto.UserDTO;
import org.example.cafe.entity.AppUser;
import org.example.cafe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostmanRegistrationController {

    private final UserService service;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ResponseEntity<List<AppUser>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<AppUser> create(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(service.addUser(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.PUT)
    public ResponseEntity<AppUser> update(@RequestBody AppUser user) {
        return new ResponseEntity<>(service.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/registration/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        service.delete(id);
        return HttpStatus.OK;
    }

}
