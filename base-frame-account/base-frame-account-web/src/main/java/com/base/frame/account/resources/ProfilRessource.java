///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.base.frame.account.resources;


import com.base.frame.socle.core.iservice.ISocleGenericService;


import com.base.frame.account.dto.ProfilDTO;
import com.base.frame.account.dto.HabilitationDTO;
import com.base.frame.account.dto.UtilisateurDTO;
import com.base.frame.account.iservice.IProfilService;
import com.base.frame.account.iservice.IUtilisateurService;
import com.base.frame.account.utils.AccountConstant;
import com.base.frame.socle.core.codification.utils.ApiError;
import com.base.frame.socle.core.codification.utils.ErrorMessage;
import com.base.frame.socle.core.codification.utils.RestConstants;
import com.base.frame.socle.core.codification.utils.TypeError;
//import com.base.frame.socle.core.;
import com.base.frame.socle.core.iservice.ISocleGenericService;
import com.base.frame.socle.core.service.ErrorMessageService;

import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.core.utils.TypeFonction;
import com.base.frame.socle.core.workflow.dto.WorkflowCycleParameter;
import com.base.frame.socle.utils.Constants;
import com.base.frame.socle.utils.Pager;
import com.base.frame.socle.utils.validators.MessageSourceKV;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Alassani
 */
@RestController
@RequestMapping(path = "api/gestion/profil")
//@PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_PROFIL + "')")
public class ProfilRessource {

    @Autowired
    private IProfilService profilService;
    @Autowired
    private MessageSourceKV messageSource;

    @Autowired
    private ISocleGenericService socleGenericService;
       @Autowired
    private IUtilisateurService utilisateurService;
       @Autowired
       protected ErrorMessageService errorMessage;

