/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.service;

import com.base.frame.account.core.repository.GroupeRepository;
import com.base.frame.account.core.repository.ProfilRepository;
import com.base.frame.account.core.repository.UtilisateurGroupeRepository;
import com.base.frame.account.dto.ProfilDTO;
import com.base.frame.account.dto.ReinitPasswordConfirmationDTO;
import com.base.frame.account.dto.UserProfilDTO;
import com.base.frame.account.dto.UtilisateurDTO;
import com.base.frame.account.dto.UtilisateurPasswordDTO;
import com.base.frame.account.entity.Utilisateur;
import com.base.frame.account.entity.UtilisateurPassword;
import com.base.frame.account.entity.UtilisateurProfil;
import com.base.frame.account.entity.UtilisateurReinitPassword;
import com.base.frame.account.iservice.IProfilService;
import com.base.frame.account.iservice.IUtilisateurService;
import com.base.frame.account.core.repository.UtilisateurPasswordRepository;
import com.base.frame.account.core.repository.UtilisateurProfilRepository;
import com.base.frame.account.core.repository.UtilisateurReinitPasswordRepository;
import com.base.frame.account.core.repository.UtilisateurRepository;
import com.base.frame.account.dto.GroupesDTO;
import com.base.frame.account.dto.ProfilDTO;
import com.base.frame.account.entity.Groupe;
import com.base.frame.account.entity.Profil;
import com.base.frame.account.entity.UtilisateurGroupe;
import com.base.frame.account.utils.AccountConstant;
import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.iservice.ISocleGenericService;
import com.base.frame.socle.core.repository.ParamListRepository;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.utils.RandomUtil;
import com.base.frame.socle.utils.exceptions.InternalServerException;
import com.base.frame.socle.utils.exceptions.ObjectValidationException;
import com.base.frame.socle.utils.mails.MailService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.LoggerFactory;
import com.base.frame.account.dao.UtilisateurDAO;
import com.base.frame.socle.core.workflow.dto.ActionDTO;
import com.base.frame.socle.core.workflow.dto.WorkFlowCycleActionSimpleObject;
import com.base.frame.socle.core.workflow.dto.WorkFlowCycleEtatSimpleObject;
import com.base.frame.socle.core.workflow.dto.WorkflowCycleParameter;
import com.base.frame.socle.core.workflow.entity.ActionPermise;
import com.base.frame.socle.core.workflow.entity.Etat;
import com.base.frame.socle.core.workflow.repository.ActionPermiseRepository;
import com.base.frame.socle.core.workflow.service.IWorkflowService;
import com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2;

/**
 *
 * @author Alassani
 */
