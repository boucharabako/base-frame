/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;


import com.base.frame.socle.core.workflow.dto.ActionDTO;
import com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2;
import com.base.frame.socle.core.workflow.dto.WorkFlowCycleEtatSimpleObject;
import com.base.frame.socle.core.workflow.entity.ActionPermise;
import com.base.frame.socle.core.workflow.entity.Etat;
import com.base.frame.socle.core.workflow.entity.WorkFlowCycle;
import com.base.frame.socle.core.workflow.entity.WorkflowCycleObjet;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author w.kouwonou
 * @since 06/06/2018
 * @version 1.0.0
 */
public interface IWorkflowCycleService {

    /**
     * Cette méthode permet d'exécuter une action, d'exécuter le
     * Callback(IExecutor) et génèrer les évenements si défini, enregistrer le
     * tracking associé à l'action. lève une exception en cas d'échec
     * (InternalServerException)
     *
     * @author W.KOUWONOU
     * @param codeFonction le code de la fonction sur la quelle le workflow est
     * exécuté
     * @param cpt concept metier de l'objet
     * @param idObjet identifiant de l'objet appelant
     * @param idAcion identifiant de l'action à exécuter
     * @param commentaire le commentaire
     * @param users les utilisateurs autorisés sur l'état suivant
     * @param params les paramètres du callback
     * @return retourne 1 en cas de succès 0 sinon
     */
    public Integer executerAction(String codeFonction, String cpt, String idObjet, String idAcion, String commentaire, String[] users, HashMap<String, Object> params);

    //public Integer executerActionForBatchWorkflowPaiement(String codeFonction, String cpt, String idObjet, String idAcion, String commentaire, String[] users, HashMap<String, Object> params, String userLogin);

    //public Integer executerActionWithGestionCommentaire(String codeFonction, String cpt, String idObjet, String idAcion, String commentaire, String[] users, HashMap<String, Object> params, List<ExtensionSimpleObject> extensionList);

    /**
     * Cette méthode permet d'exécuter une action,et génèrer les évenements si
     * défini, enregistrer le tracking associé à l'action pour les objets sous
     * forme d'arborescence. lève une exception en cas d'échec
     * (InternalServerException)
     *
     * @author W.KOUWONOU
     * @param cpt concepte metier de l'objet
     * @param idObjet identifiand de l'objet appelant
     * @param idAcion identifiant de l'action à executer
     * @param commentaire le commentaire
     * @param users les utilisateurs autorisés sur l'état suivant
     * @return retourne 1 en cas de succès 0 sinon
     */
    public Integer executerActionForChildren(String cpt, String idObjet, String idAcion, String commentaire, String[] users);

    /**
     * Cette permet de renvoyer les actions l'etat courant de l'objet.
     *
     * @author W.KOUWONOU
     * @param cpt concepte metier de l'objet
     * @param idObjet identifiand de l'objet appelant
     * @return Liste des actions
     */
    public List<ActionDTO> findActionByObectEtat(String cpt, String idObjet);

//    /**
//     * Cette méthode permet de rechercher les actions sur lesquelles le profil
//     * de l'utilisateur courant est autorisé par rapport à l'état actuel de
//     * l'objet
//     *
//     * @author W.KOUWONOU
//     * @param cpt concepte metier de l'objet
//     * @param idObjet identifiand de l'objet appelant
//     * @return Liste des actions
//     */
    /**
     * Cette méthode permet de rechercher les actions sur lesquelles
     * l'utilisateur est habilité et autorisé par rapport à l'état actuel de
     * l'objet
     *
     * @author rkoufionou
     * @param cpt concepte metier de l'objet
     * @param idObjet identifiand de l'objet appelant
     * @param codeFonction Code de la fonction utilisateur appelant
     * @return retourne la liste des actions.
     */
    public List<ActionDTO> findActionByObectAndEtatAndCurrentUser(String cpt, String idObjet, String codeFonction);
    public List<ActionDTO> findActionByObectAndEtatByUser(String cpt, String idObjet, String codeFonction,String user);

    public List<ActionDTO> findActionByObectAndEtatAndCurrentUserAndFunction(String cpt, String idObjet, String codeFonction);

    public List<ActionDTO> findActionByObectAndEtatAndCurrentUserAuthorize(String cpt, String idObjet, String codeFonction);
    public List<ActionDTO> findActionByObectAndEtatByUserAuthorize(String cpt, String idObjet, String codeFonction,String user);

