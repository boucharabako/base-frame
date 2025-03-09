///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.TitreDTO;
//import com.ngs.core.codification.entities.EmbeddedIdTitre;
//import com.ngs.core.codification.entities.RefConfigFonction;
//import com.ngs.core.codification.entities.Titre;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author W.KOUWONOU
// * @Date 29/03/2018
// * @version 1.0.0
// */
//public interface TitreRepository extends JpaRepository<Titre, EmbeddedIdTitre> {
//
//    @Query("SELECT new com.ngs.core.codification.entities.RefConfigFonction"
//            + "(f.idTitre.fonction.id,f.titre,f.idTitre.fonction.codeModule)  "
//            + "FROM Titre f  WHERE f.idTitre.codeLangue.id=:id"
//            + " AND f.idTitre.fonction.activer.ideCode =:codeActiver"
//            + " ORDER BY f.titre")
//    public List<RefConfigFonction> findFonctionByCodeLangue(@Param("id") String code,
//            @Param("codeActiver") String codeActiver);
//    
//     @Query("SELECT new com.ngs.core.codification.entities.RefConfigFonction"
//            + "(f.idTitre.fonction.id,f.titre,f.idTitre.fonction.codeModule)  "
//            + "FROM Titre f  WHERE f.idTitre.codeLangue.id=:id"
//            + " AND f.idTitre.fonction.activer.ideCode =:codeActiver "
//             + "AND f.idTitre.fonction.type_fonction IS NOT NULL AND f.idTitre.fonction.type_fonction=:typeFonction"
//            + " ORDER BY f.titre")
//    public List<RefConfigFonction> findFonctionByCodeLangueAndTypeFonction(@Param("id") String code,
//            @Param("codeActiver") String codeActiver,
//            @Param("typeFonction") String typefonction);
//
//    @Query("SELECT new com.ngs.core.codification.entities.RefConfigFonction"
//            + "(f.idTitre.fonction.id,f.idTitre.fonction.actionMaximum,f.titre,f.idTitre.fonction.codeModule)  FROM Titre f  WHERE f.idTitre.codeLangue.id=:id "
//            + " AND f.idTitre.fonction.id=:f")
//    public RefConfigFonction findFonctionByFonctionAndyCodeLangue(@Param("f") String f, @Param("id") String code);
//
//    @Query("select new com.ngs.core.codification.dto.TitreDTO(t.idTitre, t.titre)"
//            + " from Titre t where t.idTitre=:idt")
//    public TitreDTO getTitreDTO(@Param("idt") EmbeddedIdTitre idt);
//
//    @Query("SELECT t.titre FROM Titre t WHERE t.idTitre.fonction.id=:idFonction")
//    public String getLibelle(@Param("idFonction") String idFonction);
//
//}
