package com.portal.web.domain.server.webservice;


import com.portal.web.domain.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.portal.web.domain.service.AccountService;

@Controller
@RequestMapping("/api/account")
public class AccountServiceRest {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{game}/{name}")
    public Account getUser(@PathVariable String game, @PathVariable String name){
        return accountService.getUser(game, name);
    }

    @PostMapping("/perform_login")
    public String performLogin(@RequestParam String username, @RequestParam String password) {
        // Здесь нет необходимости в обработке, так как Spring Security автоматически обрабатывает аутентификацию
        return "redirect:/start"; // Перенаправляем пользователя на домашнюю страницу
    }


    @PostMapping("/register")
    public String performRegister(@RequestParam String username, @RequestParam String password) {
        // Проверяем, существует ли уже пользователь с таким именем

        System.out.println("StArt REGISTER");
        try {
            accountService.loadUserByUsername(username);
            return "redirect:/login?error=UserExists";
        }catch (UsernameNotFoundException e){

        }

        // Создаем новый аккаунт
        Account account = new Account();
        account.setName(username);
        account.setPass(password);
        account.setGame("1024");
        accountService.addUser(account); // Сохраняем нового пользователя

        return "redirect:http://localhost:8080/start";
    }

}

