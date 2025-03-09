/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;

import com.base.frame.socle.core.codification.utils.CodificationConstant;
import com.base.frame.socle.core.dto.IdLabelObject;
import com.base.frame.socle.core.workflow.dto.ActionDTO;
import com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2;
import com.base.frame.socle.core.workflow.entity.ActionEffectuee;
import com.base.frame.socle.core.workflow.entity.ActionPermise;
import com.base.frame.socle.core.workflow.entity.Etat;
import com.base.frame.socle.core.workflow.entity.EtatUtilisateurAutorise;
import com.base.frame.socle.core.workflow.entity.EvenementGenere;
import com.base.frame.socle.core.workflow.entity.EvenementPossible;
import com.base.frame.socle.core.workflow.entity.Tracking;
import com.base.frame.socle.core.workflow.entity.WorkflowCycleObjet;
import com.base.frame.socle.core.workflow.repository.ActionEffectueeRepository;
import com.base.frame.socle.core.workflow.repository.ActionPermiseRepository;
import com.base.frame.socle.core.workflow.repository.EtatRepository;
import com.base.frame.socle.core.workflow.repository.EtatUtilisateurAutoriseRepository;
import com.base.frame.socle.core.workflow.repository.EvenementGenereRepository;
import com.base.frame.socle.core.workflow.repository.EvenementProgrammeRepository;
import com.base.frame.socle.core.workflow.repository.TrackingRepository;
import com.base.frame.socle.core.workflow.repository.WorkflowCycleObjetRepository;
import com.base.frame.socle.utils.exceptions.InternalServerException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alassani
 */
@Service
public class WorkfowService implements IWorkflowService {

    @Autowired
    private ActionPermiseRepository actionPermiseRepository;
    @Autowired
    private EtatRepository etatRepository;
    @Audited
    private EvenementProgrammeRepository evenementProgrammeRepository;
    @Autowired
    private EvenementGenereRepository evenementGenereRepository;
    @Autowired
    private ActionEffectueeRepository actionEffectueeRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EtatUtilisateurAutoriseRepository etatUtilisateurAutoriseRepository;
    @Autowired(required = false)
    private IAutorisationService autorisationService;
    @Autowired(required = false)
    private ICustomUtilService customUtilService;
    @Autowired
    private TrackingRepository trackingRepository;
    @Autowired
    private WorkflowCycleObjetRepository workflowCycleObjetRepository;

    @Override
    public List<ActionDTO> recupererActionsSuivantsEtat(String etatId) {

        return this.actionPermiseRepository.findByEtat(etatId);

    }

