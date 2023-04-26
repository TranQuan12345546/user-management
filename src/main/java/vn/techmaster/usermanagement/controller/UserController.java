package vn.techmaster.usermanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.usermanagement.dto.CreateUserRequest;
import vn.techmaster.usermanagement.dto.UpdateUserRequest;
import vn.techmaster.usermanagement.dto.UserDTO;
import vn.techmaster.usermanagement.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getListUser() {
        List<UserDTO> result = userService.getAllUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping ("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDTO result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping ("/search")
    public ResponseEntity<?> getUserByNameContain(@RequestParam String name) {
        List<UserDTO> result = userService.getUserByNameContain(name);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest ) {
        UserDTO result = userService.createUser(createUserRequest);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest req, @PathVariable int id) {
        UserDTO result = userService.updateUser(id, req);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete success");
    }
}
