/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;


import com.base.frame.socle.core.workflow.dto.ActionPermiseExecutorDTO;
import com.base.frame.socle.core.workflow.entity.ActionPermiseExecutor;
import com.base.frame.socle.core.workflow.repository.ActionPermiseExecutorRepository;
import com.base.frame.socle.core.workflow.repository.CallBackRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sewa
 */
@Service
@Transactional
public class ActionPermiseExecutorService {

    @Autowired
    private ActionPermiseExecutorRepository actionPermiseExecutorRepository;
    @Autowired
    private CallBackRepository callBackRepository;

    public void saveActionPermiseExecutor(Integer numeroOrdre, String idActionPermise, String callBack) {
        if (idActionPermise != null) {
            ActionPermiseExecutor ape = new ActionPermiseExecutor();
            ape.setNumeroOrdre(numeroOrdre);
            ape.setActionPermise(idActionPermise);
            ape.setCallBack(callBack);
            this.actionPermiseExecutorRepository.save(ape);
        }
    }

    public List<ActionPermiseExecutorDTO> listExecutor() {
        List<ActionPermiseExecutorDTO> listExecutor = this.callBackRepository.findAllCalback();
        return listExecutor == null ? new ArrayList<>() : listExecutor;
    }

    public void deleteActionPermiseExecutor(String idActionPermise) {
        List<ActionPermiseExecutor> listActionPermiseExecutor = this.actionPermiseExecutorRepository.findExecutorActionPermiseByActionPermise(idActionPermise);
        this.actionPermiseExecutorRepository.deleteAll(listActionPermiseExecutor);
    }

    public List<ActionPermiseExecutorDTO> loadExecutorByActionPermise(String id) {
        List<ActionPermiseExecutorDTO> listExecutor = this.actionPermiseExecutorRepository.findListExecutorByActionPermise(id);
        return listExecutor == null ? new ArrayList<>() : listExecutor;
    }
}