    @Override
    public Integer executerAction(String etatId, String idObjet, String idAction, String codeFonction, String commentaire, String[] users) {
        System.out.println("etatId:"+etatId +" idObjet:"+idObjet +" idAction:"+idAction+" codeFonction:"+codeFonction+" users:"+users);
        Optional<Etat> etatCourant = etatRepository.findById(etatId);
        System.out.println("etatCourant:"+etatCourant.toString());
        ActionPermise actionPermise = actionPermiseRepository.getOne(idAction);
        System.out.println("actionPermise:"+actionPermise.toString());
        //List<EvenementPossible> evenementPossibles = evenementProgrammeRepository.findByAction(idAction);
        //System.out.println("evenementPossibles:"+evenementPossibles.toString());
        boolean flag = false;
        Tracking t = new Tracking();
        try {
            System.out.println("alert1");
            ActionEffectuee ev = new ActionEffectuee();
            ActionEffectuee ev1 = new ActionEffectuee();
            ev.setAction(actionPermise);

            String remoteAddr = "";
            try {
                if (request != null) {
                    remoteAddr = request.getHeader("X-FORWARDED-FOR");
                    if (remoteAddr == null || "".equals(remoteAddr)) {
                        remoteAddr = request.getRemoteAddr();
                    }
                }
            } catch (Exception e) {
            }
            
            ev.setIp(remoteAddr);
            //ev.("1");
            ev1 = this.actionEffectueeRepository.save(ev);
            System.out.println("alert1:"+ev1);

            //for (EvenementPossible e : evenementPossibles) {
//                if (e.getEvent().getCode().equals(CodificationConstant.WF_REFERENTIEL_EVENEMENTS_CHANGEMENT_BLOCAGE_ETAT)) {
//                    flag = true;
//                }
//                EvenementGenere evg = new EvenementGenere();
//                evg.setActionPermise(actionPermise);
//                //evg.setEvenement(e);
//                //evg.setConceptMeter(paramListRepository.findParamListByCode(cpt).get());
//                evg.setIdObjet(idObjet);
//                //evg.setStatut(paramListRepository.findByCodificationAndCode(CodificationConstant.EVENT_STATUS, CodificationConstant.EVENT_STATUS_NOUVEL));
//
//                evenementGenereRepository.save(evg);

            //}
            //if (!flag) {
                if (actionPermise.getEtatSuivant() != null) {
                    //genericWorkflowDAO.saveEtat(cpt, idObjet, actionPermise.getEtatSuivant().getId());

                    t.setEtatFinal(actionPermise.getEtatSuivant());

                    List<UtilisateurDTOV2> udtos = customUtilService.getUserByUsernames(users);

                    List<ActionPermise> actionEtatSuivants = actionPermiseRepository.findByEtatWorkFlow(actionPermise.getEtatSuivant().getId());
                    HashSet<IdLabelObject> labelObjects = new HashSet<>();
                    HashSet<String> profillIds = new HashSet<>();
                    /**
                     * Recherche des profiles liés aux actions de l'etats
                     * suivant
                     */
                    actionEtatSuivants.stream().forEach(item -> {
                        labelObjects.addAll(this.autorisationService.getProfilAutoriseForAction(item.getId()));
                    });

                    labelObjects.forEach(lb -> {
                        profillIds.add(lb.getId());
                    });
                    /**
                     * Enregistrement des utilisateur autorié pour recevoir des
                     * notifications pour l'etat suivant
                     */
                    udtos.forEach(u -> {

                        for (String e : u.getProfil()) {
                            if (profillIds.contains(e)) {
                                EtatUtilisateurAutorise etu;

                                etu = new EtatUtilisateurAutorise();
                                //etu.setConceptMetier(cpt);
                                etu.setUsername(u.getUsername());
                                etu.setEtat(actionPermise.getEtatSuivant());
                                etu.setIdObjet(idObjet);

                                etatUtilisateurAutoriseRepository.save(etu);

                                break;
                            }
                        }

                    });

                }
                if (t.getEtatFinal() == null) {
                    t.setEtatFinal(etatCourant.get());
                }
                //t.setConceptMetier(paramListRepository.findParamListByCode(cpt).get());
                t.setIdObject(idObjet);
                t.setEtatInitial(etatCourant.get());
                t.setAction(actionPermise);
                t.setDateEvent(Instant.now());
                trackingRepository.save(t);
                //throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.UNKNOWN_EXCEPTION)", null);
            //}
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.UNKNOWN_EXCEPTION)", null);
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UtilisateurDTOV2> findUserByAction(String idAcion
    ) {
        ActionPermise a = actionPermiseRepository.getOne(idAcion);

        if (a == null || a.getEtatSuivant() == null) {
            return new ArrayList<>();
        }

        List<ActionPermise> l = actionPermiseRepository.findByEtatWorkFlow(a.getEtatSuivant().getId());

        List<IdLabelObject> labelObjects = new ArrayList<>();

        l.forEach(e -> {
            labelObjects.addAll(this.autorisationService.getProfilAutoriseForAction(e.getId()));

        });

        HashSet<String> profillIds = new HashSet<>();
        labelObjects.forEach(lb -> {
            profillIds.add(lb.getId());
        });

        return customUtilService.getUserByProfils(profillIds.toArray()).stream().distinct().collect(Collectors.toList());

    }
    
    /**
     * Retourne l'etat initial d'un objet geré de concept métier passé en
     * parametre
     *
     * @return
     */
    @Override
    public Etat getEtatInitial(String idWorkflow
    ) {
       
        Etat etatInitial = new Etat();
//        System.out.println("WORKFLOW CYCLE +++ " + workflowCycleObjet.toString() + " CONCEPT METIER +++ " + conceptMetier);
       

            Optional<Etat> optionalEtatInitial = etatRepository.findFirtEtatByWorkfow(idWorkflow);
            if (optionalEtatInitial.isPresent()) {
                etatInitial = optionalEtatInitial.get();
            }
        
        return etatInitial;
    }

}
