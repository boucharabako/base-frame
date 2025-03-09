/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.service;


import com.base.frame.account.core.repository.HabilitationRepository;
import com.base.frame.account.core.repository.ProfilRepository;
import com.base.frame.account.core.repository.UtilisateurProfilRepository;
import com.base.frame.account.core.repository.UtilisateurRepository;
import com.base.frame.account.entity.Profil;
import com.base.frame.account.entity.Utilisateur;
import com.base.frame.account.utils.AccountConstant;
import com.base.frame.httpsession.security.SecurityUtils;
import com.base.frame.socle.core.codification.annotation.dao.GenericDAO;
import com.base.frame.socle.core.dto.IdLabelObject;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2;
import com.base.frame.socle.core.workflow.service.ICustomUtilService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Cette classe sert de controlleur pour les pages de gestion d'utilisateur
 *
 * @author Adnaane
 * @version 1.0.0
 * @since 27/02/2018
 *
 */
@Service
public class CustomUtilServiceImpl extends GenericDAO<IdLabelObject, String> implements ICustomUtilService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CustomUtilServiceImpl.class);

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private UtilisateurProfilRepository utilisateurProfilRepository;
    @Autowired
    private HabilitationRepository habilitationFonctionnelleRepository;

    public CustomUtilServiceImpl() {
        super(IdLabelObject.class);
    }

    @Override
    public List<IdLabelObject> listProfils() {
        List<IdLabelObject> listProfil = new ArrayList<>();
           List<Profil> listProfils =      this.profilRepository.findProfilByStatus(SocleConstant.STATUT_PARAM_ACTIF);
           listProfils.forEach(pr->{
               IdLabelObject idLabelObject = new IdLabelObject(pr.getId(), pr.getIntitule());
               listProfil.add(idLabelObject);
           });
        return listProfil == null ? new ArrayList<>() : listProfil;
    }

    @Override
    public Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();

                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }

    @Override
    public boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
                .orElse(false);
    }

  

    @Override
    public List<String> getAuthorities() {
        List<String> roles = new ArrayList<>();
        SecurityUtils.getRoles().forEach(r -> {
            roles.add(r.getAuthority());
        });
        return roles;
    }

    @Override
    public List<String> getCurrentUserProfil() {
        Optional<String> u = getCurrentUserLogin();
        List<String> profilsId = new ArrayList<>();
        if (u.isPresent()) {
            utilisateurProfilRepository.findProfilByUserId(u.get()).forEach(it->{
                profilsId.add(it.getId());
            });
            return profilsId;
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getProfilByUserLogin(String userLogin) {
        List<String> profilsId = new ArrayList<>();
        if (userLogin != null && !userLogin.isEmpty()) {
            utilisateurProfilRepository.findProfilByUserId(utilisateurRepository.findByUsername(userLogin).get().getId()).forEach(it->{
                profilsId.add(it.getId());
            });
            return profilsId;
        }
        return new ArrayList<>();
    }

    @Override
    public List<UtilisateurDTOV2> getUsersByUsernames(Object[] usernames) {
        if (usernames.length == 0) {
            return new ArrayList<>();
        }
         List<UtilisateurDTOV2> l = new ArrayList<>();
        for(Object o: usernames){
            Optional<Utilisateur> u = utilisateurRepository.findByUsername(o.toString());
            UtilisateurDTOV2 dto = new UtilisateurDTOV2(u.get().getId(), u.get().getUsername(), u.get().getFirstName(), u.get().getLastName());
            dto.setEmail(u.get().getEmail());
            l.add(dto);
        }
       
        l.forEach(e -> {
            List<String> profils = new ArrayList<>();
            utilisateurProfilRepository.findProfilByUserId(e.getUsername()).forEach(it->{
                profils.add(it.getId());
            });
            
            
            e.setProfil(profils);
        });
        return l;
    }

    @Override
    public List<UtilisateurDTOV2> getUserByUsernames(Object[] username) {
        if (username == null || username.length == 0) {
            return new ArrayList<>();
        }

        List<UtilisateurDTOV2> l = new ArrayList<>();
        for(Object o: username){
            Optional<Utilisateur> u = utilisateurRepository.findByUsername(o.toString());
            UtilisateurDTOV2 dto = new UtilisateurDTOV2(u.get().getId(), u.get().getUsername(), u.get().getFirstName(), u.get().getLastName());
            dto.setEmail(u.get().getEmail());
            l.add(dto);
        }
         l.forEach(e -> {
            List<String> profils = new ArrayList<>();
            utilisateurProfilRepository.findProfilByUserId(e.getUsername()).forEach(it->{
                profils.add(it.getId());
            });
            
            
            e.setProfil(profils);
        });
        return l;

    }

    @Override
    public List<UtilisateurDTOV2> getUserByProfils(Object[] profils) {
        if (profils == null || profils.length == 0) {
            return new ArrayList<>();
        }
        //List<Profil> listProfils =      this.profilRepository.findProfilByStatus(SocleConstant.STATUT_PARAM_ACTIF);
        List<UtilisateurDTOV2> l = utilisateurProfilRepository.findUtilisateurDTOByProfils(profils,
                SocleConstant.STATUT_PARAM_ACTIF);
        l.forEach(e -> {
            e.setProfil(utilisateurProfilRepository.findProfilByUtilisateurId(e.getUsername()));
        });

        return l.stream().filter(distinctByKey(p -> p.getUsername())).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDTOV2> getUserByProfil(String profil) {

        List<UtilisateurDTOV2> l = utilisateurProfilRepository.findUtilisateurDTOByProfil(profil);
        l.forEach(e -> {
            e.setProfil(utilisateurProfilRepository.findProfilByUtilisateurId(e.getUsername()));
        });
        return l.stream().filter(distinctByKey(p -> p.getUsername())).collect(Collectors.toList());
    }

    /**
     * Revoie le niveau d'habilitation en fonction de la Function et les profils
     * de l'utilisateur exemple code de function ("REF_NOMEN_E00")
     *
     * @param codeFunction
     * @return
     */
    @Override
    public String findNiveauHabilitationOfUserByFunction(String codeFunction) {
        String niveauHab = null;
        if (SecurityUtils.getCurrentUserLogin().isPresent() && codeFunction != null) {
            List<String> listProfilId = this.utilisateurProfilRepository.findProfilByUtilisateurId(SecurityUtils.getCurrentUserLogin().get());
            if (listProfilId != null) {
                //niveauHab = this.habilitationFonctionnelleRepository.findNiveauHabByProfilANdFonction(codeFunction, listProfilId.toArray());
            }
        }
        return niveauHab;
    }

    @Override
    public String findNiveauHabilitationByUserLoginAndFunction(String codeFunction, String userLogin) {
        String niveauHab = null;
        if (userLogin != null && !userLogin.isEmpty() && codeFunction != null) {
            List<String> listProfilId = this.utilisateurProfilRepository.findProfilByUtilisateurId(userLogin);
            if (listProfilId != null) {
                //niveauHab = this.habilitationFonctionnelleRepository.findNiveauHabByProfilANdFonction(codeFunction, listProfilId.toArray());
            }
        }
        return niveauHab;
    }

    /**
     * Retourne TRUE si le niveau d'habilitation de l'utilisateur est suppérieur
     * ou égale au niveau d'habilitation configuré sur le workflow FALSE dans
     * les autres CAS
     *
     * @param niveauHabilitationActionWKF
     * @param codeFunction
     * @return
     */
    @Override
    public boolean userHasHabilitationConfiguredOnWkfAction(String niveauHabilitationActionWKF, String codeFunction) {
        try {
            return Integer.valueOf(this.findNiveauHabilitationOfUserByFunction(codeFunction)) >= Integer.valueOf(niveauHabilitationActionWKF);
        } catch (NumberFormatException e) {
            LOG.error(e.getLocalizedMessage(), e);
        }
        return false;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
