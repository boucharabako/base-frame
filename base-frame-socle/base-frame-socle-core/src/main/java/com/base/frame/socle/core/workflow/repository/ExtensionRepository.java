///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.MapSizeDTO;
//import com.ngs.core.codification.entities.Extension;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author NGS_004
// */
//public interface ExtensionRepository extends JpaRepository<Extension, String> {
//
//    /**
//     * Page des Extensions
//     *
//     * @param code code du concept metier
//     * @param p pagination
//     * @return
//     */
//    @Query("SELECT e FROM Extension e WHERE e.codeConcept.id = :codeConcept ORDER BY e.createdDate DESC")
//    public Page<Extension> findPageByConceptMetierId(@Param("codeConcept") String code, Pageable p);
//
//    /**
//     * Recherche des Extensions
//     *
//     * @param code code du concept metier
//     * @param moc mot de recherche
//     * @param p pagination
//     * @return
//     */
//    @Query("SELECT e FROM Extension e WHERE e.codeConcept.id = :codeConcept "
//            + " AND (e.id LIKE :moc "
//            + " OR e.propriete.libelle LIKE :moc ) ORDER BY e.createdDate DESC")
//    public Page<Extension> findPageByConceptMetierIdMotCle(@Param("codeConcept") String code,
//            @Param("moc") String moc,
//            Pageable p);
//
//    /**
//     * Liste des Extensions par concept metier
//     *
//     * @param code code du concept metier
//     * @param ideCode
//     * @return
//     */
//    @Query("SELECT e FROM Extension e WHERE e.codeConcept.id = :codeConcept AND e.statut.ideCode=:ideCode ORDER BY e.createdDate DESC")
//    public List<Extension> findByConceptMetierIdAndStatut(@Param("codeConcept") String code, @Param("ideCode") String ideCode);
//
//    /**
//     * Liste des Extensions par propriété
//     *
//     * @param code code de la propriété
//     * @return
//     */
//    @Query("SELECT e FROM Extension e WHERE e.propriete.ide = :idPropriete")
//    public List<Extension> findByProprieteId(@Param("idPropriete") String code);
//
//    /**
//     * Liste des Extensions d'un groupe
//     *
//     * @param idGrp identifiant d'un groupe d'extensions
//     * @return
//     */
//    @Query("SELECT e FROM GroupeExtension g JOIN g.extensions e WHERE g.id = :id"
//            + " ORDER BY e.createdDate DESC ")
//    public List<Extension> findByGroupeId(@Param("id") String idGrp);
//
//
//    /**
//     * Page des Extensions
//     *
//     * @param p pagination
//     * @return
//     */
//    @Query("SELECT e  FROM Extension e  ORDER BY e.createdDate DESC")
//    public Page<Extension> findByParam(Pageable p);
//
//    /**
//     * Recherche des Extensions
//     *
//     * @param moc mot de recherche
//     * @param p pagination
//     * @return
//     */
//    @Query("SELECT e  FROM Extension e WHERE e.id LIKE :moc"
//            + " OR e.propriete.libelle LIKE :moc ORDER BY e.createdDate DESC")
//    public Page<Extension> findByParam(@Param("moc") String moc, Pageable p);
//
//    /**
//     * Liste des Extensions
//     *
//     * @param ideCode code du statut
//     * @return
//     */
//    @Query("SELECT e FROM Extension e WHERE e.statut.ideCode=:ideCode")
//    public List<Extension> findByStatut(@Param("ideCode") String ideCode);
//
//    /**
//     * Recherche des Extensions
//     *
//     * @param id identifiant de l'extension
//     * @param ideCode code du statut
//     * @return
//     */
//    @Query("SELECT e FROM Extension e WHERE e.statut.ideCode=:ideCode")
//    public List<Extension> findByStatutAndGroupe(@Param("id") String id, @Param("ideCode") String ideCode);
//
//
//
//    @Query("Select ex FROM Extension ex WHERE ex.id=:id ORDER BY ex.createdDate DESC")
//    public Optional<Extension> getExtensionById(@Param("id") String id);
//
//    @Query("Select ex FROM Extension ex WHERE ex.id=:id "
//            + " and ex.typeDonnee.id=:typeDonnee "
//            + " and ex.propriete.id=:propriete ORDER BY ex.createdDate DESC")
//    public Optional<Extension> getExtensionByIdTypeDonneAndPropriete(@Param("id") String id,
//            @Param("typeDonnee") String typeDonnee,
//            @Param("propriete") String propriete);
//
//
//   
//    @Query("SELECT ex FROM Extension ex WHERE (upper(ex.id) LIKE CONCAT('%',:motCle,'%')"
//            + " OR upper((select l.libelle from Libelle l where l.codifList.id = ex.typeDonnee.id)) LIKE CONCAT('%',:motCle,'%') "
//            + " OR upper(ex.propriete.libelle) LIKE CONCAT('%',:motCle,'%')) "
//            + " ORDER BY ex.createdDate DESC")
//    public Page<Extension> findExtensionByMotCLE(Pageable p, @Param("motCle") String motCle);
//
//    @Query("SELECT ex FROM Extension ex  ORDER BY ex.createdDate DESC")
//    public Page<Extension> findListExtension(Pageable p);
//
//    @Query("SELECT new com.ngs.core.codification.dto.MapSizeDTO(e.id, e.propriete.id,e.typeDonnee.id,  e.id, e.propriete.caption, e.codeConcept.id) FROM Extension e WHERE   e.codeConcept.id=:conceptMetier")
//    List<MapSizeDTO> findExtensionBySize(@Param("conceptMetier") String conceptMetier);
//    
//    @Query("SELECT e FROM Extension e WHERE   e.codeConcept.id=:conceptMetier")
//    public List<Extension> findTest(@Param("conceptMetier") String conceptMetier);
//    
//    
//}
