/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.service;

import com.base.frame.account.dto.HabilitationDTO;
import com.base.frame.account.dto.ProfilDTO;
import com.base.frame.account.entity.Habilitation;
import com.base.frame.account.entity.Profil;
import com.base.frame.account.iservice.IProfilService;
import com.base.frame.account.core.repository.HabilitationRepository;
import com.base.frame.account.core.repository.ProfilRepository;
import com.base.frame.account.core.repository.UtilisateurProfilRepository;
import com.base.frame.account.dao.ProfilDAO;
import com.base.frame.account.utils.AccountConstant;
import com.base.frame.socle.core.iservice.ISocleGenericService;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.core.workflow.dto.ActionDTO;
import com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2;
import com.base.frame.socle.core.workflow.dto.WorkFlowCycleActionSimpleObject;
import com.base.frame.socle.core.workflow.dto.WorkFlowCycleEtatSimpleObject;
import com.base.frame.socle.core.workflow.dto.WorkflowCycleParameter;
import com.base.frame.socle.core.workflow.entity.ActionPermise;
import com.base.frame.socle.core.workflow.entity.Etat;
import com.base.frame.socle.core.workflow.repository.ActionPermiseRepository;
import com.base.frame.socle.core.workflow.service.IWorkflowCycleService;
import com.base.frame.socle.core.workflow.service.IWorkflowService;
import com.base.frame.socle.utils.exceptions.InternalServerException;
//import com.base.frame.socle.core.workflow.service.WorkflowCycleService;

import com.base.frame.socle.utils.exceptions.ObjectValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alassani
 */
@Service
@Transactional
public class ProfilService implements IProfilService {

    @Autowired
    private ProfilRepository profilRepository;
    @Autowired
    private ISocleGenericService socleGenericService;
    @Autowired
    private HabilitationRepository habilitationRepository;

    @Autowired
    private UtilisateurProfilRepository utilisateurProfilRepository;
    @Autowired
    private IWorkflowService workflowCycleService;
    @Autowired
    private ActionPermiseRepository actionPermiseRepository;
    @Autowired
    private ProfilDAO profilDAO;
    public void verifier(){
        //throw new ObjectValidationException("this.codificationService.findMessage(CodificationConstant.UNKNOWN_EXCEPTION)", null);
    }
    @Override
    public ProfilDTO save(ProfilDTO dto) {
        this.controleValidationObjet(dto);
        Profil entity = new Profil();
        if (dto.getId() != null && this.profilRepository.existsById(dto.getId())) {
            entity = this.profilRepository.findById(dto.getId()).get();
            
        }else{
             Etat e = workflowCycleService.getEtatInitial("WKFL_PROFIL");
            entity.setEtat(e);
        }
       
        entity.setCode(dto.getCode());
        entity.setIntitule(dto.getIntitule());
        entity.setDescription(dto.getDescription());
        //entity.setEtat(this.socleGenericService.findParamListById(dto.getEtat()).get());
//        entity.setEtat(Etat.valueOf(dto.getEtat()));
        this.profilRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public void delete(String id) {
        if (this.profilRepository.existsById(id)) {
            if (!this.utilisateurProfilRepository.findByProfilId(id).isEmpty()) {
                throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PROFIL_DELETE_CONTROLE_PROFILE_USE, null);
            }
            List<Habilitation> listHabilitation = this.habilitationRepository.findByProfil(id);
            this.habilitationRepository.deleteAll(listHabilitation);
            this.profilRepository.deleteById(id);

        }
    }

    @Override
    public Optional<Profil> findById(String id) {
        return this.profilRepository.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return this.profilRepository.existsById(id);
    }

    @Override
    public Optional<Profil> findByCode(String code) {
        return this.profilRepository.findProfilByCode(code);
    }

    @Override
    public boolean existsByCode(String code) {
        return this.profilRepository.findProfilByCode(code).isPresent();
    }

    @Override
    public Page<ProfilDTO> findBySpecTerm(Optional<String> mc, PageRequest pageRequest) {
        Page<Profil> listResult;
        if (mc.isPresent()) {
            listResult = this.profilDAO.findPageProfil(pageRequest, mc.get().toUpperCase());
        } else {
            listResult = this.profilRepository.findProfil(pageRequest);
        }
        
        verifier();
        return this.mapEntityPageIntoDTOPage(pageRequest, listResult);
    }

    @Override
    public List<ProfilDTO> findProfilByStatut(String codeStatut) {
        List<Profil> listResult = this.profilRepository.findProfilByStatus(codeStatut);
        return this.mapEntitiesIntoDTOs(listResult);
    }

    @Override
    public List<ProfilDTO> findProfilCanAddByStatut(String codeStatut, String idUser) {
        List<Profil> listResult = this.profilRepository.findProfilByStatus(codeStatut, idUser);
        return this.mapEntitiesIntoDTOs(listResult);
    }