@Service
@Transactional
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    private ISocleGenericService socleGenericService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Autowired
    private UtilisateurPasswordRepository utilisateurPasswordRepository;

    @Autowired
    private UtilisateurReinitPasswordRepository utilisateurReinitPasswordRepository;
    @Autowired
    private UtilisateurProfilRepository utilisateurProfilRepository;
    @Autowired
    private UtilisateurGroupeRepository utilisateurGroupeRepository;
    @Autowired
    private IProfilService profilService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private MailService mailService;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private ProfilRepository profilRepository;
    @Autowired
    private ParamListRepository paramListRepository;
    @Autowired
    private GroupeRepository groupeRepository;
    @Value("${user.default.profile.image}")
    public String userDefaultUserProfile;
    @Autowired
    private ActionPermiseRepository actionPermiseRepository;
    @Autowired
    private IWorkflowService workflowCycleService;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UtilisateurService.class);

    @Override
    public UtilisateurDTO saveUtilisateur(UtilisateurDTO dto) {
        this.controleValidationObjetUtilisateur(dto);
        Utilisateur entity = new Utilisateur();
        if (dto.getId() != null && this.utilisateurRepository.existsById(dto.getId())) {
            entity = this.utilisateurRepository.findById(dto.getId()).get();
        } else {
           
             Etat e = workflowCycleService.getEtatInitial("WKFL_UTILISATEUR");
            entity.setEtat(e);
        
        }
        entity.setUsername(dto.getUsername());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setTitre(dto.getTitre());
        entity.setTel(dto.getTel());
        System.out.println("TEST :" + entity.toString());
        System.out.println("TEST :" + dto.toString());
       
        this.utilisateurRepository.save(entity);
        dto.setId(entity.getId());
        this.saveProfilUtilisateur(dto);
        this.saveGroupeUtilisateur(dto);
        UtilisateurPasswordDTO dto1 = new UtilisateurPasswordDTO();
        dto1.setIdUtilisateur(dto.getId());
        dto1.setConfirmationPassword(dto.getConfirmPassword());
        dto1.setPassword(dto.getPassword());
         saveUtilisateurPassword(dto1);

        return dto;
    }

    private void saveProfilUtilisateur(UtilisateurDTO utilisateurDTO) {

        List<UtilisateurProfil> listeUtisateurProfils = utilisateurProfilRepository.findByUserId(utilisateurDTO.getId());
        if (!listeUtisateurProfils.isEmpty()) {
            for (UtilisateurProfil uf : listeUtisateurProfils) {
                utilisateurProfilRepository.delete(uf);

            }
        }
        if (utilisateurDTO.getProfils() != null) {

            for (ProfilDTO profildto : utilisateurDTO.getProfils()) {
                UtilisateurProfil userProfil = new UtilisateurProfil();
                userProfil.setUtilisateur(this.utilisateurRepository.getOne(utilisateurDTO.getId()));
                Profil profil = profilRepository.getOne(profildto.getId());
                userProfil.setProfil(profil);
                Optional<UtilisateurProfil> uf = utilisateurProfilRepository.findByIdProfilAndIdUser(profildto.getId(), utilisateurDTO.getId());
                if (!uf.isPresent()) {
                    utilisateurProfilRepository.save(userProfil);

                } else {
                    listeUtisateurProfils.remove(uf.get());
                }
            }
        }
    }

    private void saveGroupeUtilisateur(UtilisateurDTO utilisateurDTO) {

        List<UtilisateurGroupe> listeUtisateurGroupes = utilisateurGroupeRepository.findByUserId(utilisateurDTO.getId());
        if (!listeUtisateurGroupes.isEmpty()) {
            for (UtilisateurGroupe ug : listeUtisateurGroupes) {
                utilisateurGroupeRepository.delete(ug);

            }
        }
        if (utilisateurDTO.getGroupes() != null) {

            for (GroupesDTO groupedto : utilisateurDTO.getGroupes()) {
                UtilisateurGroupe userGroupe = new UtilisateurGroupe();
                userGroupe.setUtilisateur(this.utilisateurRepository.getOne(utilisateurDTO.getId()));
                Groupe groupe = groupeRepository.getOne(groupedto.getId());
                userGroupe.setGroupe(groupe);
                Optional<UtilisateurGroupe> ug = utilisateurGroupeRepository.findByIdGroupeAndIdUser(groupedto.getId(), utilisateurDTO.getId());
                if (!ug.isPresent()) {
                    utilisateurGroupeRepository.save(userGroupe);

                } else {
                    listeUtisateurGroupes.remove(ug.get());
                }
            }
        }
    }

    @Override
    public void deleteUtilisateur(String id) {
        if (this.utilisateurRepository.existsById(id)) {
            Optional<UtilisateurPassword> userPassword = this.utilisateurPasswordRepository.findByUserId(id);
            if (userPassword.isPresent()) {
                this.utilisateurPasswordRepository.delete(userPassword.get());
            }
            List<UtilisateurReinitPassword> listReinitPassword
                    = this.utilisateurReinitPasswordRepository.findByUserId(id);
            this.utilisateurReinitPasswordRepository.deleteAll(listReinitPassword);

            List<UtilisateurProfil> listProfil
                    = this.utilisateurProfilRepository.findByUserId(id);
            this.utilisateurProfilRepository.deleteAll(listProfil);

            this.utilisateurRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Utilisateur> findUtilisateurById(String id) {
        return this.utilisateurRepository.findById(id);
    }

    @Override
    public Optional<Utilisateur> findUtilisateurUsername(String username) {
        return this.utilisateurRepository.findByUsername(username);
    }

    @Override
    public boolean existsUtilisateurById(String id) {
        return this.utilisateurRepository.existsById(id);
    }

    @Override
    public UtilisateurDTO getUtilisateur(String id) {
        UtilisateurDTO t = new UtilisateurDTO();
        if (this.utilisateurRepository.existsById(id)) {
            t = this.mapEntityUtilisateurIntoDTO(this.utilisateurRepository.findById(id).get());
        }
        return t;
    }

    @Override
    public Page<UtilisateurDTO> findBySpecTerm(String mc, String username, String firstName, String lastName, String profil, String groupe, String etat, Pageable pageRequest) {
        Page<Utilisateur> listResult;
        listResult = this.utilisateurDAO.findPageUtilisateur(pageRequest, username.toUpperCase(), firstName.toUpperCase(), lastName.toUpperCase(), profil, groupe, etat, mc.toUpperCase());

        return this.mapEntityPageIntoDTOPage(pageRequest, listResult);
    }

    @Override
    public List<UtilisateurDTO> getAllUser() {
        List<Utilisateur> listResult = this.utilisateurRepository.getAllUser();

        return this.mapEntitiesIntoDTOs(listResult);
    }

    public Page<UtilisateurDTO> mapEntityPageIntoDTOPage(Pageable page, Page<Utilisateur> source) {
        List<UtilisateurDTO> list = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(list, page, source.getTotalElements());
    }

    public List<UtilisateurDTO> mapEntitiesIntoDTOs(List<Utilisateur> entities) {
        List<UtilisateurDTO> result = new ArrayList<>();
        entities.stream().map(temp -> this.mapEntityUtilisateurIntoDTO(temp)).forEachOrdered(obj -> {
            result.add(obj);
        });
        return result;
    }

    @Override
    public UtilisateurDTO mapEntityUtilisateurIntoDTO(Utilisateur entity) {
        UtilisateurDTO t = new UtilisateurDTO();
        t.setId(entity.getId());
        t.setUsername(entity.getUsername());
        t.setFirstName(entity.getFirstName());
        t.setFirstAndLastName(entity.getFirstName() +" "+entity.getLastName());
        t.setLastName(entity.getLastName());
        t.setEmail(entity.getEmail());
        t.setEtat(entity.getEtat().getId());
        t.setEtatObject(new WorkFlowCycleEtatSimpleObject(entity.getEtat().getId(), entity.getEtat().getLibelleEtat(), entity.getEtat().getCodeCouleur(), entity.getEtat().getCodeCouleurText()));
        List<ActionDTO> listActionPermises = this.workflowCycleService.recupererActionsSuivantsEtat(entity.getEtat().getId());

        System.out.println("--------------------------");
        System.out.println(listActionPermises);
        System.out.println("--------------------------");
        if (listActionPermises != null && !listActionPermises.isEmpty()) {

            listActionPermises.stream().forEach(acp -> {
                WorkFlowCycleActionSimpleObject wfcaso = new WorkFlowCycleActionSimpleObject(
                        acp.getIdAction(), // ID Action
                        acp.getId(), // ID Action permise
                        this.getCodeEtatActionSuivante(acp.getId()), // ID Action permise
                        acp.getLibelle(),
                        acp.getCodeCouleur(),
                        acp.getCodeCouleurText());

                t.getListActions().add(wfcaso);
            });

        }
        t.setLibelleEtat(entity.getEtat().getLibelleEtat());
        t.setDisabled(true);
        t.setCanEdit(entity.getEtat().getCodeEtat().equalsIgnoreCase(SocleConstant.CODIFICATION_ETAT_ACTIF) ? false : true);
        return t;
    }

    public void controleValidationObjetUtilisateur(UtilisateurDTO dto) {
        System.out.println("ALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        if (dto.getUsername() == null || ("").equalsIgnoreCase(dto.getUsername())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_USER_USERNAME_OBLIG, null);
        }
        if (this.utilisateurRepository.findByUsername(dto.getUsername()).isPresent()) {
            if (dto.getId() == null || ("").equalsIgnoreCase(dto.getId()) || !this.utilisateurRepository.existsById(dto.getId())) {
                throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_USER_USERNAME_EXIST, null);
            } else {
                Utilisateur user = this.utilisateurRepository.findById(dto.getId()).get();
                if (!user.getUsername().equalsIgnoreCase(dto.getUsername())) {
                    throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_USER_USERNAME_EXIST, null);
                }
            }
        }

        if (dto.getFirstName() == null || ("").equalsIgnoreCase(dto.getFirstName())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_USER_NOM_OBLIG, null);
        }
        if (dto.getLastName() == null || ("").equalsIgnoreCase(dto.getLastName())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_USER_PRENOM_OBLIG, null);
        }
        if (dto.getEmail() == null || ("").equalsIgnoreCase(dto.getEmail())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_USER_EMAIL_OBLIG, null);
        }

        if (this.utilisateurRepository.findByEmail(dto.getEmail()).isPresent()) {
            if (dto.getId() == null || ("").equalsIgnoreCase(dto.getId()) || !this.utilisateurRepository.existsById(dto.getId())) {
                throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_USER_EMAIL_EXIST, null);
            } else {
                Utilisateur user = this.utilisateurRepository.findById(dto.getId()).get();
                if (!user.getEmail().equalsIgnoreCase(dto.getEmail())) {
                    throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_USER_EMAIL_EXIST, null);
                }
            }
        }
