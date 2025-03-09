/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.service;

import com.base.frame.account.entity.Habilitation;
import com.base.frame.account.entity.Profil;
import com.base.frame.account.entity.Utilisateur;
import com.base.frame.account.entity.UtilisateurPassword;
import com.base.frame.account.exceptions.UserNotActivatedException;
import com.base.frame.account.iservice.ILoginService;
import com.base.frame.account.core.repository.HabilitationRepository;
import com.base.frame.account.core.repository.UtilisateurPasswordRepository;
import com.base.frame.account.core.repository.UtilisateurProfilRepository;
import com.base.frame.account.core.repository.UtilisateurRepository;
import com.base.frame.socle.core.entity.Fonction;
import com.base.frame.socle.core.repository.FonctionRepository;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.core.utils.Util;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alassani
 */
@Service
@Transactional
public class LoginService implements ILoginService{

    public static final Integer MAX_AUTHORITY = 3;

    @Autowired
    private UtilisateurRepository userRepository;
    @Autowired
    private UtilisateurProfilRepository utilisateurProfilRepository;

    @Autowired
    private HabilitationRepository habilitationRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    UtilisateurPasswordRepository utilisateurPasswordRepository;

    private static final String USER = "User ";
    private static final String WAS_NOT_FOUND = " was not found";
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoginService.class);

    public User loadUserDetail(final String login) {

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<Utilisateur> userFromDatabase = userRepository.findByUsername(lowercaseLogin);
        return userFromDatabase.map(user -> {
            Set<String> authorities = new HashSet<>();

            if (!user.getUsername().equals(login) && !user.getEmail().equals(login)) {
                throw new UserNotActivatedException(USER + lowercaseLogin + " WAS_NOT_FOUND");
            }
//            if (!user.getEtat().getCode().equals(SocleConstant.CODIFICATION_ETAT_ACTIF)) {
//                throw new UserNotActivatedException(USER + lowercaseLogin + " WAS_NOT_ACTIVATED");
//            }
            Optional<UtilisateurPassword> up = utilisateurPasswordRepository.findByUserId(user.getId());
            if (!up.isPresent()) {
                throw new UserNotActivatedException(USER + lowercaseLogin + " PWD_WAS_NOT_FOUND");
            }
            List<Profil> lps = utilisateurProfilRepository.findProfilByUserId(user.getId());

            lps.forEach(p -> {
                System.out.println("======== p" + p.toString());
                List<Habilitation> hb = habilitationRepository.findByProfil(p.getId());

                hb.stream().filter(x -> x.getFonction() != null && (x.getFonction().getCode() != null))
                        .forEach((a) -> {
                            System.out.println("======== a" + a.toString());
                            authorities.add(a.getFonction().getCode());
                            Integer i = Integer.valueOf(a.getNiveauHabilitation().getCode());
                            Util.findChildren(i).stream().forEach(j -> {
                                authorities.add(a.getFonction().getCode() + "." + j);
                            });

                            String parent = a.getFonction().getParent();

                            while (!(parent == null || parent.isEmpty())) {
                                Optional<Fonction> pa = fonctionRepository.findById(parent);
                                if (pa.isPresent()) {
                                    authorities.add(pa.get().getCode());
                                    parent = pa.get().getParent();
                                } else {
                                    parent = null;
                                }
                            }

                        });

            });
            authorities.add("CONNECTED");

            LOG.info("" + authorities);
            user.setAuthorities(authorities);

            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority))
                    .collect(Collectors.toList());
                return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    up.get().getPassword(),
                    grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException(USER + lowercaseLogin + " was not found in the "
                + "database"));
    }
    
    public List<GrantedAuthority> loadAuthorisation(final String login) {

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<Utilisateur> userFromDatabase = userRepository.findByUsername(lowercaseLogin);
        return userFromDatabase.map(user -> {
            Set<String> authorities = new HashSet<>();

            if (!user.getUsername().equals(login) && !user.getEmail().equals(login)) {
                throw new UserNotActivatedException(USER + lowercaseLogin + " WAS_NOT_FOUND");
            }
            //if (!user.getEtat().getCode().equals(SocleConstant.CODIFICATION_ETAT_ACTIF)) {
//                throw new UserNotActivatedException(USER + lowercaseLogin + " WAS_NOT_ACTIVATED");
//            }
            Optional<UtilisateurPassword> up = utilisateurPasswordRepository.findByUserId(user.getId());
            if (!up.isPresent()) {
                throw new UserNotActivatedException(USER + lowercaseLogin + " PWD_WAS_NOT_FOUND");
            }
            List<Profil> lps = utilisateurProfilRepository.findProfilByUserId(user.getId());

            lps.forEach(p -> {
                System.out.println("======== p" + p.toString());
                List<Habilitation> hb = habilitationRepository.findByProfil(p.getId());

                hb.stream().filter(x -> x.getFonction() != null && (x.getFonction().getCode() != null))
                        .forEach((a) -> {
                            System.out.println("======== a" + a.toString());
                            authorities.add(a.getFonction().getCode());
                            Integer i = Integer.valueOf(a.getNiveauHabilitation().getCode());
                            Util.findChildren(i).stream().forEach(j -> {
                                authorities.add(a.getFonction().getCode() + "." + j);
                            });

                            String parent = a.getFonction().getParent();

                            while (!(parent == null || parent.isEmpty())) {
                                Optional<Fonction> pa = fonctionRepository.findById(parent);
                                if (pa.isPresent()) {
                                    authorities.add(pa.get().getCode());
                                    parent = pa.get().getParent();
                                } else {
                                    parent = null;
                                }
                            }

                        });

            });
            authorities.add("CONNECTED");

            LOG.info("" + authorities);
            user.setAuthorities(authorities);

            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority))
                    .collect(Collectors.toList());
            return grantedAuthorities;
        }).orElseThrow(() -> new UsernameNotFoundException(USER + lowercaseLogin + " was not found in the "
                + "database"));
    }
}
