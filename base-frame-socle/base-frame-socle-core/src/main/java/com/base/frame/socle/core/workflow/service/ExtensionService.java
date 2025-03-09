///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.InternalServerException;
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.dto.ProprieteObject;
//import com.ngs.core.codification.dto.StatutDto;
//import com.ngs.core.codification.entities.ProprieteEtendu;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.ExtensionRepository;
//import com.ngs.core.codification.repositories.ProprieteEtenduRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.SprRoleConstantes;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author KOMLA
// */
//@Service
//public class ExtensionService {
//
//    @Autowired
//    private ProprieteEtenduRepository proprieteEtenduRepository;
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
//    public ProprieteEtendu savedPropriete(ProprieteObject pdto) {
//        this.controleChamps(pdto);
//        ProprieteEtendu pe;
//        if (pdto.getId() == null || pdto.getId().isEmpty()) {
//            pe = new ProprieteEtendu();
//            pe.setIde(pdto.getIde());
//            pe.setLibelle(pdto.getLibelle());
//            pe.setCaption(pdto.getCaption());
//            pe.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_EN_SAISIE));
//             this.nextframeModeleValidationService.validate(pe);
//           ProprieteEtendu popri= proprieteEtenduRepository.save(pe);
//            this.trackingParamObjetService.saveStatusTracking(popri.getId(), CodificationConstant.CONCEPT_METIER_PROPRIETE_ETENDU, 
//                    this.properties.findStatutByCode(popri.getStatut().getIdeCode()));
//        } else {
//           pe = this.proprieteEtenduRepository.findOne(pdto.getId());
//            
//           if(pe.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)){
//               pe.setIde(pdto.getIde());
//           }
//
//            pe.setLibelle(pdto.getLibelle());
//            pe.setCaption(pdto.getCaption());
//            proprieteEtenduRepository.save(pe);
//         this.nextframeModeleValidationService.validate(pe);
//        }
//        return pe;
//    }
//    
//    public void delete(String id) {
//            ProprieteEtendu pre = this.proprieteEtenduRepository.findOne(id);
//            if(pre.getId()!=null && pre.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)){
//        this.proprieteEtenduRepository.delete(id);}
//
//    }
//
//    @Transactional(readOnly = true)
//    public Page<ProprieteObject> findBySpecTerm(Pageable pageRequest) {
//        Page<ProprieteEtendu> searchResultPage = this.proprieteEtenduRepository.findOrderedProp(pageRequest);
//        return this.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<ProprieteObject> findBySpecTermSimple(Pageable pageRequest) {
//        Page<ProprieteEtendu> searchResultPage = proprieteEtenduRepository.findOrderedProp(pageRequest);
//        return this.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }
//    @Transactional(readOnly = true)
//    public Page<ProprieteObject> findBySpecTermSimpleMotCle(Pageable pageRequest,String motCle) {
//        Page<ProprieteEtendu> searchResultPage = proprieteEtenduRepository.findOrderedPropByMotCle(pageRequest,motCle);
//        return this.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<ProprieteObject> mapEntityPageIntoDTOPage(Pageable page, Page<ProprieteEtendu> source) {
//        List<ProprieteObject> liPro = mapEntitiesIntoDTOs(source.getContent());
//        return new PageImpl<>(liPro, page, source.getTotalElements());
//    }
//
//    @Transactional(readOnly = true)
//    public List<ProprieteObject> mapEntitiesIntoDTOs(List<ProprieteEtendu> entities) {
//        List<ProprieteObject> result = new ArrayList<>();
//        entities.stream().map(temp -> this.mapEntityIntoDTO(temp)).forEachOrdered(obj -> {
//            result.add(obj);
//        });
//        return result;
//    }
//
//    @Transactional(readOnly = true)
//    public ProprieteObject mapEntityIntoDTO(ProprieteEtendu pe) {
//        ProprieteObject pDto = new ProprieteObject();
//        if (pe.getId() != null || !pe.getId().isEmpty()) {
//            pDto.setId(pe.getId());
//            pDto.setIde(pe.getIde());
//            pDto.setLibelle(pe.getLibelle());
//            pDto.setCaption(pe.getCaption());
//            StatutDto st = new StatutDto();
//            if(pe.getStatut()!=null){
//                st.setIdeCode(pe.getStatut().getIdeCode());
//                st.setIdeCodeStatutSuivant(st.getNextStatutIdeCode());
//                st.setLibelle(properties.findStatutByCode(st.getIdeCode())!=null ? properties.findStatutByCode(st.getIdeCode()).getLibelle():"");
//                st.setLibelleStatutSuivant(properties.findActionByCode(st.getIdeCode())!=null ? properties.findActionByCode(st.getIdeCode()).getLibelle():"");
//                pDto.setStatut(st);
//                pDto.setIsEnSaisie(pe.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE));
//                pDto.setIsActif(pe.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_ACTIF));
//                pDto.setIsObsolete(pe.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_OBSOLETE));
//            }
//
//        }
//        return pDto;
//    }
//
//    public void changeStatutPro(ProprieteEtendu pret){
//        if(pret!=null && pret.getId()!=null){
//        if(pret.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_OBSOLETE)) {
//        }
//        if(pret.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_ACTIF)){
//            pret.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_OBSOLETE));
//        }
//        if(pret.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)){
//            pret.setStatut(codifListRepository.findByCodificationAndCode(CodificationConstant.STATUT_PARAM, CodificationConstant.STATUT_PARAM_ACTIF));
//        }
//        proprieteEtenduRepository.saveAndFlush(pret);
//        trackingParamObjetService.saveStatusTracking(pret.getId(), CodificationConstant.CONCEPT_METIER_PROPRIETE_ETENDU, pret.getStatut());
//        }
//       
//    }
//    public void controleChamps(ProprieteObject pDto){
//        if(pDto.getIde()==null ||pDto.getIde().isEmpty() ){
//               throw new InternalServerException(SprRoleConstantes.PROPRIETE_EXTENSION_CODE_OBLIGATOIRE, null);
//        }
//        if(pDto.getLibelle()==null ||pDto.getLibelle().isEmpty() ){
//               throw new InternalServerException(SprRoleConstantes.PROPRIETE_EXTENSION_LIBELLE_OBLIGATOIRE, null);
//        }
//        if(pDto.getCaption()==null ||pDto.getCaption().isEmpty() ){
//               throw new InternalServerException(SprRoleConstantes.PROPRIETE_EXTENSION_LEGENDE_OBLIGATOIRE, null);
//        }
//    }
//          
//}
