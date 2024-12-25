package by.nuray.filmoratesecond.controllers;


import by.nuray.filmoratesecond.models.User;
import by.nuray.filmoratesecond.services.UserService;
import by.nuray.filmoratesecond.util.UserErrorResponse;
import by.nuray.filmoratesecond.util.UserNotCreatedException;
import by.nuray.filmoratesecond.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody @Valid User user,
                                           BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().toString());
        }

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user,
                                           BindingResult bindingResult) {

        if (user.getId() ==0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().toString());
        }

        if (userService.getUserById(user.getId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        userService.update(user, user.getId());
        return new ResponseEntity( HttpStatus.OK);
    }


    @PutMapping("/{id}/friends/{friendId}")
    public ResponseEntity<Void> updateUserFriends(@PathVariable("id") int id,
                                                  @PathVariable("friendId") int friendID){
        userService.addFriend(id, friendID);

        return ResponseEntity.ok().build();


    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public ResponseEntity<Void> deleteUserFriends(@PathVariable("id") int id,
                                                  @PathVariable("friendId") int friendID){
        userService.removeFriend(id, friendID);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/friends")
    public List<User> getUserFriends(@PathVariable("id") int id){
        return userService.getFriends(id);

    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable("id") int id,@PathVariable("otherId") int otherId){
        return userService.getMutualFriends(id, otherId);
    }



    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException ex) {
        UserErrorResponse err=new UserErrorResponse(
                ex.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }



}