    /**
     * Cette methode permet de rechercher les utilisateurs qui sont autorisés
     * sur une action de l'etat suivant
     *
     * @author W.KOUWONOU Recherche les utilisateur autorisé pour une action
     *
     * @param idAcion identifiant de l'action
     * @return Les utilisateurs autorisés pour cet action
     */
    public List<UtilisateurDTOV2> findUserByAction(String idAcion);

    /**
     * Cette methode permet de rechercher les utilisateurs qui sont autorisés
     * sur une action de l'etat courant
     *
     * @author W.KOUWONOU Recherche les utilisateur autorisé pour une action
     *
     * @param idAcion identifiant de l'action
     * @return Les utilisateurs autorisés pour cet action
     */
    public List<UtilisateurDTOV2> findAllowedUserOnAction(String idAcion);

    /**
     * Cette méthode permet de rechercher les utilisateurs affectés à l'état
     * suivant pendant l'exécution d'une action sur un objet.
     *
     * @author W.KOUWONOU
     * @param cpt concept metier de l'objet
     * @param idObjet identifiant de l'bjet
     * @param idAaction identifiant de l'action
     * @return liste des utilisateur
     */
    public List<UtilisateurDTOV2> findUserAuthorizeByAction(String cpt, String idObjet, String idAaction);

    /**
     * Cette méthode permet de mettre un objet à l'état initial du workflow
     *
     * @author W.KOUWONOU Initialise les le workflow d'un d'un objet
     * @param cpt Concept métier
     * @param idObjet identifiant de l'objet
     * @param idWorkflow
     */
    public void initWorkflowId(String cpt, String idObjet, String idWorkflow);

    /**
     * Cette méthode permet de mettre un objet dans un état quelconque du
     * workflow Initialise le workflow d'un object avec un état fournis
     * (forçage)
     *
     * @author W.KOUWONOU
     * @param cpt Concept métier
     * @param idObjet Identifiant de l'objet
     * @param etat Etat cible
     */
    public void initWorkflowByEtat(String cpt, String idObjet, Etat etat);

    /**
     * Cette méthode permet de mettre un objet dans un état quelconque du
     * workflow Initialise le workflow d'un object avec un état fournis
     * (forçage) et permet de mettre un commentaire sur le tracking
     *
     * @author KOKOU WAMA DJIMISSA
     * @param cpt Concept métier
     * @param idObjet Identifiant de l'objet
     * @param etat Etat cible
     */
    public void initWorkflowByEtatAndSetTrackingComment(String cpt, String idObjet, Etat etat, String commentaire);

    /**
     * Cette méthode permet de vérifier si l'etat passé en paramètre est l'état
     * initial du workflow
     *
     * @author PIERRE
     * @param workFlowCycle
     * @param etat
     * @return retourne True ou false
     */
    public boolean isInitalEtatOfWorkFlow(WorkFlowCycle workFlowCycle, Etat etat);

    /**
     * Cette méthode permet de vérifier si l'etat passé en paramètre est une
     * terminaison du workflow
     *
     * @author PIERRE
     * @param etat Etat passé en paramètre
     * @return True ou false
     */
    public boolean etatIsTerminaisonOfWorkFlow(Etat etat);
    public boolean etatIsTerminaisonOfWorkFlow(String etat);

    /**
     * Cette méthode permet de vérifier si le profil de l'utilisateur est
     * autorisé sur l'action passée en paramètre
     *
     * @author PIERRE
     * @param actionPermiseId Identifiant de l'action
     * @param profilId Identifiant du profil
     * @return retourne True ou False
     */
    public boolean isProfilAllowONAction(String actionPermiseId, String profilId);

    /**
     * Cette méthode permet de vérifier si les modifications sont autorisées sur
     * l'état passé en paramètre
     *
     * @author PIERRE
     * @param etat Etat passé en paramètre
     * @return
     */
    public boolean isEditAuthorisedForEtat(Etat etat);

//    /**
//     * Cette méthode permet de vérifier si le rang de la séquence passé en
//     * paramètre est égale à la séquence recherché
//     *
//     * @param idWorkflow (identifiant du workflow)
//     * @param sequence Séquence passé en paramètre
//     * @param positionSequence (Rang de la séquence recherché)
//     * @return retourn True ou false
//     */
    /**
     * Cette méthode retourne l'etat initial d'un objet geré
     *
     * @param conceptMetier concept métier
     * @return
     */
    public Etat getEtatInitial(String conceptMetier);

    /**
     * Cette méthode permet vérifier si le workflow est actif
     *
     * @param conceptMetier
     * @return
     */
    public boolean workFlowIsActif(String conceptMetier);

    public WorkFlowCycle workFlowActif(String conceptMetier);

