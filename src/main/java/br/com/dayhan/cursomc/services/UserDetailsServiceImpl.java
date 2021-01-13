package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.repositories.ClienteRepository;
import br.com.dayhan.cursomc.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) {
        final var cliente = clienteRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new UserSecurity(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
    }
}
