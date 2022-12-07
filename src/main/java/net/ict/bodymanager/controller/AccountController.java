package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Log4j2
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/ac")
    public String memInfo(){
        return accountService.MemberInfo();
    }

}
