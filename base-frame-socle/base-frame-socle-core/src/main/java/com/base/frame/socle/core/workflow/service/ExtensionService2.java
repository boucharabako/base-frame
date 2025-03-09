///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.InternalServerException;
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.dto.ExtensionDTO2;
//import com.ngs.core.codification.dto.LibelleDTO;
//import com.ngs.core.codification.dto.StatutDto;
//import com.ngs.core.codification.entities.Extension;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.CodificationRepository;
//import com.ngs.core.codification.repositories.ExtensionRepository;
//import com.ngs.core.codification.repositories.LibelleRepository;
//import com.ngs.core.codification.repositories.ProprieteEtenduRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Bouchara
// */
//@Service
//@Transactional
//public class ExtensionService2 {
//
//    private static final String PROPRIETE_LISTE_CONTROLE = "PROPRIETE_EXTENSION_LISTEDECONTROLE";
//    private static final String PROPRIETE_CLASSIFICATION = "PROPRIETE_EXTENSION_CLASSIFICATION";
//    private static final String EXTENSION_ID_EXIST = "PROPRIETE_EXTENSION_ID_EXIST";
//
//    @Autowired
//    private NProperties properties;
//
//    @Autowired
//    private ExtensionRepository extensionRepository;
//    @Autowired
//    private CodifListRepository codifListRepository;
//    @Autowired
//    private CodificationRepository codificationRepository;
//    @Autowired
//    private ProprieteEtenduRepository proprieteEtenduRepository;
//    @Autowired
//    private TrackingParamObjetService trackingParamObjetService;
//    @Autowired
//    private NextframeModeleValidationService nextframeModeleValidationService;
//    @Autowired
//    private CodificationService codificationService;
//    @Autowired
//    private LibelleRepository libelleRepository;
//
//    public Extension saveExtension(ExtensionDTO2 extensionDTO2) {
//        Extension ex = new Extension();
//        Optional<Extension> eten = this.extensionRepository.getExtensionById(extensionDTO2.getId());
//        if (eten.isPresent()) {
//            throw new InternalServerException(codificationService.findMessage(EXTENSION_ID_EXIST), null);
//        }
//        ex.setId(extensionDTO2.getId());
//        ex.setCodeConcept(this.codifListRepository.findOne(extensionDTO2.getCodeConcept()));
//        ex.setTypeDonnee(this.codifListRepository.findOne(extensionDTO2.getTypeDonnee()));
//        if (ex.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_CODIF_CLASSEUR)) {
//            if (extensionDTO2.getClassification() == null) {
//                throw new InternalServerException(codificationService.findMessage(PROPRIETE_CLASSIFICATION), null);
//            }
//        }
//        if (ex.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_CONTROLE)
//                || ex.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_MULTIPLE)) {
//            if (extensionDTO2.getListeControle() == null) {
//                throw new InternalServerException(codificationService.findMessage(PROPRIETE_LISTE_CONTROLE), null);
//            }
//        }
//        if (ex.getTypeDonnee().getId() != null) {
//            ex.setTypeDonnee(this.codifListRepository.findOne(ex.getTypeDonnee().getId()));
//            if (ex.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_CONTROLE)
//                    || ex.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_MULTIPLE)) {
//                if (extensionDTO2.getListeControle() != null && this.codificationRepository.exists(extensionDTO2.getListeControle())) {
//                    ex.setListeControle(this.codificationRepository.findOne(extensionDTO2.getListeControle()));
//                    ex.setClassification(null);
//                } else {
//                    ex.setListeControle(null);
//                }
//            } else {
//                ex.setListeControle(null);
//            }
//            if (ex.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_CODIF_CLASSEUR)) {
//                if (extensionDTO2.getClassification() != null
//                        && this.codificationRepository.exists(extensionDTO2.getClassification())) {
//                    ex.setClassification(this.codificationRepository.findOne(extensionDTO2.getClassification()));
//                    ex.setListeControle(null);
//                } else {
//                    ex.setClassification(null);
//                }
//            } else {
//                ex.setClassification(null);
//            }
//        }
//        ex.setPropriete(this.proprieteEtenduRepository.findOne(extensionDTO2.getPropriete()));
//        if (extensionDTO2.getRequis()) {
//            ex.setRequis(this.codifListRepository.findByCodificationAndCode(CodificationConstant.OUI_NON, CodificationConstant.OUI_NON_OUI));
//        } else {
//            ex.setRequis(this.codifListRepository.findByCodificationAndCode(CodificationConstant.OUI_NON, CodificationConstant.OUI_NON_NON));
//        }
//        ex.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_EN_SAISIE));
//        this.nextframeModeleValidationService.validate(ex);
//        Extension ext = this.extensionRepository.save(ex);
//
//        this.trackingParamObjetService.saveStatusTracking(ext.getId(),
//                CodificationConstant.CONCEPT_METIER_EXTENSION,
//                this.properties.findStatutByCode(ext.getStatut().getIdeCode()));
//        return ex;
//    }
//
//    public void avancement(ExtensionDTO2 extensionDTO) {
//        Extension ext = this.extensionRepository.findOne(extensionDTO.getId());
//        for (LibelleDTO element : extensionDTO.getTracking()) {
//            this.trackingParamObjetService.saveStatusTracking(ext.getId(),
//                    CodificationConstant.CONCEPT_METIER_EXTENSION,
//                    this.properties.findStatutByCode(element.getId())
//            );
//        }
//        this.extensionRepository.save(ext);
//    }
//
//    public Extension updateExtension(ExtensionDTO2 extensionDTO2) {
//        Extension exUp  = this.extensionRepository.findOne(extensionDTO2.getId());
//        exUp.setTypeDonnee(this.codifListRepository.findOne(extensionDTO2.getTypeDonnee()));
//        if (exUp.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_CODIF_CLASSEUR)) {
//            if (extensionDTO2.getClassification() == null) {
//                throw new InternalServerException(codificationService.findMessage(PROPRIETE_CLASSIFICATION), null);
//            }
//        }
//        if (exUp.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_CONTROLE)
//                || exUp.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_MULTIPLE)) {
//            if (extensionDTO2.getListeControle() == null) {
//                throw new InternalServerException(codificationService.findMessage(PROPRIETE_LISTE_CONTROLE), null);
//            }
//        }
//        if (exUp.getTypeDonnee().getId() != null) {
//            exUp.setTypeDonnee(this.codifListRepository.findOne(exUp.getTypeDonnee().getId()));
//            if (exUp.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_CONTROLE)
//                    || exUp.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_MULTIPLE)) {
//                if (extensionDTO2.getListeControle() != null && this.codificationRepository.exists(extensionDTO2.getListeControle())) {
//                    exUp.setListeControle(this.codificationRepository.findOne(extensionDTO2.getListeControle()));
//                    exUp.setClassification(null);
//                } else {
//                    exUp.setListeControle(null);
//                }
//            } else {
//                exUp.setListeControle(null);
//            }
//            if (exUp.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_CODIF_CLASSEUR)) {
//                if (extensionDTO2.getClassification() != null
//                        && this.codificationRepository.exists(extensionDTO2.getClassification())) {
//                    exUp.setClassification(this.codificationRepository.findOne(extensionDTO2.getClassification()));
//                    exUp.setListeControle(null);
//                } else {
//                    exUp.setClassification(null);
//                }
//            } else {
//                exUp.setClassification(null);
//            }
//        }
//        exUp.setPropriete(this.proprieteEtenduRepository.findOne(extensionDTO2.getPropriete()));
//        exUp.setCodeConcept(this.codifListRepository.findOne(extensionDTO2.getCodeConcept()));
//        if (extensionDTO2.getRequis()) {
//            exUp.setRequis(this.codifListRepository.findByCodificationAndCode(CodificationConstant.OUI_NON, CodificationConstant.OUI_NON_OUI));
//        } else {
//            exUp.setRequis(this.codifListRepository.findByCodificationAndCode(CodificationConstant.OUI_NON, CodificationConstant.OUI_NON_NON));
//        }
//        this.nextframeModeleValidationService.validate(exUp);
//        this.extensionRepository.save(exUp);
//    
//        return exUp;
//
//    }
//
//    /**
//     *
//     * @param p
//     * @return
//     */
//
//    public void changeStatut(Extension ext) {
//        if (ext != null && ext.getId() != null) {
//            if (ext.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_OBSOLETE)) {
//            }
//            if (ext.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_ACTIF)) {
//                ext.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_OBSOLETE));
//            }
//            if (ext.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//                ext.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_ACTIF));
//            }
//            extensionRepository.saveAndFlush(ext);
//            trackingParamObjetService.saveStatusTracking(ext.getId(), CodificationConstant.CONCEPT_METIER_EXTENSION, ext.getStatut());
//        }
//    }
//
//    public ExtensionDTO2 mapEntityIntoDTO(Extension ex) {
//        ExtensionDTO2 exDTO = new ExtensionDTO2();
//        exDTO.setId(ex.getId());
//        if (ex.getPropriete() != null) {
//            exDTO.setPropriete(ex.getPropriete().getId());
//            exDTO.setProprieteLibelle(ex.getPropriete().getLibelle());
//        }
//        if (ex.getTypeDonnee() != null) {
//            exDTO.setTypeDonnee(ex.getTypeDonnee().getId());
//            exDTO.setTypeDonneeLibelle(libelleRepository.findByCodifListIdAndLangue(exDTO.getTypeDonnee(), properties.getCodeLangue().getIdeCode()).getLibelle());
//        }
//        exDTO.setCodeConcept(ex.getCodeConcept().getId());
//        exDTO.setTypeDonnee(ex.getTypeDonnee().getId());
//        if (ex.getClassification() != null) {
//            exDTO.setClassification(ex.getClassification().getCode());
//        }
//        if (ex.getListeControle() != null) {
//            exDTO.setListeControle(ex.getListeControle().getCode());
//        }
//        if (ex.getRequis().getIdeCode().equals(CodificationConstant.OUI_NON_OUI)) {
//            exDTO.setRequis(true);
//        } else if (ex.getRequis().getIdeCode().equals(CodificationConstant.OUI_NON_NON)) {
//            exDTO.setRequis(false);
//        } else {
//            exDTO.setRequis(false);
//        }
//
//        StatutDto st = new StatutDto();
//        if (ex.getStatut() != null) {
//            st.setIdeCode(ex.getStatut().getIdeCode());
//
//            st.setIdeCodeStatutSuivant(st.getNextStatutIdeCode());
//            st.setLibelle(properties.findStatutByCode(st.getIdeCode()) != null
//                    ? properties.findStatutByCode(st.getIdeCode()).getLibelle() : "");
//            st.setLibelleStatutSuivant(properties.findActionByCode(st.getIdeCode()) != null
//                    ? properties.findActionByCode(st.getIdeCode()).getLibelle() : "");
//            exDTO.setStatut(st);
//
//            exDTO.setIsEnSaisie(ex.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE));
//            exDTO.setIsActif(ex.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_ACTIF));
//            exDTO.setIsObsolete(ex.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_OBSOLETE));
//        }
//        exDTO.setDisable(true);
//        return exDTO;
//    }
//
//
//    @Transactional(readOnly = true)
//    public Page<ExtensionDTO2> findListSimple(Pageable pageRequest) {
//        Page<Extension> searchResultPage = extensionRepository.findListExtension(pageRequest);
//        return this.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<ExtensionDTO2> findByMotCle(Pageable pageRequest, String motCle) {
//        Page<Extension> searchResultPage = extensionRepository.findExtensionByMotCLE(pageRequest, motCle);
//        return this.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }
//
//    public Page<ExtensionDTO2> mapEntityPageIntoDTOPage(Pageable page, Page<Extension> source) {
//        List<ExtensionDTO2> listJM = mapEntitiesIntoDTOs(source.getContent());
//        return new PageImpl<>(listJM, page, source.getTotalElements());
//    }
//
//    public List<ExtensionDTO2> mapEntitiesIntoDTOs(List<Extension> entities) {
//        List<ExtensionDTO2> result = new ArrayList<>();
//        entities.stream().map(temp -> this.mapEntityIntoDTO(temp)).forEachOrdered(obj -> {
//            result.add(obj);
//        });
//        return result;
//    }
//}
