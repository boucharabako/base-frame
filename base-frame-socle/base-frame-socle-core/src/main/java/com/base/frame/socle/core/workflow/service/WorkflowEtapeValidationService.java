/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;

//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.repositories.EtatRepository;
//import com.ngs.core.codification.repositories.TrackingRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
import com.base.frame.socle.core.codification.utils.CodificationConstant;
import com.base.frame.socle.core.workflow.repository.EtatRepository;
import com.base.frame.socle.core.workflow.repository.TrackingRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author william
 */
@Service
public class WorkflowEtapeValidationService implements IWorkflowEtapeValidationService {
@Autowired
    private EtatRepository etatRepository;
@Autowired TrackingRepository trackingRepository;

    @Override
    public boolean isBeforeFirstEtapeValidation(String concepteMetier, String idObjet, String idEtape) {

        String idWkf = etatRepository.findIdWorklowByIdEtat(idEtape);
        Integer sequence = etatRepository.findSequenceWorklowByIdEtat(idEtape);

        List<String> etapesValidation = etatRepository.findEtapeValidationOrderBySequence(idWkf, CodificationConstant.OUI_NON_OUI);
        List<Integer> sequencesEtapesValidation = etatRepository.findSequenceEtapeValidation(idWkf, CodificationConstant.OUI_NON_OUI);
        if (etapesValidation.isEmpty()) {
            return false;
        }
        return sequencesEtapesValidation.get(0) >= sequence && !etapesValidation.get(0).equals(idEtape);

    }

    @Override
    public boolean isOnFirstEtapeValidation(String concepteMetier, String idObjet, String idEtape) {

        String idWkf = etatRepository.findIdWorklowByIdEtat(idEtape);
        Integer sequence = etatRepository.findSequenceWorklowByIdEtat(idEtape);

        List<String> etapesValidation = etatRepository.findEtapeValidationOrderBySequence(idWkf, CodificationConstant.OUI_NON_OUI);
        List<Integer> sequencesEtapesValidation = etatRepository.findSequenceEtapeValidation(idWkf, CodificationConstant.OUI_NON_OUI);
        if (etapesValidation.isEmpty()) {
            return false;
        }
        return etapesValidation.get(0).equals(idEtape);
    }

    @Override
    public boolean isBetweenFirstAndSecondEtapeValidation(String concepteMetier, String idObjet, String idEtape) {

        String idWkf = etatRepository.findIdWorklowByIdEtat(idEtape);
        Integer sequence = etatRepository.findSequenceWorklowByIdEtat(idEtape);

        List<String> etapesValidation = etatRepository.findEtapeValidationOrderBySequence(idWkf, CodificationConstant.OUI_NON_OUI);
        List<Integer> sequencesEtapesValidation = etatRepository.findSequenceEtapeValidation(idWkf, CodificationConstant.OUI_NON_OUI);
        if (etapesValidation.size() < 2) {
            return false;
        }
        return sequencesEtapesValidation.get(0)<sequence && sequencesEtapesValidation.get(1)>=sequence &&
                 !etapesValidation.get(1).equals(idEtape) && trackingRepository.findByEtats(idObjet, concepteMetier, etapesValidation).size()==1;

    }

    @Override
    public boolean isOnSecondEtapeValidation(String concepteMetier, String idObjet, String idEtape) {

        String idWkf = etatRepository.findIdWorklowByIdEtat(idEtape);
        Integer sequence = etatRepository.findSequenceWorklowByIdEtat(idEtape);

        List<String> etapesValidation = etatRepository.findEtapeValidationOrderBySequence(idWkf, CodificationConstant.OUI_NON_OUI);
        List<Integer> sequencesEtapesValidation = etatRepository.findSequenceEtapeValidation(idWkf, CodificationConstant.OUI_NON_OUI);
       
        
        if (etapesValidation.size() < 2) {
            return false;
        }
        return  etapesValidation.get(1).equals(idEtape);

    }

    @Override
    public boolean isPassedSecondEtapeValidation(String concepteMetier, String idObjet, String idEtape) {
  
     String idWkf = etatRepository.findIdWorklowByIdEtat(idEtape);
        Integer sequence = etatRepository.findSequenceWorklowByIdEtat(idEtape);

        List<String> etapesValidation = etatRepository.findEtapeValidationOrderBySequence(idWkf, CodificationConstant.OUI_NON_OUI);
        List<Integer> sequencesEtapesValidation = etatRepository.findSequenceEtapeValidation(idWkf, CodificationConstant.OUI_NON_OUI);
        if (etapesValidation.size() < 2) {
            return false;
        }
        
        System.out.println("********** trackingRepository.findByEtats "+trackingRepository.findByEtats(idObjet, concepteMetier, etapesValidation).size());
        return  trackingRepository.findByEtats(idObjet, concepteMetier, etapesValidation).size()>=2;

    
    }

    @Override
    public boolean isNotPassAnyEtapeValidation(String concepteMetier, String idObjet, String idEtape) {
String idWkf = etatRepository.findIdWorklowByIdEtat(idEtape);
        Integer sequence = etatRepository.findSequenceWorklowByIdEtat(idEtape);

        List<String> etapesValidation = etatRepository.findEtapeValidationOrderBySequence(idWkf, CodificationConstant.OUI_NON_OUI);
        List<Integer> sequencesEtapesValidation = etatRepository.findSequenceEtapeValidation(idWkf, CodificationConstant.OUI_NON_OUI);
        if (etapesValidation.size() < 2) {
            return false;
        }
        
        return trackingRepository.findByEtats(idObjet, concepteMetier, etapesValidation).isEmpty() && !etapesValidation.get(0).equals(idEtape);

    }

}
