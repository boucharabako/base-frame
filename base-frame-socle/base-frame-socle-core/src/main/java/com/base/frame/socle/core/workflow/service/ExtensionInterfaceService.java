///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.InternalServerException;
//import com.ngs.core.codification.dto.ValeurExtensionInterfaceDTO;
//import com.ngs.core.codification.entities.ValeurExtensionInterface;
//import com.ngs.core.codification.entities.ExtensionInterface;
//import com.ngs.core.codification.repositories.ExtensionInterfaceRepository;
//import com.ngs.core.codification.repositories.ValeurExtensionInterfaceRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.ComposantsHtml;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author kouwonou
// */
//@Service
//public class ExtensionInterfaceService {
//
//    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ExtensionInterfaceService.class);
//
//    @Autowired
//    private ValeurExtensionInterfaceRepository valeurExtensionInterfaceRepository;
//
//    @Autowired
//    private ExtensionInterfaceRepository extensionInterfaceRepository;
//    @Autowired(required = false)
//    private Map<String, IExtensionInterface> extensions;
//
//    public List<ValeurExtensionInterfaceDTO> save(List<ValeurExtensionInterfaceDTO> l, String idObjet, String conceptMetier) {
//
//        l.forEach(a -> {
//            Optional<ValeurExtensionInterface> v = valeurExtensionInterfaceRepository.
//                    findAllByIdObjetAndExtension(idObjet, a.getExtensionId());
//            a.setIdObjet(idObjet);
//
//            LOG.info("Valeure apres " + a.getValeur());
//            ValeurExtensionInterface val = validate(a);
//
//            LOG.info("Valeur apres " + a.getValeur());
//
//            if (v.isPresent()) {
//                val = v.get();
//                val.setValeur(a.getValeur());
//
//            }
//
//            val = valeurExtensionInterfaceRepository.save(val);
//            a.setIdObjet(idObjet);
//            a.setId(val.getId());
//
//        });
//        return l;
//    }
//
//    public List<ValeurExtensionInterfaceDTO> setExtension(List<ValeurExtensionInterfaceDTO> l) {
//        i = 0;
//        if (l == null) {
//            return new ArrayList<>();
//        }
//        l.forEach(a -> {
//            System.out.println("hhhhhhhhhhhhhhhhhhhh " + a.getValeur());
//            a.setExtension(extensionInterfaceRepository.findOne(a.getExtensionId()));
//
//            if (a.getExtension().getImplentation() != null && !a.getExtension().getImplentation().isEmpty()
//                    && extensions.get(a.getExtension().getImplentation()) != null) {
//
//                a.setList(
//                        extensions
//                                .get(a.getExtension()
//                                        .getImplentation())
//                                .findAll());
//
//                if (a.getExtension().getComposant() == ComposantsHtml.ARBRE) {
//                    a.setId("" + i);
//                    i++;
//
//                    if (a.getValeur() != null && !a.getValeur().isEmpty()) {
//                        a.setLibelle(extensions.get(a.getExtension().getImplentation()).findOne(a.getValeur()).getLibelle());
//                    }
//                }
//            }
//        });
//        return l;
//    }
//
//    /**
//     * Liste des extensions lié à un utilisateur
//     *
//     * @param username
//     * @return
//     */
//    int i = 0;
//
//    public List<ValeurExtensionInterfaceDTO> findByObject(String idObjet, String conceptMetier) {
//        List<ValeurExtensionInterfaceDTO> l = new ArrayList<>();
//        List<ExtensionInterface> extensionInterfaces = extensionInterfaceRepository.findByConceptMetier(conceptMetier);
//        System.out.println("extensionInterfaces ="+extensionInterfaces);
//        i = 0;
//        extensionInterfaces.forEach(a -> {
//            Optional<ValeurExtensionInterface> v = valeurExtensionInterfaceRepository.
//                    findAllByIdObjetAndExtension(idObjet == null ? "-1" : idObjet, a.getCode());
//            System.out.println("a.getCode() " + a.getCode());
//
//            ValeurExtensionInterfaceDTO val = new ValeurExtensionInterfaceDTO();
//            val.setExtension(a);
//            if (a.getComposant() == ComposantsHtml.ARBRE) {
//                val.setId("" + i);
//                i++;
//            }
//            val.setIdObjet(idObjet);
//
//            val.setExtensionId(a.getCode());
//            if (a.getImplentation() != null && !a.getImplentation().isEmpty() && extensions.get(a.getImplentation()) != null) {
//
//                val.setList(extensions.get(a.getImplentation()).findAll());
//                if (v.isPresent()) {
//                    val.setLibelle(v.get().getValeur() == null ? null : extensions.get(a.getImplentation()).findOne(v.get().getValeur()).getLibelle());
//                }
//                if (a.getHiden() != null && a.getHiden() && val.getList() != null && !val.getList().isEmpty()) {
//                    val.setValeur(val.getList().get(0).getId());
//                }
//            }
//            if (v.isPresent()) {
//                System.out.println("VALEUR EXTENSION INTERFACE IS PRESENT ");
//                System.out.println("VALEUR EXTENSION INTERFACE IS PRESENT 2 " + v.toString());
//                val.setValeur(v.get().getValeur());
//                val.setValeurs(v.get().getValeurs());
//            }
//
//            l.add(val);
//        });
//        return l;
//    }
//
//    public void deleteAllByObject(String idObjet, String conceptMetier) {
//        List<ValeurExtensionInterface> extensionInterfaces = valeurExtensionInterfaceRepository.findAllByIdObjet(idObjet, conceptMetier);
//        extensionInterfaces.stream().forEach(x -> {
//            valeurExtensionInterfaceRepository.delete(x.getId());
//        });
//    }
//
//    /**
//     * Validation d'une extension
//     *
//     * @param v extention à vilier
//     * @return
//     */
//    public ValeurExtensionInterface validate(ValeurExtensionInterfaceDTO v) {
//        if (v.getExtensionId() == null || v.getExtensionId().isEmpty()) {
//            throw new InternalServerException("Extension invalide", null);
//
//        }
//
//        ExtensionInterface exu = extensionInterfaceRepository.findOne(v.getExtensionId());
//
//        if (exu == null) {
//            throw new InternalServerException("Extension invalide", null);
//        }
//
//        if (exu.getRequired() && (v.getValeur() == null || v.getValeur().isEmpty())) {
//            throw new InternalServerException(exu.getNom() + " est requis(e)", null);
//
//        }
//        if (v.getIdObjet() == null || v.getIdObjet().isEmpty()) {
//            throw new InternalServerException("L'identifiant de l'objet est requis", null);
//
//        }
//        ValeurExtensionInterface val = new ValeurExtensionInterface();
//        val.setExtension(exu);
//        val.setValeur(v.getValeur());
//        val.setValeurs(v.getValeurs());
//        val.setIdObjet(v.getIdObjet());
//        if (exu.getImplentation() != null && !exu.getImplentation().isEmpty()) {
//            if (extensions.get(exu.getImplentation()) == null) {
//                throw new InternalServerException("Extention invalide", null);
//
//            }
//
//            if (v.getValeur() != null && !v.getValeur().isEmpty() && extensions.get(exu.getImplentation()).findOne(v.getValeur()) == null) {
//                throw new InternalServerException("Valeur invalide", null);
//            }
//
//            return val;
//        }
//        if (exu.getTypeDonnee() == null) {
//            throw new InternalServerException("L'extension utilisateur a été mal configurée", null);
//
//        }
//        if (v.getValeur() != null && !v.getValeur().isEmpty() && exu.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_DATE)) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//
//            try {
//                LOG.info("ddd " + v.getValeur());
//                sdf.parse(v.getValeur());
//            } catch (Exception e) {
//                throw new InternalServerException(exu.getNom() + " est invalide", null);
//
//            }
//
//            return val;
//        }
//        LOG.info("k9");
//        if (v.getValeur() != null && !v.getValeur().isEmpty() && exu.getTypeDonnee().getIdeCode().equals(CodificationConstant.TYPE_DONNEE_NOMBRE)) {
//
//            try {
//                Double.valueOf(v.getValeur());
//            } catch (Exception e) {
//                LOG.info("10");
//                throw new InternalServerException(exu.getNom() + " est invalide", null);
//
//            }
//
//            return val;
//        }
//        LOG.info("k10");
//        return val;
//    }
//
//    public Map<String, IExtensionInterface> getExtensions() {
//        return extensions;
//    }
//
//    public void setExtensions(Map<String, IExtensionInterface> extensions) {
//        this.extensions = extensions;
//    }
//
//}
