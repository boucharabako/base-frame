/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alassani
 */

@Service
public class CodificationService {
    
    public <T> Page<T> genericPage(List<T> listElements, Pageable pageRequest) {
        List<T> listResultFinal = new ArrayList<>();
        int index = ((pageRequest.getPageNumber() + 1) * (pageRequest.getPageSize())) - (pageRequest.getPageSize());
        int indexCible = this.genericGetRealIndex(listElements, (index + pageRequest.getPageSize()));
        if (!listElements.isEmpty()) {
            listResultFinal.addAll(listElements.subList(index<=listElements.size() ? index :0, indexCible<=listElements.size() ? indexCible :listElements.size()));
        }
        return new PageImpl<>(listResultFinal, pageRequest, listElements.size());
    }
    public <T> int genericGetRealIndex(List<T> list, int index) {
        while (index > list.size()) {
            index--;
        }
        return index;
    }
}
