/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.iservice;

import com.base.frame.account.dto.ProfilDTO;
import com.base.frame.account.dto.ReinitPasswordConfirmationDTO;
import com.base.frame.account.dto.UserProfilDTO;
import com.base.frame.account.dto.UtilisateurDTO;
import com.base.frame.account.dto.UtilisateurPasswordDTO;
import com.base.frame.account.entity.Profil;
import com.base.frame.account.entity.Utilisateur;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Alassani
 */
public interface IUtilisateurService {

    /**
     * Enregistrement d'un utilisateur
     *
     * @param dto
     * @return
     */
    public UtilisateurDTO saveUtilisateur(UtilisateurDTO dto);

    /**
     * Suppression d'un utilisateur
     *
     * @param id
     */
    public void deleteUtilisateur(String id);

    /**
     * Recupère un utilisateur par son id
     *
     * @param id
     * @return
     */
    public Optional<Utilisateur> findUtilisateurById(String id);

    public Optional<Utilisateur> findUtilisateurUsername(String username);

    /**
     * Renvois true si le utilisateur existe
     *
     * @param id
     * @return
     */
    public boolean existsUtilisateurById(String id);

    /**
     * List des utilisateurs avec pagination
     *
     * @param mc
     * @param pageRequest
     * @return
     */
    public Page<UtilisateurDTO> findBySpecTerm(String mc,String username, String firstName, String lastName, String profil,String  groupe,String etat, Pageable pageRequest);

    /**
     * List de tous les utilisateur
     *
     * @return
     */
    public List<UtilisateurDTO> getAllUser();

    /**
     * Renvoie un utilisateur formaté sous son DTO
     *
     * @param entity
     * @return
     */
    public UtilisateurDTO mapEntityUtilisateurIntoDTO(Utilisateur entity);

    /**
     * Renvoie un utilisateur formaté sous son DTO
     *
     * @param id
     * @return
     */
    public UtilisateurDTO getUtilisateur(String id);

    /**
     * Enregistre ou Modifie un mot de passe utilisateur
     *
     * @param dto
     * @return
     */
    public UtilisateurPasswordDTO saveUtilisateurPassword(UtilisateurPasswordDTO dto);

    /**
     * Modifie un mot de passe utilisateur
     *
     * @param dto
     * @return
     */
    public UtilisateurPasswordDTO updateUtilisateurPassword(UtilisateurPasswordDTO dto);

    /**
     * Genere le code de réinitialisation de mot de passe
     *
     * @param idUtilisateur
     * @return
     */
    public String reinitUtilisateurPassword(String idUtilisateur);

    public void forgetUtilisateurPassword(String email);

    /**
     * Enregistre les profils d'un utilisateur
     *
     * @param dto
     * @return
     */
    public UserProfilDTO saveUserProfil(UserProfilDTO dto);

    public UserProfilDTO getUserProfil(String idUser);

    public ReinitPasswordConfirmationDTO reinitUtilisateurPasswordConfirmation(ReinitPasswordConfirmationDTO dto);

    public Page<UtilisateurDTO> findByProfilPaginate(Pageable pageRequest, String idProfil);
    
     public List<ProfilDTO> loadingProfil();
     
    public void forwardWorkflowUtilisateur(String idActionPermise, String idObjet, String commentaire);
}
