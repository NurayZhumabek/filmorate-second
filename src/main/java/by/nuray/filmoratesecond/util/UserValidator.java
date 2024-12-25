package by.nuray.filmoratesecond.util;

import by.nuray.filmoratesecond.models.User;
import by.nuray.filmoratesecond.storages.InMemoryUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final InMemoryUserStorage userStorage;


    @Autowired
    public UserValidator(InMemoryUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userStorage.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", null, "This email is already in use");
        }
    }
}