    public Page<ProfilDTO> mapEntityPageIntoDTOPage(Pageable page, Page<Profil> source) {
        List<ProfilDTO> list = mapEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(list, page, source.getTotalElements());
    }

    @Override
    public List<ProfilDTO> mapEntitiesIntoDTOs(List<Profil> entities) {
        List<ProfilDTO> result = new ArrayList<>();
        entities.stream().map(temp -> this.mapEntityIntoDTO(temp)).forEachOrdered(obj -> {
            result.add(obj);
        });
        return result;
    }

    @Override
    public ProfilDTO getProfil(String id) {
        ProfilDTO t = new ProfilDTO();
        if (this.profilRepository.existsById(id)) {
            t = this.mapEntityIntoDTO(this.profilRepository.findById(id).get());
        }
        return t;
    }

    @Override
    public ProfilDTO mapEntityIntoDTO(Profil entity) {
        //throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PROFIL_DELETE_CONTROLE_PROFILE_USE, null);
        System.out.println("****************************" + entity.toString());
        ProfilDTO t = new ProfilDTO();
        t.setId(entity.getId());
        t.setCode(entity.getCode());
        t.setIntitule(entity.getIntitule());
        t.setDescription(entity.getDescription());
        t.setEtat(entity.getEtat().getId());
        t.setEtatObject(new WorkFlowCycleEtatSimpleObject(entity.getEtat().getId(), entity.getEtat().getLibelleEtat(), entity.getEtat().getCodeCouleur(), entity.getEtat().getCodeCouleurText()));
        List<ActionDTO> listActionPermises = this.workflowCycleService.recupererActionsSuivantsEtat(entity.getEtat().getId());

        System.out.println("--------------------------");
        System.out.println(listActionPermises);
        System.out.println("--------------------------");
        if (listActionPermises != null && !listActionPermises.isEmpty()) {
//            if (listActionPermises.size() == 1) {
//                t.setActionObject(new WorkFlowCycleActionSimpleObject(
//                        listActionPermises.get(0).getIdAction(),//ID Action
//                        listActionPermises.get(0).getId(), // ID Action permise
//                        this.getCodeEtatActionSuivante(listActionPermises.get(0).getId()), // ID Action permise
//                        listActionPermises.get(0).getLibelle(),
//                        listActionPermises.get(0).getCodeCouleur(),
//                        listActionPermises.get(0).getCodeCouleurText()));
//            }
            //oppDto.setEtatHasOnlyOneAction(false);
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
//        t.setCanEdit(entity.getEtat().equals(Etat.ACTIF) ? false : true);
        t.toString();
        return t;
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

    @Override
    public List<String> getListEtat() {
        List<String> result = new ArrayList<>();
//        for (Etat value : Etat.values()) {
//            result.add(value.name());
//        }
        return result;
    }

    public void controleValidationObjet(ProfilDTO dto) {
        System.out.println("aaaaaaaaaaaaaaaaaaa");
        if (dto.getCode() == null || ("").equalsIgnoreCase(dto.getCode())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PROFIL_CODE_OBLIG, null);
        }
        System.out.println("bbbbbbbbbbbbbbbbbb");

        if (this.profilRepository.findProfilByCode(dto.getCode()).isPresent()) {
            if (dto.getId() == null || ("").equalsIgnoreCase(dto.getId()) || !this.profilRepository.existsById(dto.getId())) {
                throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PROFIL_CODE_USE, null);

            } else {
                Profil pro = this.profilRepository.findById(dto.getId()).get();
                if (!pro.getCode().equalsIgnoreCase(dto.getCode())) {
                    throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PROFIL_CODE_USE, null);
                }
            }
        }
        System.out.println("11111111111111111111111: " + dto.getIntitule());
        if (dto.getIntitule() == null || ("").equalsIgnoreCase(dto.getIntitule())) {
            System.out.println("22222222222222222222222222: " + dto.getIntitule());
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_PROFIL_INTITULE_OBLIG, null);
        }
        System.out.println("22222222222222222222222222: " + dto.getEtat());
