package hh.sof003.bikestore.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof003.bikestore.domain.Account;
import hh.sof003.bikestore.domain.AccountRepository;
import hh.sof003.bikestore.domain.SignupForm;
import jakarta.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;


    // Shows login page
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    // Shows user a new sign-up form to fill
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String addNewAccount(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    // If all inputs are valid a new account from sign-up form is saved into repository
    @RequestMapping(value = "/saveaccount", method = RequestMethod.POST)
    public String saveAccount(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder cryptEncoder = new BCryptPasswordEncoder();
                String hashPwd = cryptEncoder.encode(pwd);

                Account newAccount = new Account();
                newAccount.setPasswordHash(hashPwd);
                newAccount.setUsername(signupForm.getUsername());
                newAccount.setFirstName(signupForm.getFirstName());
                newAccount.setLastName(signupForm.getLastName());
                newAccount.setEmail(signupForm.getEmail());
                newAccount.setPhone(signupForm.getPhone());
                newAccount.setRole("USER");

                if (accountRepository.findByEmail(signupForm.getEmail()) == null) {
                    if (accountRepository.findByUsername(signupForm.getUsername()) == null) {
                        accountRepository.save(newAccount);
                    } else {
                        bindingResult.rejectValue("username", "err.username", "Username is already in use.");
                        return "signup";
                    }
                } else {
                    bindingResult.rejectValue("email", "err.email", "Email is already in use.");
                    return "signup";
                }

            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
                return "signup";
            }
        } else {
            return "signup";
        }
        return "redirect:/login";
    }

}
