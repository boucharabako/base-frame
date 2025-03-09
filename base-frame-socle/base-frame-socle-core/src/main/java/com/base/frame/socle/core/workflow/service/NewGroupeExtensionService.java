///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.InternalServerException;
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.dto.GroupeExtensionObject;
//import com.ngs.core.codification.dto.StatutDto;
//import com.ngs.core.codification.entities.Extension;
//import com.ngs.core.codification.entities.GroupeExtension;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.ExtensionRepository;
//import com.ngs.core.codification.repositories.GroupeExtensionRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.SprRoleConstantes;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author KOMLA
// */
//@Service
//public class NewGroupeExtensionService {
//
//    @Autowired
//    private GroupeExtensionRepository groupeExtensionRepository;
//    @Autowired
//    private NProperties properties;
//    @Autowired
//    private CodifListRepository codifListRepository;
//    @Autowired
//    private ExtensionRepository extensionRepository;
//    @Autowired
//    private TrackingParamObjetService trackingParamObjetService;
//    @Autowired
//    NextframeModeleValidationService nextframeModeleValidationService;
//    
//    public Object[] customNumberSequence(Integer from, Integer to) {
//        List<Integer> value = new ArrayList<>();
//        for (int i = from; i <= to; i++) {
//            value.add(i);
//        }
//        return value.toArray();
//    }
//
//    @Transactional(readOnly = true)
//    public Page<GroupeExtensionObject> findBySpecTerm(Pageable pageRequest) {
//        Page<GroupeExtension> searchResultPage = this.groupeExtensionRepository.findAll(pageRequest);
//        return this.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<GroupeExtensionObject> findBySpecTermSimple(Specification<GroupeExtension> customerSpec, Pageable pageRequest) {
//        List<GroupeExtensionObject> listDto = new ArrayList<>();
//        if (customerSpec == null) {
//            return new PageImpl<>(listDto, pageRequest, listDto.size());
//        }
//        Page<GroupeExtension> searchResultPage = groupeExtensionRepository.findAll(customerSpec, pageRequest);
//        return this.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<GroupeExtensionObject> mapEntityPageIntoDTOPage(Pageable page, Page<GroupeExtension> source) {
//        List<GroupeExtensionObject> listGpr = mapEntitiesIntoDTOs(source.getContent());
//        return new PageImpl<>(listGpr, page, source.getTotalElements());
//    }
//
//    @Transactional(readOnly = true)
//    public List<GroupeExtensionObject> mapEntitiesIntoDTOs(List<GroupeExtension> entities) {
//        List<GroupeExtensionObject> result = new ArrayList<>();
//        entities.stream().map(temp -> this.mapEntityIntoDTO(temp)).forEachOrdered(obj -> {
//            result.add(obj);
//        });
//        return result;
//    }
//
//    @Transactional(readOnly = true)
//    public GroupeExtensionObject mapEntityIntoDTO(GroupeExtension ge) {
//        GroupeExtensionObject geDTO = new GroupeExtensionObject();
//        if (ge.getId() != null || !ge.getId().isEmpty()) {
//            geDTO.setId(ge.getId());
//            geDTO.setTitre(ge.getTitre());
//            geDTO.setDescription(ge.getDescription());
//            if (ge.getCodeConcept() != null) {
//                geDTO.setConceptMetier(ge.getCodeConcept().getId());
//            }
//            
//            List<Extension> listeExt = ge.getExtensions();
//            List<String> listExtension = new ArrayList<>();
//                listeExt.stream().forEach(mp->{
//                    listExtension.add(mp.getId());
//                });
//                geDTO.setExtension(listExtension);
//            
//            
//            StatutDto st = new StatutDto();
//            if (ge.getStatut() != null) {
//                st.setIdeCode(ge.getStatut().getIdeCode());
//                st.setIdeCodeStatutSuivant(st.getNextStatutIdeCode());
//                st.setLibelle(properties.findStatutByCode(st.getIdeCode()) != null ? properties.findStatutByCode(st.getIdeCode()).getLibelle() : "");
//                st.setLibelleStatutSuivant(properties.findActionByCode(st.getIdeCode()) != null ? properties.findActionByCode(st.getIdeCode()).getLibelle() : "");
//                geDTO.setStatut(st);
//                geDTO.setIsEnSaisie(ge.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE));
//                geDTO.setIsActif(ge.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_ACTIF));
//                geDTO.setIsObsolete(ge.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_OBSOLETE));
//            }
//
//        }
//        return geDTO;
//    }
//
//    public GroupeExtension saveGroupeExtension(GroupeExtensionObject gdto) {
//        this.controleChamp(gdto);
//        GroupeExtension grep;
//        if (gdto.getId() == null || gdto.getId().isEmpty()) {
//            grep = new GroupeExtension();
//            grep.setDescription(gdto.getDescription());
//            grep.setTitre(gdto.getTitre());
//            grep.setCodeConcept(this.codifListRepository.findOne(gdto.getConceptMetier()));
//            grep.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_EN_SAISIE));
//            this.nextframeModeleValidationService.validate(grep);
//            List<Extension> listExt = new ArrayList<>();
//            for (String idExt : gdto.getExtension()) {
//                if (this.extensionRepository.exists(idExt)) {
//                    listExt.add(this.extensionRepository.findOne(idExt));
//                }
//            }
//            
//            grep.setExtensions(listExt);
//            
//            GroupeExtension ge = groupeExtensionRepository.save(grep);
//            this.trackingParamObjetService.saveStatusTracking(ge.getId(), CodificationConstant.CONCEPT_METIER_GROUPE_EXTENSION, this.properties.findStatutByCode(ge.getStatut().getIdeCode()));
//        } else {
//            grep = this.groupeExtensionRepository.findOne(gdto.getId());
//            if (grep.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//                grep.setId(gdto.getId());
//            }
//            
//            grep.setDescription(gdto.getDescription());
//            grep.setTitre(gdto.getTitre());
//            grep.setCodeConcept(this.codifListRepository.findOne(gdto.getConceptMetier()));
//
//            List<Extension> listExt = new ArrayList<>();
//            for (String idExt : gdto.getExtension()) {
//                if (this.extensionRepository.exists(idExt)) {
//                    listExt.add(this.extensionRepository.findOne(idExt));
//                }
//            }
//            grep.setExtensions(listExt);
//            if(listExt.isEmpty()){
//                throw new InternalServerException(CodificationConstant.EXTENSION_OBLIGATOIRE, null);
//            }
//            groupeExtensionRepository.save(grep);
//            this.nextframeModeleValidationService.validate(grep);
//        }
//
//        return grep;
//    }
//
//    public void deleteGroupeExtension(String id) {
//            GroupeExtension ge = this.groupeExtensionRepository.findOne(id);
//            if (ge.getId() != null || ge.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//                this.groupeExtensionRepository.delete(id);
//            }
//    }
//
//    public void changeStatutGrp(GroupeExtension grep) {
//        if (grep != null && grep.getId() != null) {
//            if (grep.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_OBSOLETE)) {
//            }
//            if (grep.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_ACTIF)) {
//                grep.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_OBSOLETE));
//            }
//            if (grep.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//                grep.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_ACTIF));
//            }
//            groupeExtensionRepository.saveAndFlush(grep);
//            trackingParamObjetService.saveStatusTracking(grep.getId(), CodificationConstant.CONCEPT_METIER_GROUPE_EXTENSION, grep.getStatut());
//        }
//    
//    }
//    
//    public void controleChamp(GroupeExtensionObject geo){
//        if(geo.getExtension()==null || geo.getExtension().isEmpty()){
//            throw new InternalServerException(SprRoleConstantes.PROPRIETE_EXTENSION_OBLIGATOIRE, null);
//        }
//        if(geo.getDescription()==null || geo.getDescription().isEmpty()){
//            throw new InternalServerException(SprRoleConstantes.PROPRIETE_EXTENSION_DESCRIPTION_OBLIGATOIRE, null);
//        }
//        if(geo.getTitre()==null || geo.getTitre().isEmpty()){
//            throw new InternalServerException(SprRoleConstantes.PROPRIETE_EXTENSION_TITRE_OBLIGATOIRE, null);
//        }
//    }
//
//}
