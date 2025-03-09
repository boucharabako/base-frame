/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;

import com.base.frame.socle.core.workflow.entity.Codification;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Adnaane
 * @version 1.0.0
 * @since 27/02/2018
 */
public interface CodificationRepository extends JpaRepository<Codification, String>,JpaSpecificationExecutor<Codification>  {

    /**
     * Cette méthode retourne la liste des codifications à travers un type donné
     *
     * @param typeCode le type de la codification (paramètres, étiquettes,
     * liste)
     * @return
     */
    @Query("SELECT c FROM Codification c WHERE c.typeCodif.code = :type")
    List<Codification> getAllByCodificationTypeByCode(@Param("type") String typeCode);

    /**
     * Cette méthode retourne la liste des codifications à travers un type donné
     *
     * @param type le type de la codification (paramètres, étiquettes, liste)
     * @return
     */
    @Query("SELECT c FROM Codification c WHERE c.typeCodif.id = :type")
    List<Codification> getAllByCodificationType(@Param("type") String type);

    /**
     * Retroune une liste de codification en fonction du code
     *
     * @param code le code de codification
     * @return
     */
    @Query("SELECT c FROM Codification c WHERE c.code = :code")
    List<Codification> getAllByCode(@Param("code") String code);

    /**
     * Cette méthode permet de retrouver une codification connaissant son code
     *
     * @param code le code de codification
     * @return une codification
     */
    @Query("SELECT c FROM Codification c WHERE c.code = :code")
    Codification getByCode(@Param("code") String code);

    /**
     * Cette méthode permet de retrouver des codification en fonction du domaine
     *
     * @param domaine le domaine concerné
     * @param typeCodification
     * @return liste de codification
     */
    @Query("SELECT c FROM Codification c WHERE c.domaine.code = :domain AND c.typeCodif.code <>:codifType")
    List<Codification> getAllByDomaine(@Param("domain") String domaine,
            @Param("codifType") String typeCodification);

    /**
     * Cette méthode permet d'avoir une liste de codification via le type de
     * codification et le domaine concerné
     *
     * @param domainId le code du domaine
     * @param typeCodification le type de codification (paremètre , étiquette,
     * liste)
     * @return liste de codification
     */
    @Query("SELECT c FROM Codification c WHERE c.domaine.id = :id AND c.typeCodif.code = :codifType")
    List<Codification> getAllByDomaineId(
            @Param("id") String domainId,
            @Param("codifType") String typeCodification
    );

    /**
     * Cette méthode permet d'avoir une liste de codification via le type de
     * codification et le domaine concerné
     *
     * @param domainId le code du domaine
     * @param typeCodification le type de codification (paremètre , étiquette,
     * liste)
     * @return liste de codification
     */
    @Query("SELECT c FROM Codification c WHERE c.domaine.code = :code AND c.typeCodif.code = :codifType")
    List<Codification> getAllByDomaineIdCode(
            @Param("code") String domainId,
            @Param("codifType") String typeCodification
    );

    /**
     * Cette méthode permet d'avoir la liste des codifications via la porté et
     * le type de codification
     *
     * @param porteId l'id de la portée
     * @param typeCodification le type de codification
     * @return liste de codification
     */
    @Query("SELECT c FROM Codification c WHERE c.porte.id = :id AND c.typeCodif.code = :codifType")
    List<Codification> getAllByPorteId(
            @Param("id") String porteId,
            @Param("codifType") String typeCodification
    );

    /**
     * Cette méthode permet d'avoir la liste des codifications via la porté
     *
     * @param porte la portée
     * @return une liste de codification
     */
    @Query("SELECT c FROM Codification c WHERE c.porte.code = :porte")
    List<Codification> getAllByPorte(@Param("porte") String porte);

    /**
     * Cette méthode permet d'avoir une liste de codification via le type avec
     * des paginations
     *
     * @param type le type de codification
     * @param p le paremètre
     * @return
     */
    @Query("SELECT c FROM Codification c WHERE c.typeCodif.id = :type")
    Page<Codification> getAllByCodificationType(@Param("type") String type, Pageable p);

