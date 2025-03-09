/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.repository;

import com.base.frame.socle.core.entity.Fonction;
import com.base.frame.socle.core.utils.TypeFonction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Alassani
 */
public interface FonctionRepository extends JpaRepository<Fonction, String>, JpaSpecificationExecutor<Fonction>{
    
        @Query("SELECT f FROM Fonction f WHERE f.typeFonction=:typeFonction ")
    List<Fonction> findFonctionByType(@Param("typeFonction") TypeFonction typeFonction);
}
