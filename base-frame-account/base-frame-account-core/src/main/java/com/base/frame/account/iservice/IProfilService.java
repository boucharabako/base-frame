/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.iservice;

import com.base.frame.account.dto.HabilitationDTO;
import com.base.frame.account.dto.ProfilDTO;
import com.base.frame.account.entity.Habilitation;
import com.base.frame.account.entity.Profil;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  
 * @author Alassani
 */
@Service
@Transactional
public interface IProfilService {

    /**
     * Enregistrement d'un profil
     *
     * @param dto
     * @return
     */
    public ProfilDTO save(ProfilDTO dto);

    /**
     * Suppression d'un profil
     *
     * @param id
     */
    public void delete(String id);

    /**
     * Recupère un profil par son id
     *
     * @param id
     * @return
     */
    public Optional<Profil> findById(String id);
    public Optional<Profil> findByCode(String code);

    /**
     * Renvois true si le profil existe
     *
     * @param id
     * @return
     */
    public boolean existsById(String id);
    public boolean existsByCode(String code);

    /**
     * List des profils avec pagination
     *
     * @param mc
     * @param pageRequest
     * @return
     */
    public Page<ProfilDTO> findBySpecTerm(Optional<String> mc, PageRequest pageRequest);

    /**
     * List des profils par statut
     *
     * @param codeStatut
     * @return
     */
    public List<ProfilDTO> findProfilByStatut(String codeStatut);
    public List<ProfilDTO> findProfilCanAddByStatut(String codeStatut, String idUser);

    /**
     * Renvoie un profil formaté sous son DTO
     *
     * @param entity
     * @return
     */
    public ProfilDTO mapEntityIntoDTO(Profil entity);

    /**
     * Renvoie une liste de profils formaté sous son DTO
     *
     * @param entities
     * @return
     */
    public List<ProfilDTO> mapEntitiesIntoDTOs(List<Profil> entities);

    /**
     * Renvoie un profil formaté sous son DTO
     *
     * @param entity
     * @return
     */
    public ProfilDTO getProfil(String id);

    public List<String> getListEtat();

    /**
     * Recupère une habilitation par son id
     *
     * @param id
     * @return
     */
    public Optional<Habilitation> findHabilitationById(String id);

    /**
     * Renvoie true si l'habilitation existe
     *
     * @param id
     * @return
     */
    public boolean existsHabilitationById(String id);

    /**
     * Renvoie une habilitation formaté sous son DTO
     *
     * @param id
     * @return
     */
    public HabilitationDTO getHabilitation(String id);

    /**
     * Enregistre une habilitation
     *
     * @param dto
     * @return
     */
    public HabilitationDTO saveHabilitation(HabilitationDTO dto);

    /**
     * Supprime une habilitation
     *
     * @param id
     */
    public void deleteHabilitation(String id);

    /**
     * Renvoie la liste des habilitations d'un profil
     *
     * @param idProfil
     * @return
     */
    public List<HabilitationDTO> finListHabilitationByProfil(String idProfil);

    /**
     * Renvoie une habilitation formaté sous son DTO
     *
     * @param entity
     * @return
     */
    public HabilitationDTO mapEntityHabilitationIntoDTO(Habilitation entity);

    /**
     * List des habilitation d'un profil avec pagination
     *
     * @param pageRequest
     * @param idProfil
     * @return
     */
    public Page<HabilitationDTO> findHabilitationPaginate(Pageable pageRequest, String idProfil);

    
    public void forwardWorkflowProfil(String idActionPermise, String idObjet, String commentaire);
}