    /**
     * Cette méthode récupère la list des profils
     *
     * @param pageSize
     * @param page
     * @param mc
     * @return
     */
//       @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_PROFIL_LIRE + "')")
    @RequestMapping(value = "/list/{pageSize}/{page}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> listProfil(
            @PathVariable("pageSize") Optional<Integer> pageSize, @PathVariable("page") Optional<Integer> page,
            @RequestParam(required = false, name = "mc") Optional<String> mc) {
        HashMap<String, Object> model = new HashMap<>();

        int evalPageSize = pageSize.orElse(Pager.INITIAL_PAGE_SIZE);
        int evalPage = 0;
        if (page.isPresent()) {
            evalPage = (page.orElse(0) < 1) ? Pager.INITIAL_PAGE : page.get() - 1;
        }
        Page<ProfilDTO> listProfil = this.profilService.findBySpecTerm(mc,   PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(listProfil.getTotalPages(), listProfil.getNumber(), Pager.BUTTONS_TO_SHOW);
        model.put("pageSize", Pager.getPageSize());
        model.put("pager", pager);
        model.put("sequence", Pager.customNumberSequence(pager.getStartPage(), pager.getEndPage()));
        model.put("selectedPageSize", evalPageSize);
        model.put("listProfil", listProfil);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
       // System.out.println("======================== listProfil " + listProfil.getContent().size());
        //System.out.println("======================== listProfil" + listProfil.getContent().toString());
        return ResponseEntity.accepted().headers(headers).body(model);

    }

    /**
     * Cette méthode supprime un profil
     *
     * @param id
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_PROFIL_SAISIR + "')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HashMap<String, Object>> deleteProfil(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        System.out.println("DELETE PROFIL CAS 1");
        if (id != null && this.profilService.existsById(id)) {
            this.profilService.delete(id);
            System.out.println("DELETE PROFIL CAS 2");
        }
        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
        return ResponseEntity.ok().headers(headers).build();
    }

    /**
     * Cette méthode enregistre un profil
     *
     * @param dto
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_PROFIL_SAISIR + "')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Object>> saveProfil(@RequestBody ProfilDTO dto) {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        ProfilDTO result = this.profilService.save(dto);
        modeHashMap.put("profilDTO", this.profilService.getProfil(result.getId()));
        System.out.println("SAVE RESULT  ================" + this.profilService.getProfil(result.getId()));
        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
    }

    /**
     * Cette méthode récupère la liste des états d'un objet
     *
     * @return
     */
    @RequestMapping(value = "/listEtat", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> listEtat() {
        HashMap<String, Object> model = new HashMap<>();
        model.put("listEtat", this.socleGenericService.findParamListByCodeParamCode(SocleConstant.CODIFICATION_ETAT));
//        model.put("listEtat", this.profilService.getListEtat());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        return ResponseEntity.accepted().headers(headers).body(model);
    }
    
    //@RequestMapping(value = "/changerEtat", method = RequestMethod.POST)
    @PostMapping(path = "/changerEtat")
    public ResponseEntity<HashMap<String, Object>> changerEtat(@RequestBody WorkflowCycleParameter p) {
        HashMap<String, Object> model = new HashMap<>();
        //HashMap<String, MessageParam> errors = new HashMap<>();
       ApiError apiError = new ApiError();
        //apiError
        //apiError.setErrorMessages(errorMessages);
        apiError.setStatut("200");
        apiError.setTypeError(TypeError.INFO);
        apiError.setPrincipal("Operation reussi");
        List<ErrorMessage> errorMessages = new ArrayList<>();
        ErrorMessage errorMsg = new ErrorMessage("200", "Operatioon reussi");
        errorMessages.add(errorMsg);
        apiError.setErrorMessages(errorMessages);
        this.profilService.forwardWorkflowProfil(p.getIdAction(), p.getIdObjet(), "");
        //this.messageSource.
       // apiError = this.errorMessage.getApiError("200", TypeError.INFO, properties.getInfoPrase(), errors);
       model.put(RestConstants.MSG_KEY, apiError);
        //model.put("listEtat", this.socleGenericService.findParamListByCodeParamCode(SocleConstant.CODIFICATION_ETAT));
//        model.put("listEtat", this.profilService.getListEtat());
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        
        return ResponseEntity.accepted().body(model);
    }
   
        
    /**
     * Cette méthode récupère les informations d'un profil
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getProfil/{id}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getProfil(@PathVariable("id") String id) {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        if (id != null && this.profilService.findById(id).isPresent()) {
            modeHashMap.put("profilDTO", this.profilService.getProfil(id));
        }
        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
    }

//    HABILITATION
    /**
     * Cettte méthode récupère la liste des niveaux d'habilitation
     *
     * @return
     */
    @RequestMapping(value = "/listNiveauHabilitation", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> listNiveauHabilitation() {
        HashMap<String, Object> model = new HashMap<>();
        model.put("listNiveau", this.socleGenericService.findParamListByCodeParamCode(SocleConstant.CODIFICATION_NIVEAU_HABILITATION));
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        return ResponseEntity.accepted().headers(headers).body(model);
    }

    /**
     * Cette méthode récupère la liste des fonctions
     *
     * @return
     */
    @RequestMapping(value = "/listFonction", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> listFonction() {
        HashMap<String, Object> model = new HashMap<>();
        model.put("listFonction", this.socleGenericService.findFonctionByType(TypeFonction.FONCTION));
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        return ResponseEntity.accepted().headers(headers).body(model);
    }

    /**
     * Cette méthode supprime une habilitation
     *
     * @param id
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_PROFIL_SAISIR + "')")
    @RequestMapping(value = "/deleteHabilitation/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HashMap<String, Object>> deleteHabilitation(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        System.out.println("DELETE deleteHabilitation CAS 1");
        if (id != null && this.profilService.existsHabilitationById(id)) {
            this.profilService.deleteHabilitation(id);
            System.out.println("DELETE deleteHabilitation CAS 2");
        }
        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
        return ResponseEntity.ok().headers(headers).build();
    }

    /**
     * Cette méthode enregistre une habilitation
     *
     * @param dto
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_PROFIL_SAISIR + "')")
    @RequestMapping(value = "/saveHabilitation", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Object>> saveHabilitation(@RequestBody HabilitationDTO dto) {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        HabilitationDTO result = this.profilService.saveHabilitation(dto);
        modeHashMap.put("habilitationDTO", this.profilService.getHabilitation(result.getId()));
        System.out.println("SAVE RESULT  ================" + this.profilService.getHabilitation(result.getId()));
        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
    }

    /**
     * Cette méthode récupère la list des habilitations
     *
     * @param pageSize
     * @param page
     * @param idProfil
     * @return
     */
    @RequestMapping(value = "/listHabilitation/{pageSize}/{page}/{idProfil}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> listHabilitation(@PathVariable("pageSize") Optional<Integer> pageSize,
            @PathVariable("page") Optional<Integer> page, @PathVariable("idProfil") String idProfil) {
        HashMap<String, Object> model = new HashMap<>();
        int evalPageSize = pageSize.orElse(Pager.INITIAL_PAGE_SIZE);
        int evalPage = 0;
        if (page.isPresent()) {
            evalPage = (page.orElse(0) < 1) ? Pager.INITIAL_PAGE : page.get() - 1;
        }
        Page<HabilitationDTO> listHabilitation = this.profilService.findHabilitationPaginate(PageRequest.of(evalPage, evalPageSize), idProfil);
        Pager pager = new Pager(listHabilitation.getTotalPages(), listHabilitation.getNumber(), Pager.BUTTONS_TO_SHOW);
        model.put("pageSize", Pager.getPageSize());
        model.put("pager", pager);
        model.put("sequence", Pager.customNumberSequence(pager.getStartPage(), pager.getEndPage()));
        model.put("selectedPageSize", evalPageSize);
        model.put("listHabilitation", listHabilitation);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        System.out.println("======================== listProfil " + listHabilitation.getContent().size());
        System.out.println("======================== listProfil" + listHabilitation.getContent().toString());
        return ResponseEntity.accepted().headers(headers).body(model);
    }

    /**
     * Cette méthode récupère les informations d'une habilitation
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getHabilitation/{id}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getHabilitation(@PathVariable("id") String id) {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        if (id != null && this.profilService.findHabilitationById(id).isPresent()) {
            modeHashMap.put("habilitationDTO", this.profilService.getHabilitation(id));
        }
        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
    }

//        @RequestMapping(value = "/getProfilIdentifiant/{id}", method = RequestMethod.GET)
//    public ResponseEntity<HashMap<String, Object>> getProfilIdentifiant(@PathVariable("id") String id) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        if (id != null && this.profilService.findById(id).isPresent()) {
//            modeHashMap.put("profilDTO", this.profilService.getProfil(id));
//        }
////        System.out.println("SAVE RESULT  ================" + this.profilService.getProfil(result.getId()));
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
    /**
     * Cette méthode récupère la liste des utlisateurs habilités sur le profil
     * @param pageSize
     * @param page
     * @param idProfil
     * @return 
     */
        @RequestMapping(value = "/listUsers/{pageSize}/{page}/{idProfil}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> listUsers(@PathVariable("pageSize") Optional<Integer> pageSize,
            @PathVariable("page") Optional<Integer> page, @PathVariable("idProfil") String idProfil) {
        HashMap<String, Object> model = new HashMap<>();
        int evalPageSize = pageSize.orElse(Pager.INITIAL_PAGE_SIZE);
        int evalPage = 0;
        if (page.isPresent()) {
            evalPage = (page.orElse(0) < 1) ? Pager.INITIAL_PAGE : page.get() - 1;
        }
        Page<UtilisateurDTO> listUsers = this.utilisateurService.findByProfilPaginate(PageRequest.of(evalPage, evalPageSize), idProfil);
        Pager pager = new Pager(listUsers.getTotalPages(), listUsers.getNumber(), Pager.BUTTONS_TO_SHOW);
        model.put("pageSize", Pager.getPageSize());
        model.put("pager", pager);
        model.put("sequence", Pager.customNumberSequence(pager.getStartPage(), pager.getEndPage()));
        model.put("selectedPageSize", evalPageSize);
        model.put("listUsers", listUsers);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        System.out.println("======================== listProfil " + listUsers.getContent().size());
        System.out.println("======================== listProfil" + listUsers.getContent().toString());
        return ResponseEntity.accepted().headers(headers).body(model);
    }
    
    
}
