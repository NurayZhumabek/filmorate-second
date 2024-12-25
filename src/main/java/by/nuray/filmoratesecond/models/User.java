package by.nuray.filmoratesecond.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Login can only contain letters, numbers, and underscores")
    private String login;

    private String name;

    @Past(message = "Date of birth cannot be in the future")
    private LocalDate birthday;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
