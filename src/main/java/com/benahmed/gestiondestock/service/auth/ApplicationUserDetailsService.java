package com.benahmed.gestiondestock.service.auth;

import com.benahmed.gestiondestock.DTO.UtilisateurDto;
import com.benahmed.gestiondestock.model.auth.ExtendedUser;
import com.benahmed.gestiondestock.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurService utilisateurService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateur = utilisateurService.findByEmail(email);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new ExtendedUser(utilisateur.getEmail(), utilisateur.getPassword(), authorities,
                utilisateur.getEntreprise().getId());
    }
}
