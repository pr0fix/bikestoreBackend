package hh.sof003.bikestore.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupForm {

    @NotEmpty(message = "Username cannot be empty.")
    @Size(min = 5, max = 20, message = "Username length must be between 6 and 20 characters.")
    private String username = "";

    @NotEmpty(message = "Password cannot be empty.")
    @Size(min = 8, max = 30, message = "Password length must be between 8 and 35 characters.")
    private String password = "";

    @NotEmpty(message = "Re-type password cannot be empty.")
    @Size(min = 8, max = 30, message = "Re-type password length must be between 8 and 35 characters.")
    private String passwordCheck = "";

    @NotEmpty(message = "Please give me your fucking name... or else")
    private String firstName = "";

    @NotEmpty(message = "Your lastname, give it, RIGHT NOW!")
    private String lastName = "";

    @NotEmpty(message = "Email cannot be empty.")
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "The email you gave was invalid.")
    private String email = "";

    @NotEmpty(message = "Phone cannot be empty.")
    @Pattern(regexp = "\\d{10}")
    private String phone = "";

    @NotEmpty
    private String role = "USER";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
