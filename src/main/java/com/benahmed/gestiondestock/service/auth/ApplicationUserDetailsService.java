package com.benahmed.gestiondestock.service.auth;

import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.NotAuthorized;
import com.benahmed.gestiondestock.model.Utilisateur;
import com.benahmed.gestiondestock.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByEmail(email).orElseThrow(()->
                new NotAuthorized("Vous n etes pas autorise", ErrorCodes.UTILISATEUR_NOT_AUTHORIZED)
        );

        return new User(utilisateur.getEmail(), utilisateur.getPassword(), Collections.emptyList());
    }
}