//        if (dto.getEtat() == null || ("").equalsIgnoreCase(dto.getEtat()) || !this.socleGenericService.existsParamListById(dto.getEtat())) {
//            throw new ObjectValidationException(AccountConstant.MSG_ETAT_OBLIG, null);
//        }
    }

    @Override
    public UtilisateurPasswordDTO saveUtilisateurPassword(UtilisateurPasswordDTO dto) {
        this.controleValidationObjetPasswordUtilisateur(dto);
        UtilisateurPassword entity = new UtilisateurPassword();
        if (this.utilisateurPasswordRepository.findByUserId(dto.getIdUtilisateur()).isPresent()) {
            entity = this.utilisateurPasswordRepository.findByUserId(dto.getIdUtilisateur()).get();
        }
        entity.setUtilisateur(this.findUtilisateurById(dto.getIdUtilisateur()).get());
        entity.setPassword(encoder.encode(dto.getPassword()));
        this.utilisateurPasswordRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public UtilisateurPasswordDTO updateUtilisateurPassword(UtilisateurPasswordDTO dto) {
        this.controleValidationObjetPasswordUtilisateur(dto);
        this.controleValidationObjetPasswordUtilisateurSpecOldPassword(dto);
        UtilisateurPassword entity = new UtilisateurPassword();
        if (this.utilisateurPasswordRepository.findByUserId(dto.getIdUtilisateur()).isPresent()) {
            entity = this.utilisateurPasswordRepository.findByUserId(dto.getIdUtilisateur()).get();
        }
        entity.setUtilisateur(this.findUtilisateurById(dto.getIdUtilisateur()).get());
        entity.setPassword(encoder.encode(dto.getPassword()));
        this.utilisateurPasswordRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public ReinitPasswordConfirmationDTO reinitUtilisateurPasswordConfirmation(ReinitPasswordConfirmationDTO dto) {
        this.controleValidationObjetPasswordReinit(dto);
//        System.out.println("reinitUtilisateurPasswordConfirmation ==== 1 " + dto.toString());
        List<UtilisateurReinitPassword> list = this.utilisateurReinitPasswordRepository.findByReinitCode(dto.getToken(),
                SocleConstant.CODIFICATION_ETAT_ACTIF);
        UtilisateurReinitPassword u = new UtilisateurReinitPassword();

//        System.out.println("reinitUtilisateurPasswordConfirmation ==== 2 " + list.size());
//        System.out.println("reinitUtilisateurPasswordConfirmation ==== 3 " + list.toString());
        for (UtilisateurReinitPassword utilisateurReinitPassword : list) {
//            System.out.println("reinitUtilisateurPasswordConfirmation ==== for =========== " + list.toString());
            if (encoder.matches(utilisateurReinitPassword.getUtilisateur().getUsername(), dto.getUserCode())) {
                u = utilisateurReinitPassword;
//                System.out.println("reinitUtilisateurPasswordConfirmation ==== ENTER =========== " + u.toString());
            }
        }
        if (u == null || u.getId() == null) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_REINIT_INVALIDE, null);
        }
        if (Instant.now().isAfter(u.getDateExpiration())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_REINIT_EXPIRE, null);
        }
        UtilisateurPassword entity = new UtilisateurPassword();
        if (this.utilisateurPasswordRepository.findByUserId(u.getUtilisateur().getId()).isPresent()) {
            entity = this.utilisateurPasswordRepository.findByUserId(u.getUtilisateur().getId()).get();
        }
        entity.setUtilisateur(u.getUtilisateur());
        entity.setPassword(encoder.encode(dto.getPassword()));

        this.utilisateurPasswordRepository.save(entity);
        u.setEtat(this.socleGenericService.findParamListById(SocleConstant.CODIFICATION_ETAT_INACTIF).get());
        return dto;
    }

    public void controleValidationObjetPasswordReinit(ReinitPasswordConfirmationDTO dto) {

        if (dto.getToken() == null || ("").equalsIgnoreCase(dto.getToken())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_REINIT_INVALIDE, null);
        }
        if (dto.getUserCode() == null || ("").equalsIgnoreCase(dto.getUserCode())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_REINIT_INVALIDE, null);
        }
        if (dto.getPassword() == null || ("").equalsIgnoreCase(dto.getPassword())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_REINIT_PASS_OBLIG, null);
        }
        if (dto.getConfirmPassword() == null || ("").equalsIgnoreCase(dto.getConfirmPassword())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_REINIT_CONFIRM_PASS_OBLIG, null);
        }
        if (!dto.getPassword().equalsIgnoreCase(dto.getConfirmPassword())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_REINIT_CONTROLE_PASS_CONFORME, null);
        }

    }

    public void controleValidationObjetPasswordUtilisateur(UtilisateurPasswordDTO dto) {
        if (dto.getIdUtilisateur() == null || ("").equalsIgnoreCase(dto.getIdUtilisateur())
                || !this.utilisateurRepository.existsById(dto.getIdUtilisateur())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_EDIT_USERNAME_OBLIG, null);
        }
        if (dto.getPassword() == null || ("").equalsIgnoreCase(dto.getPassword())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_EDIT_PASS_OBLIG, null);
        }
        if (dto.getConfirmationPassword() == null || ("").equalsIgnoreCase(dto.getConfirmationPassword())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_EDIT_CONFIRM_PASS_OBLIG, null);
        }
        if (!dto.getPassword().equalsIgnoreCase(dto.getConfirmationPassword())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_EDIT_CONTROLE_PASS_CONFORME, null);
        }
    }

    public void controleValidationObjetPasswordUtilisateurSpecOldPassword(UtilisateurPasswordDTO dto) {
        if (dto.getOldPassword() == null || ("").equalsIgnoreCase(dto.getOldPassword())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_EDIT_OLD_PASS_OBLIG, null);
        }
        UtilisateurPassword entity = new UtilisateurPassword();
        if (this.utilisateurPasswordRepository.findByUserId(dto.getIdUtilisateur()).isPresent()) {
            entity = this.utilisateurPasswordRepository.findByUserId(dto.getIdUtilisateur()).get();
            if (!encoder.matches(dto.getOldPassword(), entity.getPassword())) {
//            if (!entity.getPassword().equalsIgnoreCase(encoder.encode(dto.getOldPassword()))) {
                throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_EDIT_OLD_PASS_INCORRECT, null);
            }
        }
    }

    @Override
    public String reinitUtilisateurPassword(String idUtilisateur) {
        //Desactiver les codes actif
        Utilisateur user = this.findUtilisateurById(idUtilisateur).get();
        List<UtilisateurReinitPassword> listRenit
                = this.utilisateurReinitPasswordRepository.findByUserAndCodeStatut(user.getId(), SocleConstant.CODIFICATION_ETAT_ACTIF);
        listRenit.stream().forEach(x -> {
            x.setEtat(this.socleGenericService.findParamListByCode(SocleConstant.CODIFICATION_ETAT_INACTIF).get());
        });
        UtilisateurReinitPassword entity = new UtilisateurReinitPassword();
        entity.setUtilisateur(user);
        entity.setReinitCode(RandomUtil.generateResetKey());
        entity.setDateExpiration(Instant.now().plus(1, ChronoUnit.HOURS));
        entity.setEtat(this.socleGenericService.findParamListByCode(SocleConstant.CODIFICATION_ETAT_ACTIF).get());
        this.sendMail(entity.getReinitCode(), user);
        this.utilisateurReinitPasswordRepository.save(entity);

        return entity.getReinitCode();
    }

    @Override
    public void forgetUtilisateurPassword(String email) {
        if (email != null && this.utilisateurRepository.findByEmail(email).isPresent()) {
            Optional<Utilisateur> user = this.utilisateurRepository.findByEmail(email);
//            if (user.get().getEtat().getCode().equalsIgnoreCase(SocleConstant.CODIFICATION_ETAT_ACTIF)) {
//                this.reinitUtilisateurPassword(user.get().getId());
//            } else {
//                throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_FORGET_USER_INACTIF, null);
//            }
        } else {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PASSWORD_FORGET_EMAIL_INVALIDE, null);
        }
    }

    @Override
    public UserProfilDTO saveUserProfil(UserProfilDTO dto) {
        this.controleValidationObjetUtilisateurProfil(dto);

        List<UtilisateurProfil> listProfil
                = this.utilisateurProfilRepository.findByUserId(dto.getIdUtilisateur());
//        System.out.println("saveUserProfil=============== " + listProfil.toString());
        for (UtilisateurProfil utilisateurProfil : listProfil) {
            this.utilisateurProfilRepository.delete(utilisateurProfil);
        }
//        System.out.println("saveUserProfil 2=============== " + this.utilisateurProfilRepository.findByUserId(dto.getIdUtilisateur()).toString());
        for (ProfilDTO profilDTO : dto.getListProfil()) {
            UtilisateurProfil entity = new UtilisateurProfil();
            if (this.utilisateurProfilRepository.findByIdProfilAndIdUser(profilDTO.getId(), dto.getIdUtilisateur()).isPresent()) {
                entity = this.utilisateurProfilRepository.findByIdProfilAndIdUser(profilDTO.getId(), dto.getIdUtilisateur()).get();
            }
            entity.setUtilisateur(this.findUtilisateurById(dto.getIdUtilisateur()).get());
            entity.setProfil(this.profilService.findById(profilDTO.getId()).get());
            this.utilisateurProfilRepository.save(entity);
        }
        dto.setDisabled(true);
        return dto;
    }

    public void controleValidationObjetUtilisateurProfil(UserProfilDTO dto) {
        if (dto.getIdUtilisateur() == null || ("").equalsIgnoreCase(dto.getIdUtilisateur()) || !this.existsUtilisateurById(dto.getIdUtilisateur())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_ASSOCIATION_USER_PROFIL_USER_OBLIG, null);
        }
        for (ProfilDTO profilDTO : dto.getListProfil()) {
            if (profilDTO.getId() == null || ("").equalsIgnoreCase(profilDTO.getId()) || !this.profilService.existsById(profilDTO.getId())) {
                throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_ASSOCIATION_USER_PROFIL_PROFIL_OBLIG, null);
            }
        }
    }

    @Override
    public UserProfilDTO getUserProfil(String idUser) {
        UserProfilDTO dto = new UserProfilDTO();
        if (this.existsUtilisateurById(idUser)) {
            dto.setIdUtilisateur(idUser);
            dto.setListProfil(this.profilService.mapEntitiesIntoDTOs(this.utilisateurProfilRepository.findProfilByUserId(idUser)));
            dto.setDisabled(true);
        }
        return dto;
    }

    @Override
    public Page<UtilisateurDTO> findByProfilPaginate(Pageable pageRequest, String idProfil) {
        Page<Utilisateur> listResult = this.utilisateurProfilRepository.findByProfilPaginate(pageRequest, idProfil);
        return this.mapEntityPageIntoDTOPage(pageRequest, listResult);
    }

    @Override
    public List<ProfilDTO> loadingProfil() {
        List<ProfilDTO> pro = new ArrayList<>();

        List<Profil> profil = profilRepository.findProfilByStatus("PROFIL002");

        profil.forEach(it -> {
            ProfilDTO profilDTO = new ProfilDTO();
            profilDTO.setId(it.getId());
            profilDTO.setIntitule(it.getIntitule());
            profilDTO.setCode(it.getCode());
            pro.add(profilDTO);
        });
        //Optional<ParamList> statut = paramListRepository.findParamListByCode("PARAM_LIST_ETAT_AC");
//                .STATUT_PARAM,
//                CodificationConstant.STATUT_PARAM_ACTIF);

        //profil = profil.stream().filter(p -> p.getEtat() != null && p.getEtat().getId() == statut.get().getId()).collect(Collectors.toList());
        return pro;
    }

    public List<Groupe> listGroupes() {
        //ProfilRepository profils = null ;
        List<Groupe> groupe = groupeRepository.findAll();
        Optional<ParamList> statut = paramListRepository.findParamListByCode("PARAM_LIST_ETAT_AC");
        groupe = groupe.stream().filter(g -> g.getEtat() != null && g.getEtat().getId() == statut.get().getId()).collect(Collectors.toList());
        return groupe;
    }

    public void sendMail(String code, Utilisateur u) {

        Map<String, Object> mp = new HashMap<>();
        mp.put("code", code);
        mp.put("userCode", encoder.encode(u.getUsername()));
        this.mailService.sendEmailFromTemplate(u.getEmail(), u, "reinitPasswordEmail", "Réinitialisation de mot de passe", "fr", httpServletRequest.getContextPath() + "/password/reinit", mp);
    }

    public byte[] getUserDeafultPicture() {
        return this.getDefaultProfileFile();
    }

    public byte[] getDefaultProfileFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        LOG.info("User defaut profile path ssss : " + this.userDefaultUserProfile);
        File file = new File(classLoader.getResource(this.userDefaultUserProfile).getFile());
        if (this.userDefaultUserProfile != null && file.exists()) {
            byte[] bytesArray = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                LOG.info("read file into bytes[]" + fis.read(bytesArray));
                return bytesArray;
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new byte[0];
    }

    public String getCodeEtatActionSuivante(String idActionPermise) {
        String result;
        ActionPermise ap = this.actionPermiseRepository.getOne(idActionPermise);

        if (ap.getEtatSuivant() != null && ap.getEtatSuivant().getCodeEtat() != null) {
            result = ap.getEtatSuivant().getCodeEtat();

        } else {
            result = ap.getEtat().getCodeEtat();
        }
        return result;
    }

    public void forwardWorkflowUtilisateur(String idActionPermise, String idObjet, String commentaire) {
        System.out.println("forwardWorkflowProfil");
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(idObjet);

        if (utilisateur.isPresent()) {
            Optional<ActionPermise> apOp = this.actionPermiseRepository.findById(idActionPermise);
            System.out.println("0forwardWorkflowProfil");
            if (apOp.isPresent()) {
                System.out.println("1forwardWorkflowProfil");
                Etat e = apOp.get().getEtatSuivant();
                WorkflowCycleParameter p = new WorkflowCycleParameter();
                if (idActionPermise != null) {
                    System.out.println("2forwardWorkflowProfil");
                    List<UtilisateurDTOV2> users = workflowCycleService.findUserByAction(idActionPermise);
                    if (users != null && !users.isEmpty()) {
                        System.out.println("3forwardWorkflowProfil");
                        p.setUsers(this.getUserName(users).stream().toArray(String[]::new));
                    } else {
                        System.out.println("4forwardWorkflowProfil");
                        p.setUsers(new ArrayList<>().stream().toArray(String[]::new));
                    }
                    p.setIdObjet(idObjet);
                    //p.setConcepMetier(CgeNomenclatureConstant.CONCEPT_METIER_OPPOSITION);
                    String codeFonction = "FonctionProfil";
                    p.setIdAction(idActionPermise);
                    p.setCommentaire(apOp.get().getEtat().getLibelleEtat() + " --> " + e.getLibelleEtat());
                    System.out.println("forwardWorkflowProfil");
                    this.workflowCycleService.executerAction(e.getId(), idObjet, idActionPermise, codeFonction, commentaire, p.getUsers());
                    Utilisateur user = utilisateur.get();
                    user.setEtat(e);

                    this.utilisateurRepository.save(user);

                }
            } else {
            }
        } else {
        }
    }

    public List<String> getUserName(List<UtilisateurDTOV2> users) {
        List<String> usernames = new ArrayList<>();
        users.stream().forEach(t -> {
            usernames.add(t.getUsername());
        });
        return usernames;
    }
}
