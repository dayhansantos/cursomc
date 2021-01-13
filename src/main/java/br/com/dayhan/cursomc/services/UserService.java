package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.security.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    
    public static UserSecurity authenticated() {
        try {
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
