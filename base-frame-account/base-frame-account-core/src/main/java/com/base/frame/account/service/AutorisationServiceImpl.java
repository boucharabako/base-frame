/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.service;

import com.base.frame.account.core.repository.AutorisationRepository;
import com.base.frame.account.core.repository.ProfilRepository;
import com.base.frame.account.entity.Autorisation;
import com.base.frame.account.entity.Profil;
import com.base.frame.socle.core.dto.IdLabelObject;
import com.base.frame.socle.core.workflow.service.IAutorisationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alassani
 */
@Service
public class AutorisationServiceImpl  implements IAutorisationService{
    @Autowired
    private AutorisationRepository autorisationRepository;
    @Autowired
    private ProfilRepository profilRepository;
    
    
    @Override
    @Transactional
    public void addAutorisation(String idObjet, String idObjetAutorisant){
        
    }
    
    @Override
    public   List<IdLabelObject> getProfilAutoriseForAction(String idObjet) {
        List<Autorisation> autorisation = this.autorisationRepository.findByIdObjet(idObjet);
        List<IdLabelObject> listIdLabelObject = new ArrayList<>();
        if (autorisation != null && !autorisation.isEmpty()) {
            autorisation.stream().forEach(t -> {
                Profil p =this.profilRepository.getOne(t.getIdeObjetAutorisant());
                if(p!=null){
                IdLabelObject idLabelObject = new IdLabelObject(p.getId(), p.getIntitule());
                listIdLabelObject.add(idLabelObject);
                }
            });
        }
        return listIdLabelObject;
    }
}
