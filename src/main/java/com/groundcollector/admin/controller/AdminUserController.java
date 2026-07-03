package com.groundcollector.admin.controller;

import com.groundcollector.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", accountRepository.findAll());
        return "admin/userManagement";
    }

    @PostMapping("/toggle")
    public String toggleUserStatus(@RequestParam("username") String username, 
                                   @RequestParam("enabled") int enabled) {
        accountRepository.toggleEnabled(username, enabled);
        return "redirect:/admin/users";
    }
}
