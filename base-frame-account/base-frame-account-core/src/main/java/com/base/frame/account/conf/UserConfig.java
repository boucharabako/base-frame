/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.conf;

import com.base.frame.account.dto.HabilitationDTO;
import com.base.frame.account.dto.ProfilDTO;
import com.base.frame.account.dto.UtilisateurDTO;
import com.base.frame.account.dto.UtilisateurPasswordDTO;
import com.base.frame.account.entity.Profil;
import com.base.frame.account.entity.Utilisateur;
import com.base.frame.account.entity.UtilisateurPassword;
import com.base.frame.account.entity.UtilisateurProfil;
import com.base.frame.account.iservice.IProfilService;
import com.base.frame.account.iservice.IUtilisateurService;
import com.base.frame.account.core.repository.HabilitationRepository;
import com.base.frame.account.core.repository.UtilisateurPasswordRepository;
import com.base.frame.account.core.repository.UtilisateurProfilRepository;
import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.iservice.ISocleGenericService;
import com.base.frame.socle.core.repository.ParamListRepository;
import com.base.frame.socle.core.utils.SocleConstant;
import java.util.Optional;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Alassani
 */
//@Configuration
@ConfigurationProperties(prefix = "system")
public class UserConfig {

    private static final Logger LOG = Logger.getLogger(UserConfig.class.getName());

    private final Utilisateur user = new Utilisateur();
    private final UtilisateurPassword userPassword = new UtilisateurPassword();
    private final Profil profil = new Profil();

    @Autowired
    private IProfilService profilService;
    @Autowired
    private IUtilisateurService utilisateurService;
    @Autowired
    private ISocleGenericService socleGenericService;
    @Autowired
    private HabilitationRepository habilitationRepository;
    @Autowired
    private UtilisateurPasswordRepository utilisateurPasswordRepository;
    @Autowired
    private UtilisateurProfilRepository utilisateurProfilRepository;
    @Autowired
    private ParamListRepository paramListRepository;

    @Autowired
    public Utilisateur getUser() {
        return user;
    }

    @Autowired
    public UtilisateurPassword getUserPassword() {
        return userPassword;
    }

    @Autowired
    public Profil getProfil() {
        return profil;
    }

    @PostConstruct
    public void init() {
        System.out.println("Alassani: "+getProfil());
        if (profil.getCode() != null && !profil.getCode().isEmpty()
                && profil.getIntitule() != null && !profil.getIntitule().isEmpty()) {
            System.out.println("111111111111111111");
            ProfilDTO profilDTO = new ProfilDTO();
            if (this.profilService.existsByCode(profil.getCode())) {
                profilDTO.setId(this.profilService.findByCode(profil.getCode()).get().getId());
            }
            System.out.println("222222222222222222");
            profilDTO.setCode(profil.getCode());
            profilDTO.setIntitule(profil.getIntitule());
            profilDTO.setDescription(profil.getDescription());
            profilDTO.setEtat(SocleConstant.CODIFICATION_ETAT_ACTIF);
            System.out.println("3333333333333333333");
            ProfilDTO profilDTO2 = this.profilService.save(profilDTO);
            System.out.println("444444444444444444444");

            for (String s : profil.getListHabilitation()) {
                System.out.println("55555555555555555555555555:"+s);
                if (this.socleGenericService.existsFonctionByCode(s)) {
                    if (!this.habilitationRepository.findByProfilAndFonction(profilDTO2.getId(), s).isPresent()) {
                        HabilitationDTO h = new HabilitationDTO();
                        h.setCodeFonction(s);
                        h.setIdProfil(profilDTO2.getId());
                        System.out.println("666666666666666666666666");
                        h.setIdNiveauHabilitation(SocleConstant.CODIFICATION_NIVEAU_HABILITATION_SAISIR);
                        this.profilService.saveHabilitation(h);
                    }
                }
            }

            System.out.println("NGS USER " + user.getUsername());
            if (user.getUsername() != null && !user.getUsername().isEmpty()
                    && user.getEmail() != null && !user.getEmail().isEmpty()
                    && user.getFirstName() != null && !user.getFirstName().isEmpty()
                    && user.getLastName() != null && !user.getLastName().isEmpty()) {
                UtilisateurDTO u = new UtilisateurDTO();

                if (this.utilisateurService.findUtilisateurUsername(user.getUsername()).isPresent()) {
                    u.setId(this.utilisateurService.findUtilisateurUsername(user.getUsername()).get().getId());
                }
                u.setUsername(user.getUsername());
                u.setEmail(user.getEmail());
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                System.out.println("gggggggggggggggg");
                Optional<ParamList>  etat = paramListRepository.findParamListByCode(/*SocleConstant.CODIFICATION_ETAT_ACTIF*/"PARAM_LIST_ETAT_AC");
                u.setStatut(etat.get());
                UtilisateurDTO u2 = this.utilisateurService.saveUtilisateur(u);

                if (userPassword.getPassword() != null && !userPassword.getPassword().isEmpty()) {
                    UtilisateurPasswordDTO userPass = new UtilisateurPasswordDTO();
                    if (this.utilisateurPasswordRepository.findByUserId(u2.getId()).isPresent()) {
                        userPass.setId(this.utilisateurPasswordRepository.findByUserId(u2.getId()).get().getId());
                    }
                    userPass.setIdUtilisateur(u2.getId());
                    userPass.setPassword(userPassword.getPassword());
                    userPass.setConfirmationPassword(userPassword.getPassword());
                    this.utilisateurService.saveUtilisateurPassword(userPass);
                }
                Profil p = this.profilService.findById(profilDTO2.getId()).get();
                Utilisateur uEntity = this.utilisateurService.findUtilisateurById(u2.getId()).get();
                if (!this.utilisateurProfilRepository.findByIdProfilAndIdUser(p.getId(), uEntity.getId()).isPresent()) {
                    UtilisateurProfil up = new UtilisateurProfil(uEntity, p);
                    this.utilisateurProfilRepository.save(up);
                }
            }
        }

    }

}
