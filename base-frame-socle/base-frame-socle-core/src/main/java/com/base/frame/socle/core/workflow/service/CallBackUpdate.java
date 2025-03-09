/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;


import com.base.frame.socle.core.workflow.entity.ActionPermise;
import com.base.frame.socle.core.workflow.entity.ActionPermiseExecutor;
import com.base.frame.socle.core.workflow.repository.ActionPermiseExecutorRepository;
import com.base.frame.socle.core.workflow.repository.ActionPermiseRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Sewa
 */
@Configuration
public class CallBackUpdate {

    private static final Logger LOG = Logger.getLogger(CallBackUpdate.class.getName());

    @Autowired
    private ActionPermiseRepository actionPermiseRepository;

    @Autowired
    private ActionPermiseExecutorService actionPermiseExecutorService;
    @Autowired
    private ActionPermiseExecutorRepository actionPermiseExecutorRepository;

    @PostConstruct
    public void selectAllActionPermise() {
//        System.out.println("Entree correct callback 1");
        /**
         * Toutes les actions ayant un callback
         */
        List<ActionPermise> list = this.actionPermiseRepository.findAllActionHavingCallBack();
        list.stream().forEach(ac -> {
//            System.out.println("Entree correct callback 2");
            /**
             * Enregistre dans la nouvelle table si pas présent
             */
            Optional<ActionPermiseExecutor> ape = this.actionPermiseExecutorRepository.findByActionPermiseAndExecutor(ac.getId(), ac.getCallBack().getCode());
            if (!ape.isPresent()) {
//                System.out.println("Entree correct callback 3");
                this.actionPermiseExecutorService.saveActionPermiseExecutor(0, ac.getId(), ac.getCallBack().getCode());
                /**
                 * Rend null l'unique callBack défini avant
                 */
                ac.setCallBack(null);
                /**
                 * Met a jour l'action
                 */
                this.actionPermiseRepository.save(ac);
            }
        });
    }
}