    /**
     * Cette méthode permet de retrouver une codifciation via le code et le type
     * de la codification
     *
     * @param code le code de codification
     * @param type le type de la codification
     * @return
     */
    @Query("SELECT c FROM Codification c WHERE c.code = :code AND  c.typeCodif.code = :type")
    Codification findOneByCodificationType(@Param("code") String code, @Param("type") String type);

    /**
     * Cette fonction permet de retrouver des codification via leur type
     *
     * @param type le type de la codification
     * @return
     */
    @Query("SELECT c FROM Codification c WHERE   c.typeCodif.code = :type")
    List<Codification> findOneByCodificationType(@Param("type") String type);

    /**
     * Cette fonction permet de retrouver une codifiction via son code
     *
     * @param code
     * @return
     */
    @Query("SELECT c FROM Codification c WHERE  c.code = :code")
    Codification findOneByCodificationCode(@Param("code") String code);

    /**
     * Cette cette fonction permet de retrouver une liste de codification via le
     * type de codificatio et le domaine et aussi le statut
     *
     * @param type le type de codification
     * @param idDomaine l'id du domaine
     * @param statut le statut
     * @return une liste de codification
     */
    @Query("SELECT c FROM Codification c WHERE   c.typeCodif.code = :type "
            + "AND c.domaine.code = :d AND c.statut.code = :s")
    List<Codification> findOneByCodificationTypeAndDomaine(@Param("type") String type,
            @Param("d") String idDomaine, @Param("s") String statut);

    /**
     * Cette méthode permet d'avoir la liste des codification via le type , le
     * statut et aussi le domaine
     *
     * @param type le type
     * @param statut le statut
     * @param domain le domaine
     * @return la liste de codification
     */
    @Query("SELECT c FROM Codification c WHERE c.typeCodif.code =:type AND c.statut.code=:statut AND c.domaine.code=:domain")
    List<Codification> findOneByCodificationTypeAndStatutAndDomaine(@Param("type") String type, @Param("statut") String statut, @Param("domain") String domain);

    /**
     * Cette méthode permet d'avoir la liste des codification via le type , le
     * statut et aussi le domaine
     *
     * @param type le type
     * @param statut le statut
     * @param domain le domaine
     * @return la liste de codification
     * @Modified by hakakpo 20-05-2019
     */
    @Query("SELECT new com.base.frame.socle.core.workflow.entity.Codification(c.code,c.libelle) FROM Codification c WHERE c.typeCodif.code =:type AND c.statut.code=:statut AND c.domaine.code=:domain "
            + "ORDER BY c.libelle ASC")
    List<Codification> findOneByCodificationTypeAndStatutAndDomaineSimple(@Param("type") String type, @Param("statut") String statut, @Param("domain") String domain);
    
    /**
     * Cette méthode permet d'avoir la liste des codification via la portée , le
     * statut et aussi le domaine
     *
     *
     * @param porte la portée
     * @param statut le statut
     * @param domain le domaine
     * @return la liste de codification
     */
    @Query("SELECT c FROM Codification c WHERE c.porte.code =:porte AND c.statut.code=:statut AND c.domaine.code=:domain")
    List<Codification> findOneByCodificationPorteAndStatutAndDomaine(@Param("porte") String porte, @Param("statut") String statut, @Param("domain") String domain);

  
    /**
     * Cette méthode permet d'avoir la liste des codifications
     *
     * @param type le type de codification
     * @param statut le statut
     * @param domain le domaine
     * @return
     */
    @Query("SELECT c FROM Codification c WHERE c.typeCodif.code =:type AND c.statut.code=:statut AND c.porte.code=:domain")
    List<Codification> findOneByCodificationTypeAndStatutAndPorte(@Param("type") String type, @Param("statut") String statut, @Param("domain") String domain);

    
        /**
     * Cette méthode permet d'avoir la liste des codifications via le type et le
     * statut 
     *
     * @param type le type
     * @param statut le statut
     
     * @return la liste de codification
     
     */
    @Query("SELECT c FROM Codification c WHERE c.typeCodif.code =:type AND c.statut.code=:statut "
            + " ORDER BY c.libelle ASC")
    List<Codification> findOneByCodificationTypeAndStatut(@Param("type") String type, @Param("statut") String statut);
  
 
}