    /**
     * Cette méthode permet de recupérer tous les Etats d'un workflow dont le
     * concept metier de l'objet géré est passé en paramètre
     *
     * @param conceptMetier
     * @return
     */
    public List<WorkFlowCycleEtatSimpleObject> getAllEtat(String conceptMetier);

    /**
     * Cette méthode permet de verifier si le workflow d'une fonction utilisant
     * les objets géré est bien configuré
     *
     * @param conceptMetier Le concept metier de l'objet
     * @param errorConstantWkfNotConfigured Constante d'erreur pour un workflow
     * non configuré
     * @param errorConstantWkfNotValide Constant d'erreur pour un workflow mal
     * configuré
     * @return
     */
    public WorkflowCycleObjet findWorkFlowCycleObjectOrThrowError(String conceptMetier, String errorConstantWkfNotConfigured, String errorConstantWkfNotValide);

    /**
     * Cette méthode permet de recuperer l'état initial d'un workflow a objet
     * géré
     *
     * @param conceptMetier Concept métier
     * @param errorConstantWkfNotConfigured constant d'erreur pour workflow non
     * configuré
     * @param errorConstantWkfNotValide constant d'erreur pour workflow mal
     * configuré
     * @return retourne l'Etat initial du workflow à objet géré
     */
    public Etat getEtatInitialOrThrowError(String conceptMetier, String errorConstantWkfNotConfigured, String errorConstantWkfNotValide);

    /**
     * Cette méthode permet de recupérer le rang de la séquence de l'état
     * courant passé en paramètre
     *
     * @param etat Etat courant du workflow
     * @return retourne un entier. (Exemple: 0 pour la première séquence, 1 pour
     * la deuxième sequence ...)
     */
    public Integer currentSequence(Etat etat);

    public Integer currentSequenceDistinct(Etat etat);

    /**
     * Cette méthode permet de recupérer le rang de la séquence de l'etat
     * suivant de l'action passée en paramètre.
     *
     * @param actionPermise Action à executer
     * @return retourne le rang de la prochaine sequence de l'etat suivant du
     * workflow
     */
    public Integer nextSequence(ActionPermise actionPermise);

    /**
     * Cette méthode permet de recupérer le rang de l'état courant par rapport à
     * sa séquence
     *
     * @param etat Etat passé en paramètre
     * @return retourne le rang de l'etat courant par rapport à sa séquence.
     */
    public Integer currentEtape(Etat etat);

    /**
     * Cette méthode permet de recupérer le rang de l'état suivant de l'action
     * par rapport à sa séquence
     *
     * @param actionPermise Action passé en paramètre
     * @return retourne le rang de l'état suivant de l'action par rapport à sa
     * séquence
     */
    public Integer nextEtape(ActionPermise actionPermise);

//    /**
//     * Cette méthode permet de forcer le passage à un état du workflow et
//     * d'insérer le tracking
//     *
//     * @param etatSuivant Etat cible
//     * @param idObjet Identifiant de l'objet
//     * @param conceptMetier Le concept métier de l'objet
//     */
    /**
     * Cette méthode vérifie si l'état en paramètre est une terminaison de son
     * workflow.
     *
     *
     * @param etat Etat passé en paramètre
     * @return retourne true ou false
     */
    public boolean isTerminaisonWorkflow(Etat etat);
    
    public List<Etat> terminaisonEtats(String wkf);

    /**
     * Retourne la derniere séquence d'un workflow
     *
     * @param idw
     * @return
     */
    public Etat getLastSequenceLastEtat(String idw);

    /**
     * Retourne un objet état en fonction de l'index de sa séquence et de
     * l'index de son état
     *
     * @param idw
     * @param sequenceIndex
     * @param sequenceEtat
     * @return
     */
    public Etat getEtatByRangSequence(String idw, Integer sequenceIndex, Integer sequenceEtat);

    //public void executerActionSaveComment(String idObjet, List<ExtensionSimpleObject> extensionList);

    public String getCodeEtatActionSuivante(String idActionPermise);

    public boolean etatSuivantIsTerminaison(String idActionPermise);

    public boolean etatIsBeforeFirstEtapeValidation(Etat etat);

    /**
     * Cette méthode permet de mettre un objet dans un état quelconque du
     * workflow Initialise le workflow d'un object avec un état parameter
     *
     * @author layarma
     * @param cpt Concept métier
     * @param idObjet Identifiant de l'objet
     * @param etat Etat cible
     */
    public void initWorkflowByEtatParametrer(String cpt, String idObjet, Etat etat);
    public boolean getCommentRequired(String idActionPermise);
}