//        if (dto.getEtat() == null || ("").equalsIgnoreCase(dto.getEtat())) {
//            throw new ObjectValidationException(AccountConstant.MSG_ETAT_OBLIG, null);
//        }
    }

    @Override
    public void deleteHabilitation(String id) {
        if (this.habilitationRepository.existsById(id)) {
            this.habilitationRepository.deleteById(id);
        }
    }

    @Override
    public HabilitationDTO saveHabilitation(HabilitationDTO dto) {
        this.controleValidationObjetHabilitation(dto);
        Habilitation entity = new Habilitation();
        if (dto.getId() != null && this.habilitationRepository.existsById(dto.getId())) {
            entity = this.habilitationRepository.findById(dto.getId()).get();
        }
        entity.setProfil(this.profilRepository.findById(dto.getIdProfil()).get());
        entity.setFonction(this.socleGenericService.findFonctionCodeByCode(dto.getCodeFonction()).get());
        entity.setNiveauHabilitation(this.socleGenericService.findParamListById(dto.getIdNiveauHabilitation()).get());
        this.habilitationRepository.save(entity);
        System.out.println("Enregistrement: " + dto.toString());
        dto.setId(entity.getId());
        return dto;
    }

    public void controleValidationObjetHabilitation(HabilitationDTO dto) {
        if (dto.getIdProfil() == null || ("").equalsIgnoreCase(dto.getIdProfil()) || !this.existsById(dto.getIdProfil())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_HABILITATION_PROFIL_OBLIG, null);
        }
        System.out.println("dto.getCodeFonction():"+dto.toString());
        if (dto.getCodeFonction() == null || ("").equalsIgnoreCase(dto.getCodeFonction()) || !this.socleGenericService.existsFonctionByCode(dto.getCodeFonction())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_HABILITATION_FONCTION_OBLIG, null);
        }
        if (this.habilitationRepository.findByProfilAndFonction(dto.getIdProfil(), dto.getCodeFonction()).isPresent()
                && !this.habilitationRepository.findByProfilAndFonction(dto.getIdProfil(), dto.getCodeFonction()).get().getId().equalsIgnoreCase(dto.getId())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_HABILITATION_FONCTION_EXIST, null);
        }
        if (dto.getIdNiveauHabilitation() == null || ("").equalsIgnoreCase(dto.getIdNiveauHabilitation())
                || !this.socleGenericService.existsParamListById(dto.getIdNiveauHabilitation())) {
            throw new ObjectValidationException(AccountConstant.MSG_ACCOUNT_HABILITATION_NIVEAU_OBLIG, null);
        }
    }

    @Override
    public List<HabilitationDTO> finListHabilitationByProfil(String idProfil) {
        List<Habilitation> entities = this.habilitationRepository.findByProfil(idProfil);
        List<HabilitationDTO> result = new ArrayList<>();
        entities.stream().map(temp -> this.mapEntityHabilitationIntoDTO(temp)).forEachOrdered(obj -> {
            result.add(obj);
        });
        return result;
    }

    @Override
    public Optional<Habilitation> findHabilitationById(String id) {
        return this.habilitationRepository.findById(id);
    }

    @Override
    public boolean existsHabilitationById(String id) {
        return this.habilitationRepository.existsById(id);
    }

    @Override
    public HabilitationDTO getHabilitation(String id) {
        HabilitationDTO t = new HabilitationDTO();
        if (this.habilitationRepository.existsById(id)) {
            t = this.mapEntityHabilitationIntoDTO(this.habilitationRepository.findById(id).get());
        }
        return t;
    }

    @Override
    public Page<HabilitationDTO> findHabilitationPaginate(Pageable pageRequest, String idProfil) {
        Page<Habilitation> listResult = this.habilitationRepository.findByProfilPaginate(pageRequest, idProfil);
        return this.mapEntityhabilitationPageIntoDTOPage(pageRequest, listResult);
    }

    public Page<HabilitationDTO> mapEntityhabilitationPageIntoDTOPage(Pageable page, Page<Habilitation> source) {
        List<HabilitationDTO> list = this.mapEntitiesHabilitationIntoDTOs(source.getContent());
        return new PageImpl<>(list, page, source.getTotalElements());
    }

    public List<HabilitationDTO> mapEntitiesHabilitationIntoDTOs(List<Habilitation> entities) {
        List<HabilitationDTO> result = new ArrayList<>();
        entities.stream().map(temp -> this.mapEntityHabilitationIntoDTO(temp)).forEachOrdered(obj -> {
            result.add(obj);
        });
        return result;
    }

    @Override
    public HabilitationDTO mapEntityHabilitationIntoDTO(Habilitation entity) {
        HabilitationDTO t = new HabilitationDTO();
        t.setId(entity.getId());
        t.setIdProfil(entity.getProfil().getId());
        t.setLibelleProfil(entity.getProfil().getIntitule());
        t.setCodeFonction(entity.getFonction().getCode());
        t.setLibelleFonction(entity.getFonction().getLibelle());
        t.setIdNiveauHabilitation(entity.getNiveauHabilitation().getId());
        t.setLibelleNiveauHabilitation(entity.getNiveauHabilitation().getLibelle());
        t.setDisabled(true);
        return t;
    }

    public void forwardWorkflowProfil(String idActionPermise, String idObjet, String commentaire) {
        System.out.println("forwardWorkflowProfil");
        Optional<Profil> profil = profilRepository.findById(idObjet);

        if (profil.isPresent()) {
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
                    Profil prof = profil.get();
                    prof.setEtat(e);
                    
                    this.profilRepository.save(prof);

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
