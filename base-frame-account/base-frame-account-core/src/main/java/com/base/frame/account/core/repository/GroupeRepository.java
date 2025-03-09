/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alassani
 */
public interface GroupeRepository extends JpaRepository<Groupe, String>{
    
    
}
