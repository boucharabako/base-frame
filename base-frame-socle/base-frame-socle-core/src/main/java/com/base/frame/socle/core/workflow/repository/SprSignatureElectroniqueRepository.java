//package com.base.frame.socle.core.workflow.repository;
//
//
//import com.ngs.core.codification.entities.se.SprSignatureElectronique;
//import com.ngs.core.codification.services.se.SprSignatureDTO;
//import com.ngs.core.codification.utils.SprSignatureEtat;
//import com.ngs.core.codification.utils.SignatureVerifier;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface SprSignatureElectroniqueRepository extends JpaRepository<SprSignatureElectronique, String> {
//
//    public Optional<SprSignatureElectronique> findByUserIdAndDocumentId(String UserId, String documentId);
//    public List<SprSignatureElectronique> findByUserIdAndDocumentIdAndNature(String UserId, String documentId,SignatureVerifier n);
//
//    @Query("SELECT new com.ngs.core.codification.services.se.SprSignatureDTO(u.firstName,u.lastName,u.titre,c.dateSignature) " +
//            " FROM  SprSignatureElectronique s,Utilisateur u, SprCodeConfirmation c " +
//            "WHERE s.documentId=:d AND s.etat=:e AND s.userId=u.id AND c.signature.id=s.id AND c.etat=:e")
//    public List<SprSignatureDTO> findSignataire(@Param("d") String idDocument, @Param("e") SprSignatureEtat e);
//
////    @Query("SELECT s FROM  SignatureElectronique s WHERE s.etat=:e  AND s.documentId=:idDoc")
////    public Optional<SignatureElectronique> findByDocAndEtat(@Param("idDoc") String idDoc,@Param("e") SignatureEtat e);
////    
//    @Query("SELECT s FROM  SprSignatureElectronique s WHERE s.documentId=:idDoc")
//    public List<SprSignatureElectronique> findByDoc(@Param("idDoc") String idDoc);
//    @Query("SELECT s FROM  SprSignatureElectronique s WHERE s.documentId=:idDoc AND s.userId=:uid AND "
//            + "s.createdDate >= (SELECT max(t.createdDate) FROM  SprSignatureElectronique t WHERE t.documentId=:idDoc AND t.userId=:uid)")
//    public Optional<SprSignatureElectronique> findBLastSigneByDocIdAndUserId(@Param("idDoc") String idDoc,@Param("uid")String userId);
//    
//    
//}
