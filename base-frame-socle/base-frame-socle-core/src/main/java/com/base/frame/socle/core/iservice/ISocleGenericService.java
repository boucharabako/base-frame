/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.iservice;

import com.base.frame.socle.core.entity.Fonction;
import com.base.frame.socle.core.entity.ParamCode;
import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.TypeFonction;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Alassani
 */
public interface ISocleGenericService {
//Service PARAM CODIFICATION

    public Optional<ParamCode> findParamCodeById(String id);

    public boolean existsParamCodeById(String id);

    public Optional<ParamCode> findParamCodeByCode(String code);

    public boolean existsParamCodeByCode(String code);

//    SERVICE PARAM LIST
    public Optional<ParamList> findParamListById(String id);

    public boolean existsParamListById(String id);

    public Optional<ParamList> findParamListByCode(String code);

    public boolean existsParamListByCode(String code);

    public List<ParamList> findParamListByCodeParamCode(String code);

    public Optional<Fonction> findFonctionCodeByCode(String code);

    public boolean existsFonctionByCode(String code);

    // SERVICE FONCTION
    public List<Fonction> findAllFonction();
    public List<Fonction> findFonctionByType(TypeFonction typeFonction);
}
