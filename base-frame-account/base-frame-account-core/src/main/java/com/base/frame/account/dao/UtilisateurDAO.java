/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dao;

import com.base.frame.account.entity.Utilisateur;
import com.base.frame.socle.core.codification.annotation.dao.GenericDAO;
import com.base.frame.socle.core.service.CodificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Alassani
 */
@Component
@Transactional
public class UtilisateurDAO extends GenericDAO<Utilisateur, String> {

    public UtilisateurDAO() {
        super(Utilisateur.class);
    }
    @Autowired
    private CodificationService codificationService;

    public Page<Utilisateur> findPageUtilisateur(Pageable p, String username, String firstName, String lastName, String profil, String groupe, String etat, String motCle) {

        String queryChain = "SELECT  u FROM Utilisateur u  WHERE 1=1 ";
        if (!"".equals(username) && username != null && !username.isEmpty()) {
            queryChain += " AND (upper(u.username) LIKE CONCAT('%',:username,'%') )";
        }
        if (!"".equals(firstName) && firstName != null && !firstName.isEmpty()) {
            queryChain += " AND (upper(u.firstName) LIKE CONCAT('%',:firstName,'%')) ";
        }
        if (!"".equals(lastName) && lastName != null && !lastName.isEmpty()) {
            queryChain += " AND (upper(u.lastName) LIKE CONCAT('%',:lastName,'%') )";
        }
        if (!"".equals(etat) && etat != null && !etat.isEmpty()) {
            queryChain += " AND u.etat.id=:etat ";
        }
        if (!"".equals(profil) && profil != null && !"null".equals(profil) && !profil.isEmpty()) {
            queryChain += " AND (u.id IN (SELECT up.utilisateur.id FROM UtilisateurProfil up WHERE up.profil.id=:profil ))";
        }
        if (!"".equals(groupe) && groupe != null && !"null".equals(groupe)&& !groupe.isEmpty()) {
            queryChain += " AND ((u.id IN (SELECT ug.utilisateur.id FROM  UtilisateurGroupe ug WHERE ug.groupe.id=:groupe ))";
        }
       
        if (!"".equals(motCle) && motCle != null && !motCle.isEmpty()) {
            queryChain += " AND (upper(u.username) LIKE CONCAT('%',:motCle,'%') "
                    + " OR upper(u.firstName) LIKE CONCAT('%',:motCle,'%') "
                    + " OR upper(u.lastName) LIKE CONCAT('%',:motCle,'%') "
                    + " OR upper(u.tel) LIKE CONCAT('%',:motCle,'%') "
                    + " OR upper(u.titre) LIKE CONCAT('%',:motCle,'%') "
//                    + " OR upper(u.etat.libelle) LIKE CONCAT('%',:motCle,'%') "
//                    + " OR upper(u.etat.code) LIKE CONCAT('%',:motCle,'%') "
                    + " )";
        }
        queryChain += " ORDER BY u.createdDate DESC";
        System.out.println("req:"+queryChain);
        Query query = em.createQuery(queryChain);
        if (!"".equals(motCle) && motCle != null && !motCle.isEmpty()) {
            query.setParameter("motCle", motCle);
        }
        if (!"".equals(username) && username != null && !username.isEmpty()) {
            query.setParameter("username", username);
        }
        if (!"".equals(firstName) && firstName != null && !firstName.isEmpty()) {
            query.setParameter("firstName", firstName);
        }
        if (!"".equals(lastName) && lastName != null && !lastName.isEmpty()) {
            query.setParameter("lastName", lastName);
        }
        if (!"".equals(etat) && etat != null && !etat.isEmpty()) {
            query.setParameter("etat", etat);
        }
        if (!"".equals(profil) && profil != null && !"null".equals(profil) && !profil.isEmpty()) {
            query.setParameter("profil", profil);
        }
        if (!"".equals(groupe) && groupe != null && !"null".equals(groupe) && !groupe.isEmpty()) {
            query.setParameter("groupe", groupe);
        }
        List<Utilisateur> list = query.getResultList();

        return codificationService.genericPage(list, p);

    }
}
