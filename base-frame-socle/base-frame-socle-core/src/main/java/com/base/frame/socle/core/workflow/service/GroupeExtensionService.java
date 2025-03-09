///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.InternalServerException;
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.dto.ExtensionSimpleObject;
//import com.ngs.core.codification.dto.IdLabelObject;
//import com.ngs.core.codification.entities.Classeur;
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Extension;
//import com.ngs.core.codification.entities.GroupeExtension;
//import com.ngs.core.codification.entities.ValeurExtension;
//import com.ngs.core.codification.repositories.ClasseurRepository;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.ExtensionRepository;
//import com.ngs.core.codification.repositories.GroupeExtensionRepository;
//import com.ngs.core.codification.repositories.LibelleRepository;
//import com.ngs.core.codification.repositories.ValeurExtensionRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Herve
// */
//@Service
//public class GroupeExtensionService {
//
//    @Autowired
//    private TrackingParamObjetService trackingParamObjetService;
//    @Autowired
//    private CodifListRepository codifListRepository;
//    @Autowired
//    private ExtensionRepository extensionRepository;
//    @Autowired
//    private GroupeExtensionRepository groupeExtensionRepository;
//    @Autowired
//    private LibelleRepository libelleRepository;
//    @Autowired
//    private ValeurExtensionRepository valeurExtensionRepository;
//    @Autowired
//    private NProperties properties;
//    @Autowired
//    private ClasseurRepository classeurRepository;
//
//    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GroupeExtensionService.class);
//
//    @Transactional
//    public boolean saveWithList(GroupeExtension groupeExtention, String[] ids) {
//        //On fait un ajout
//        if (groupeExtention.getId() == null || groupeExtention.getId().isEmpty()) {
//            for (String id : ids) {
//                if (!id.isEmpty()) {
//                    groupeExtention.getExtensions().add(this.extensionRepository.findOne(id));
//                }
//            }
//            if (this.groupeExtensionRepository.sizeByTitre(groupeExtention.getTitre()) == 0) {
//                groupeExtention.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_EN_SAISIE));
//                groupeExtention = this.groupeExtensionRepository.save(groupeExtention);
//                trackingParamObjetService.saveStatusTracking(groupeExtention.getId(), CodificationConstant.CONCEPT_METIER_GROUPE_EXTENSION, CodificationConstant.CREATION_OBJET, groupeExtention.getStatut());
//            } else {
//                return false;
//            }
//        } else {
//            //On fait une modification
//            GroupeExtension ge = this.groupeExtensionRepository.findGroupFetchExtension(groupeExtention.getId());
//            ge.setTitre(groupeExtention.getTitre());
//            ge.setDescription(groupeExtention.getDescription());
//            ge.setCodeConcept(groupeExtention.getCodeConcept());
//            ge.setExtensions(new ArrayList<>());
//
//            for (int i = 0; i < ids.length; i++) {
//                if (!ids[i].isEmpty()) {
//                    ge.getExtensions().add(this.extensionRepository.findOne(ids[i]));
//                }
//            }
//            this.groupeExtensionRepository.save(ge);
//        }
//        return true;
//    }
//
//    public GroupeExtension goToNextStep(String idGroupExtension) {
//        GroupeExtension ge = groupeExtensionRepository.findOne(idGroupExtension);
//        if (ge.getStatut() != null) {
//            List<CodifList> list = codifListRepository.findNextStep(CodificationConstant.STATUT_PARAM, ge.getStatut().getIdeCode(), new PageRequest(0, 1));
//            if (list != null && !list.isEmpty()) {
//                CodifList c = list.get(0);
//                ge.setStatut(c);
//                ge = groupeExtensionRepository.save(ge);
//                trackingParamObjetService.saveStatusTracking(idGroupExtension, CodificationConstant.CONCEPT_METIER_GROUPE_EXTENSION, ge.getStatut());
//            }
//        }
//        return ge;
//    }
//
//    public boolean saveWithList(GroupeExtension groupeExtention) {
//        //On fait un ajout
//        String[] ids = groupeExtention.getListeExtensions().split(",");
//        if (groupeExtention.getId() == null || groupeExtention.getId().isEmpty()) {
//            for (String id : ids) {
//                if (!id.isEmpty()) {
//                    groupeExtention.getExtensions().add(this.extensionRepository.findOne(id));
//                }
//            }
//            if (this.groupeExtensionRepository.sizeByTitre(groupeExtention.getTitre()) == 0) {
//                this.groupeExtensionRepository.save(groupeExtention);
//            } else {
//                return false;
//            }
//        } else {
//            //On fait une modification
//            GroupeExtension ge = this.groupeExtensionRepository.findOne(groupeExtention.getId());
//            ge.setTitre(groupeExtention.getTitre());
//            ge.setDescription(groupeExtention.getDescription());
//            for (int i = 0; i < ids.length; i++) {
//                if (!ids[i].isEmpty()) {
//                    ge.getExtensions().add(this.extensionRepository.findOne(ids[i]));
//                }
//            }
//            this.groupeExtensionRepository.save(ge);
//        }
//        return true;
//    }
//
//    public List<ExtensionSimpleObject> getExtensions(GroupeExtension gpExt, String idObject) {
//        List<ExtensionSimpleObject> extensionObjects = new ArrayList<>();
//        if (gpExt != null && gpExt.getId() != null
//                && this.groupeExtensionRepository.exists(gpExt.getId())) {
//            List<Extension> extensions = this.extensionRepository.findByGroupeId(
//                    gpExt.getId());
//
//            if (extensions != null && !extensions.isEmpty()) {
//                for (Extension extension : extensions) {
//                    Extension e = this.extensionRepository.findOne(extension.getId());
//                    ExtensionSimpleObject extensionDTO = new ExtensionSimpleObject();
//
//                    extensionDTO.setId(e.getId());
//                    extensionDTO.setLibelle(e.getPropriete().getCaption());
//                    extensionDTO.setTypeDonnee(e.getTypeDonnee().getIdeCode());
//                    extensionDTO.setRequired(e.getRequis().getIdeCode().equals(
//                            CodificationConstant.OUI_NON_OUI));
//
//                    if (e.getListeControle() != null) {
//                        extensionDTO.setTypeDonneListDeControle(true);
//                        if (e.getListeCodifListControle() != null && e.getListeControle().getCode().equalsIgnoreCase(CodificationConstant.OUI_NON)) {
//                            extensionDTO.setCodeListDeControleIsOUI_NON(true);
//                            extensionDTO.setValeur("false");
//                        }
//                        extensionDTO.setListDeControle(
//                                this.libelleRepository.findByCodification(
//                                        e.getListeControle().getCode(), this.properties.getLangue().getId()));
//
//                        if (extension.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_LISTE_MULTIPLE)) {
//                            extensionDTO.setTypeDonneListDeControleMultiple(true);
//                            extensionDTO.setHauteur(extensionDTO.getListDeControle().size());
//                        }
//                    }
//
//                    if (e.getClassification() != null && e.getClassification().getCode() != null) {
//                        extensionDTO.setClassification(this.libelleRepository.findByCodificationCodeAndCodeLangueUsingIdLabelObject(e.getClassification().getCode(), this.properties.getLangue().getId()));
//                        extensionDTO.setTypeDonneClassification(true);
//                        extensionDTO.setTypeDonneListDeControle(false);
//                    }
//
//                    if (idObject != null) {
//                        ValeurExtension valeurExtension = this.valeurExtensionRepository
//                                .findOneByIdExtensionIdObject(e.getId(), idObject);
//                        if (valeurExtension != null) {
//                            if (extensionDTO.getCodeListDeControleIsOUI_NON()) {
//                                if (valeurExtension.getValeur().equalsIgnoreCase(CodificationConstant.OUI_NON_OUI_IDE_CODE)) {
//                                    extensionDTO.setValeur("true");
//                                }
//                            } else if (extensionDTO.getTypeDonneListDeControle() && !extensionDTO.getCodeListDeControleIsOUI_NON()) {
//                                extensionDTO.setValeurLDC(new IdLabelObject(valeurExtension.getValeur(), ""));
//                            }
//                            {
//                                extensionDTO.setValeur(valeurExtension.getValeur());
//                                extensionDTO.setCochet(valeurExtension.getValeur() != null && valeurExtension.getValeur().trim() != null);
//                            }
//                        }
//                        if (valeurExtension != null && valeurExtension.getValeur() == null && valeurExtension.getValeurMultiple() != null
//                                && !valeurExtension.getValeurMultiple().isEmpty() && extensionDTO.getListDeControle() != null) {
//
//                            extensionDTO.getListDeControle().forEach((label) -> {
//                                valeurExtension.getValeurMultiple().stream().filter((str) -> (label.getId().equalsIgnoreCase(str))).forEachOrdered((_item) -> {
//                                    label.setCheckValue(true);
//                                });
//                            });
//                        }
//                        if (valeurExtension != null && valeurExtension.getValeur() != null && e.getClassification() != null && e.getClassification().getCode() != null) {
//                            extensionDTO.setValeur(valeurExtension.getValeur());
//                            Classeur c = this.classeurRepository.findOne(valeurExtension.getValeur());
//                            extensionDTO.setLibelleClassification(c.getLibelle());
//                        }
//                    }
//                    extensionObjects.add(extensionDTO);
//                }
//                extensionObjects.stream().sorted(
//                        Comparator.comparing(ExtensionSimpleObject::getRequired, Comparator.reverseOrder()))
//                        .collect(Collectors.toList());
//            }
//        }
//        return extensionObjects;
//    }
//
//    private Set<String> getSelectedValue(List<IdLabelObject> listMapped) {
//        HashSet<String> model = new HashSet<>();
//        listMapped.stream().forEach(element -> {
//            if (element.isCheckValue()) {
//                model.add(element.getId());
//            }
//        });
//        return model;
//    }
//
//    public List<ExtensionSimpleObject> saveExtensions(String idObjet, List<ExtensionSimpleObject> extensionList) {
//        //Enregistrement des extensions
//        if (extensionList != null && !extensionList.isEmpty()) {
//            extensionList.stream().forEach(t -> {
//                if (t.getRequired() && ((t.getValeur() == null || t.getValeur() == "")
//                        && (t.getListDeControle() == null
//                        || this.getSelectedValue(t.getListDeControle()).isEmpty())
//                        && (t.getValeurLDC() == null || t.getValeurLDC().equals(new IdLabelObject())))) {
//                    throw new InternalServerException(CodificationConstant.F_REQUIERD + "#" + t.getLibelle(), null);
//                }
//
//                List<ValeurExtension> listValeurExtensions = valeurExtensionRepository.findByIdExtensionIdObject(t.getId(), idObjet);
//                if (listValeurExtensions.isEmpty()) {
//
//                    if (t.getTypeDonneListDeControle() && t.getValeurLDC() != null && t.getValeurLDC().getId() != null) {
//
//                        t.setValeur(t.getValeurLDC().getId());
//                    }
//                    if (t.getTypeDonneListDeControle() && !t.getTypeDonneListDeControleMultiple() && t.getValeurLDC() == null && t.getValeur() != null) {
//                        if (t.getCodeListDeControleIsOUI_NON()) {
//
//                            if (t.getValeur().equalsIgnoreCase("true")) {
//                                t.setValeur(CodificationConstant.OUI_NON_OUI_IDE_CODE);
//                                valeurExtensionRepository.save(new ValeurExtension(extensionRepository.findOne(t.getId()), idObjet, CodificationConstant.OUI_NON_OUI_IDE_CODE));
//                            } else {
//                                t.setValeur(CodificationConstant.OUI_NON_NON_IDE_CODE);
//                                valeurExtensionRepository.save(new ValeurExtension(extensionRepository.findOne(t.getId()), idObjet, CodificationConstant.OUI_NON_NON_IDE_CODE));
//                            }
//                        }
//                    }
//                    if (!t.getTypeDonneListDeControleMultiple() && t.getValeur() != null && !t.getValeur().isEmpty() && !t.getCodeListDeControleIsOUI_NON()) {
//                        valeurExtensionRepository.save(new ValeurExtension(extensionRepository.findOne(t.getId()), idObjet, t.getValeur()));
//                    }
//                    //Gestion des listes de controle multiples
//                    if (t.getTypeDonneListDeControle() && t.getTypeDonneListDeControleMultiple()) {
//                        Set<String> valeurSave = this.getSelectedValue(t.getListDeControle());
//                        if (!valeurSave.isEmpty()) {
//                            ValeurExtension valeurExtension = new ValeurExtension();
//                            valeurExtension.setExtension(extensionRepository.findOne(t.getId()));
//                            valeurExtension.setIdObjet(idObjet);
//                            valeurExtension.setValeurMultiple(valeurSave);
//                            valeurExtensionRepository.save(valeurExtension);
//                        }
//
//                    }
//                } else {
//                    listValeurExtensions.forEach(lv -> {
//                        if ((t.getValeur() != null && !t.getValeur().isEmpty()) || t.getValeurLDC() != null || t.getValeurCLA() != null || (t.getTypeDonneListDeControleMultiple() && !this.getSelectedValue(t.getListDeControle()).isEmpty())) {
//                            if (t.getTypeDonneListDeControle() && t.getValeurLDC() != null && t.getValeurLDC().getId() != null) {
//                                t.setValeur(t.getValeurLDC().getId());
//                            }
//                            if (t.getTypeDonneListDeControle() && !t.getTypeDonneListDeControleMultiple() && t.getValeurLDC() == null && t.getValeur() != null) {
//                                if (t.getCodeListDeControleIsOUI_NON()) {
//                                    if (t.getValeur().equalsIgnoreCase("true")) {
//                                        t.setValeur(CodificationConstant.OUI_NON_OUI_IDE_CODE);
//                                        lv.setValeur(CodificationConstant.OUI_NON_OUI_IDE_CODE);
//                                    } else {
//                                        t.setValeur(CodificationConstant.OUI_NON_NON_IDE_CODE);
//                                        lv.setValeur(CodificationConstant.OUI_NON_NON_IDE_CODE);
//                                    }
//                                }
//                            }
//                            if (!t.getTypeDonneListDeControleMultiple() && t.getValeur() != null && !t.getValeur().isEmpty() && !t.getCodeListDeControleIsOUI_NON()) {
//                                lv.setValeur(t.getValeur());
//                            }
//                            if (t.getTypeDonneListDeControle() && t.getTypeDonneListDeControleMultiple()) {// Modification Gestion des liste de controles multiples
//                                Set<String> valeurSave = this.getSelectedValue(t.getListDeControle());
//                                if (!valeurSave.isEmpty()) {
//                                    lv.setExtension(extensionRepository.findOne(t.getId()));
//                                    lv.setIdObjet(idObjet);
//                                    lv.setValeurMultiple(valeurSave);
//                                }
//                            }
//
//                        } else { // Suppression des valeurs extensions non renseignée mais déjà existente en base de données
//                            if (!t.getTypeDonneListDeControle()) {
//                                if (valeurExtensionRepository.exists(lv.getId())) {
//                                    valeurExtensionRepository.delete(lv);
//                                }
//                            }
//                            if (!t.getTypeDonneListDeControleMultiple()) {
//                                if (valeurExtensionRepository.exists(lv.getId())) {
//                                    valeurExtensionRepository.delete(lv);
//                                }
//
//                            }
//                            if (t.getTypeDonneListDeControle() && t.getTypeDonneListDeControleMultiple()) {
//                                lv.getValeurMultiple().clear();
//                                valeurExtensionRepository.delete(lv);
//
//                            }
////                            this.removeExtension
//                        }
//                    });
//                }
//
//            });
//        }
//        return extensionList;
//    }
//
//}
