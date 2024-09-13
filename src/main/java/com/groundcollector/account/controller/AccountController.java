package com.groundcollector.account.controller;

import com.groundcollector.account.model.UserDetails;
import com.groundcollector.account.repository.AccountRepository;
import com.groundcollector.account.service.AccountService;
import com.groundcollector.account.util.OnCreateAccountEvent;
import com.groundcollector.account.model.Account;
import com.groundcollector.model.Teams;
import com.groundcollector.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private TeamsService teamsService;

    @GetMapping("account")
    public String  getRegistration(@ModelAttribute ("account") Account account, Model model) {

        ArrayList<Teams> teamsList = new ArrayList<>();
        teamsService.populateDropdown(teamsList);
        model.addAttribute("teamName", teamsList);

        return "account";
    }

    @PostMapping("account")
    public String addRegistration(@Valid @ModelAttribute ("account")
                                            Account account,
                                    BindingResult result) {
        //check for errors
        //should verify that account and the user don't exist
        //should verify email

        //encrypt password
        account.setPassword(encoder.encode(account.getPassword()));

        // create the account
        account = accountService.create(account);

        // TODO This block will need removing before verification emails go live
        // Add standard user authority role
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails =
                new UserDetails(account.getUsername(),
                        account.getPassword(),
                        authorities);
        accountRepository.createAuthorities(userDetails);


        //fire off an event on creation
        eventPublisher.publishEvent(new OnCreateAccountEvent(account, "GroundsCollector"));
        return "redirect:login";
    }

    @GetMapping("accountConfirm")
    public String confirmAccount(@RequestParam("token") String token) {
        accountService.confirmAccount(token);

        return "accountConfirmed";
    }
}
