///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.InternalServerException;
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.dto.ClasseurDTO;
//import com.ngs.core.codification.entities.Classeur;
//import com.ngs.core.codification.entities.Codification;
//import com.ngs.core.codification.repositories.ClasseurRepository;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.CodificationRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.RestConstants;
//import com.ngs.core.codification.utils.TypeError;
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// *
// * @author kouwonou
// */
//@Component
//public class ClasseurService {
//
//    @Autowired
//    private NProperties properties;
//    @Autowired
//    private ErrorMessageService errorMessage;
//    @Autowired
//    private ClasseurRepository classeurRepository;
//    @Autowired
//    private CodificationRepository codificationRepository;
//    @Autowired
//    private CodifListRepository codifListRepository;
//
//    @Transactional
//    public Object deleteCodification(@RequestBody ClasseurDTO dto) {
//        if (dto.getCodification() == null || dto.getCodification().isEmpty()) {
//            throw new InternalServerException("L'identifiant de l'élément à suprrimer est réquise", null);
//        }
//
//        Codification c = codificationRepository.findOne(dto.getCodification());
//
//        if (!c.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//            throw new InternalServerException("Opération impossible. La codification  est deja activée", null);
//
//        }
//
//        List<String> rootClasseurs = classeurRepository.findRoot(dto.getCodification());
//        rootClasseurs.stream().forEach(x -> {
//            this.deleteClasseurCascade(x);
//        });
//        codificationRepository.delete(dto.getCodification());
//        this.deleteClasseurCascade(dto.getId());
//        HashMap<String, Object> model = new HashMap<>();
//        model.put(RestConstants.MSG_KEY, this.errorMessage.getApiError("400", TypeError.INFO, properties.getInfoPrase(), new HashMap<>()));
//        return model;
//    }
//
//    public void deleteClasseurItems(String codif){
//         List<String> rootClasseurs = classeurRepository.findRoot(codif);
//        rootClasseurs.stream().forEach(x -> {
//            this.deleteClasseurCascade(x);
//        });
//    }
//    @Transactional
//    public Object deleteClasseur(@RequestBody ClasseurDTO dto) {
//        if (dto.getId() == null || dto.getId().isEmpty()) {
//            throw new InternalServerException("L'identifiant de l'élément à suprrimer est réquise", null);
//        }
//
//        Classeur c = classeurRepository.findOne(dto.getId());
//
//        if (!c.getCodification().getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//            throw new InternalServerException("Opération impossible. La codification  est deja activée", null);
//
//        }
//        this.deleteClasseurCascade(dto.getId());
//        
//        classeurRepository.flush();
//        HashMap<String, Object> model = new HashMap<>();
//        List<Classeur> classeurDTOs = classeurRepository.findByCodif(c.getCodification().getCode());
//        model.put("classeurs", classeurDTOs.stream().map(x-> new ClasseurDTO(x.getId(),
//                x.getCode(), x.getLibelle(), x.getParent())).collect(Collectors.toList()));
//        model.put(RestConstants.MSG_KEY, this.errorMessage.getApiError("400", TypeError.INFO, properties.getInfoPrase(), new HashMap<>()));
//        return model;
//    }
//
//    public void deleteClasseurCascade(String id) {
//        if (id != null) {
//            List<String> filles = classeurRepository.findFilleByParent(id);
//
//            filles.stream().forEach(x -> {
//                this.deleteClasseurCascade(x);
//            });
//            classeurRepository.delete(id);
//        }
//
//    }
//}
