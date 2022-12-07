package net.ict.bodymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.bodymanager.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

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

    @GetMapping(value = "/ac2", produces = "application/text;charset=UTF-8")
    public String priceInfo(){
        return accountService.infoList();
    }

    @GetMapping("/ac3")
    public String orderList(@RequestBody Map<String, Object> page){
        return accountService.orderList();
    }


}
