/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.service;

import com.base.frame.socle.core.entity.Fonction;
import com.base.frame.socle.core.entity.ParamCode;
import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.iservice.ISocleGenericService;
import com.base.frame.socle.core.repository.FonctionRepository;
import com.base.frame.socle.core.repository.ParamCodeRepository;
import com.base.frame.socle.core.repository.ParamListRepository;
import com.base.frame.socle.core.utils.TypeFonction;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alassani
 */
@Service
@Transactional
public class SocleGenericService implements ISocleGenericService {

    @Autowired
    private ParamCodeRepository paramCodeRepository;
    @Autowired
    private ParamListRepository paramListRepository;
    @Autowired
    private FonctionRepository fonctionRepository;

    @Override
    public Optional<ParamCode> findParamCodeById(String id) {
        return this.paramCodeRepository.findById(id);
    }

    @Override
    public boolean existsParamCodeById(String id) {
        return this.paramCodeRepository.existsById(id);
    }

    @Override
    public Optional<ParamCode> findParamCodeByCode(String code) {
        return this.paramCodeRepository.findParamCodeByCode(code);
    }

    @Override
    public boolean existsParamCodeByCode(String code) {
        return this.findParamCodeByCode(code).isPresent();
    }

    @Override
    public Optional<ParamList> findParamListById(String id) {
        return this.paramListRepository.findById(id);
    }

    @Override
    public boolean existsParamListById(String id) {
        return this.paramListRepository.existsById(id);
    }

    @Override
    public Optional<ParamList> findParamListByCode(String code) {
        return this.paramListRepository.findParamListByCode(code);
    }

    @Override
    public boolean existsParamListByCode(String code) {
        return this.findParamListByCode(code).isPresent();
    }

    @Override
    public List<ParamList> findParamListByCodeParamCode(String code) {
        return this.paramListRepository.findParamListByCodeParamCode(code);
    }

    @Override
    public Optional<Fonction> findFonctionCodeByCode(String code) {
        return this.fonctionRepository.findById(code);
    }

    @Override
    public boolean existsFonctionByCode(String code) {
        return this.fonctionRepository.existsById(code);
    }

    @Override
    public List<Fonction> findAllFonction() {
        return this.fonctionRepository.findAll();
    }
     @Override
    public List<Fonction> findFonctionByType(TypeFonction typeFonction) {
        return this.fonctionRepository.findFonctionByType(typeFonction);
    }

}
