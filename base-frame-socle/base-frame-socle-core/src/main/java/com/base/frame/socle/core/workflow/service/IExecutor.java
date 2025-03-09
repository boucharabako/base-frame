/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;

import com.base.frame.socle.utils.exceptions.InternalServerException;
import java.util.Map;

/**
 * Interface pour les callback des workflows
 *
 * @author WIlliam KOUWONOU 
 * @since 17/09/2018
 * @version 1.0.0
 */
public interface IExecutor {

    /**
     *
     * Execute des traitement avant d'executer une action sur le workflow
     *
     * @author W.KOUWONOU
     * @since 17/09/2018
     * @param argument les arguments de la fonction
     * @return
     * @throws InternalServerException
     */
    public boolean execut(Map<String, Object> argument) throws InternalServerException;
}
