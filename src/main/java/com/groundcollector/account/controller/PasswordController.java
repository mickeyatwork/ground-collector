package com.groundcollector.account.controller;

import com.groundcollector.account.util.OnPasswordResetEvent;
import com.groundcollector.account.model.Password;
import com.groundcollector.account.model.ResetToken;
import com.groundcollector.account.repository.PasswordRepository;
import com.groundcollector.account.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class PasswordController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("password")
    public String getPasswordReset (@ModelAttribute("password") Password password) {

        return "password";
    }

    @PostMapping("password")
    public String sendEmailToReset(@Valid @ModelAttribute ("password")
                                   Password password,
                                   BindingResult result) {
        // check for errors
        // verify valid email address
        // verify email in database
        // fire off an event to reset email
        // TODO configure for email sends
        eventPublisher.publishEvent(new OnPasswordResetEvent(password, "groundcollector_war"));
        return "redirect:password?sent=true";
    }

    @GetMapping("passwordReset")
    public ModelAndView getNewPassword(@RequestParam("token") String token) {
        //verify token
        Password password = new Password();
        password.setToken(token);

        return new ModelAndView("resetPassword", "password", password);
    }

    @PostMapping("passwordReset")
    public String saveNewAPassword(@RequestParam("token") String token,
                                   @ModelAttribute("password") Password password) {

        //verify token
        ResetToken resetToken = passwordRepository.findByToken(token);
        if(resetToken.getExpiryDate().after(new Date())){
            password.setPassword(encoder.encode(password.getPassword()));
            passwordService.update(password, resetToken.getUsername());
            return "redirect:passwordReset?reset=true&token=0";
        }
        else {
            // Doesn't exist in this example
            return "tokenExpired";
        }

    }
}
