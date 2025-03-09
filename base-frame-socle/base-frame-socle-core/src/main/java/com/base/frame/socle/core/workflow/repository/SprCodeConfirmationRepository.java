//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.se.SprCodeConfirmation;
//import com.ngs.core.codification.utils.SprSignatureEtat;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface SprCodeConfirmationRepository extends JpaRepository<SprCodeConfirmation, String> {
//    @Query("SELECT c FROM SprCodeConfirmation c WHERE c.signature.userId=:ui AND c.signature.documentId=:di")
//    List<SprCodeConfirmation> findByUsreIdAndDocumentId(@Param("ui") String userId, @Param("di") String documentId);
//
//    @Query("SELECT c FROM  SprCodeConfirmation c WHERE c.signature.userId=:u " +
//            "AND c.code=:c AND c.signature.documentId=:d  AND c.etat=:e")
//    public Optional<SprCodeConfirmation> findByCode(@Param("d") String document, @Param("u") String user,
//                                                 @Param("c") String code, @Param("e") SprSignatureEtat e);
//
//
//}
