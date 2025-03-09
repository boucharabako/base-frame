/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.resources;

import com.base.frame.account.dto.UtilisateurDTO;
import com.base.frame.account.iservice.IUtilisateurService;
import com.base.frame.socle.core.ISecurityUtils;
import com.base.frame.socle.core.codification.utils.ApiError;
import com.base.frame.socle.core.codification.utils.ErrorMessage;
import com.base.frame.socle.core.codification.utils.RestConstants;
import com.base.frame.socle.core.codification.utils.TypeError;
import com.base.frame.socle.core.workflow.dto.WorkflowCycleParameter;
//import com.base.frame.socle.core.utils.MessageParam;
//import com.base.frame.socle.utils.exceptions.ApiError;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import com.base.frame.socle.utils.Constants;
import com.base.frame.socle.utils.validators.MessageSourceKV;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.Errors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.base.frame.socle.utils.Pager;
import java.util.ArrayList;

/**
 *
 * @author Alassani
 */
@RestController
@RequestMapping(path = "gestion/utilisateur")
public class UtilisateurResource {
    private static final String ERROR = "error";
    private static final String MSG = "msg";
    private static final String REQUEST_OBJECT = "requestObject";
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UtilisateurResource.class);

    public static final String OPERATION_COMPLETED = "operationCompleted";
    public static final String SELECTED_PAGE_SIZE = "selectedPageSize";
    public static final String SEQUENCE = "sequence";
    public static final String PAGER = "pager";
    private static final String VALUE_RESP = "Responded";
    private static final String VALUE_TIMEZONE = "TimeZonesController";
    @Autowired
    private ISecurityUtils securityUtils;
//    @Autowired
//    private ErrorMessageService errorMessage;

    @Autowired
    private IUtilisateurService utilisateurService;
    @Autowired
    private MessageSourceKV messageSource;

    @RequestMapping(value = "/saveOrUpdateUser", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Object>> saveOrUpdateUtilisateur(@RequestBody UtilisateurDTO utilisateurDto) throws CloneNotSupportedException {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        UtilisateurDTO result = this.utilisateurService.saveUtilisateur(utilisateurDto);
        modeHashMap.put("userDTO", this.utilisateurService.getUtilisateur(result.getId()));
        System.out.println("SAVE RESULT  ================" + this.utilisateurService.getUtilisateur(result.getId()));
        System.out.println("fffffffffffffffff:"+this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
        headers.add("X-nframe-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
        //return ResponseEntity.accepted().headers(headers).body(modeHashMap);
    }

    @RequestMapping(value = "/listAllUtilisateur", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> listAllUtilisateur() {
        HashMap<String, Object> model = new HashMap<>();

        List<UtilisateurDTO> listUtilisateur = this.utilisateurService.getAllUser();

        model.put("listUtilisateur", listUtilisateur);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        System.out.println("======================== listUtilisateur " + listUtilisateur.size());
        System.out.println("======================== listUtilisateur" + listUtilisateur.toString());
        return ResponseEntity.accepted().headers(headers).body(model);

    }

    @RequestMapping(value = "/list/{pageSize}/{page}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> listProfil(
            @PathVariable("pageSize") Optional<Integer> pageSize, @PathVariable("page") Optional<Integer> page,
            @RequestParam(required = false, name = "mc") Optional<String> mc,
            @RequestParam(required = false, name = "username") Optional<String> username,
            @RequestParam(required = false, name = "firstName") Optional<String> firstName,
            @RequestParam(required = false, name = "lastName") Optional<String> lastName,
            @RequestParam(required = false, name = "profil") Optional<String> profil,
            @RequestParam(required = false, name = "groupe") Optional<String> groupe,
            @RequestParam(required = false, name = "etat") Optional<String> etat) {
        HashMap<String, Object> model = new HashMap<>();
        
        String smc = "",  susername ="", sfirstName="",slastName="", sprofil ="", sgroupe="", setat ="" ;
        if(username.isPresent()){
            susername =username.get();
        }
        if(firstName.isPresent()){
            sfirstName =firstName.get();
        }
        if(mc.isPresent()){
            smc =mc.get();
        }
        if(lastName.isPresent()){
            slastName =lastName.get();
        }
        if(profil.isPresent()){
            sprofil =profil.get();
        }
        if(groupe.isPresent()){
            sgroupe =groupe.get();
        }
        if(etat.isPresent()){
            setat =etat.get();
        }
        
        int evalPageSize = pageSize.orElse(Pager.INITIAL_PAGE_SIZE);
        int evalPage = 0;
        if (page.isPresent()) {
            evalPage = (page.orElse(0) < 1) ? Pager.INITIAL_PAGE : page.get() - 1;
        }
        Page<UtilisateurDTO> listUtilisateur = this.utilisateurService.findBySpecTerm(smc,susername,sfirstName, slastName,sprofil,sgroupe,setat,PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(listUtilisateur.getTotalPages(), listUtilisateur.getNumber(), Pager.BUTTONS_TO_SHOW);
        model.put("pageSize", Pager.getPageSize());
        model.put("pager", pager);
        model.put("sequence", Pager.customNumberSequence(pager.getStartPage(), pager.getEndPage()));
        model.put("selectedPageSize", evalPageSize);
        model.put("listUtilisateur", listUtilisateur);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
       // System.out.println("======================== listProfil " + listProfil.getContent().size());
        //System.out.println("======================== listProfil" + listProfil.getContent().toString());
        return ResponseEntity.accepted().headers(headers).body(model);

    }
    
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
        this.utilisateurService.forwardWorkflowUtilisateur(p.getIdAction(), p.getIdObjet(), "");
        //this.messageSource.
       // apiError = this.errorMessage.getApiError("200", TypeError.INFO, properties.getInfoPrase(), errors);
       model.put(RestConstants.MSG_KEY, apiError);
        //model.put("listEtat", this.socleGenericService.findParamListByCodeParamCode(SocleConstant.CODIFICATION_ETAT));
//        model.put("listEtat", this.profilService.getListEtat());
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        
        return ResponseEntity.accepted().body(model);
    }
    @GetMapping(value = "/listeProfil")
    public ResponseEntity<HashMap<String, Object>> listeProfil() {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        modeHashMap.put("listeProfils", this.utilisateurService.loadingProfil());
        // System.out.println(" liste des profils actifs :"+modeHashMap.toString());
        return ResponseEntity.accepted().body(modeHashMap);
    }

    @GetMapping(value = "/listeGroupe")
    public ResponseEntity<HashMap<String, Object>> listeGroupe() {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        //modeHashMap.put("listeGroupes", this.utilisateurService.listGroupes());

        return ResponseEntity.accepted().body(modeHashMap);
    }

    @RequestMapping(value = "/deleteUtilisateur/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HashMap<String, Object>> deleteUtilisateur(@PathVariable("id") String id) {
        try {
            HashMap<String, Object> modeHashMap = new HashMap<>();
            //HashMap<String, MessageParam> errors = new HashMap<>();
            //ApiError apiError;
            this.utilisateurService.deleteUtilisateur(id);
            //apiError = this.errorMessage.getApiError("200", TypeError.INFO, properties.getInfoPrase(), errors);
            //modeHashMap.put(RestConstants.MSG_KEY, apiError);
            return ResponseEntity.accepted().body(modeHashMap);
        } catch (Exception e) {
            throw new Error("Impossible");
            //throw new InternalServerException("Impossible", null);
        }
    }
    
    /**
     * Cette méthode récupère les informations d'un Utilisateur
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUtilisateur/{id}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getUtilisateur(@PathVariable("id") String id) {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        if (id != null && this.utilisateurService.getUtilisateur(id) != null) {
            modeHashMap.put("utilisateurDTO",this.utilisateurService.getUtilisateur(id));
        }
        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
    }
    
    @RequestMapping(value = "/getUserProfil/{idUser}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getUserProfil(@PathVariable("idUser") String idUser) {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
        if (idUser != null && this.utilisateurService.findUtilisateurById(idUser).isPresent()) {
            modeHashMap.put("utilisateurProfilDto", this.utilisateurService.getUserProfil(idUser));
            
        }
        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
    }
    
    @RequestMapping(value = "/getUtilisateurConnect", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getUtilisateur() {
        HashMap<String, Object> modeHashMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        String id = "eff89322-49f0-496b-9c06-f9fcce7b051a";

//
        Optional<String> username = securityUtils.getCurrentUserLogin();
        if (username.isPresent() && this.utilisateurService.findUtilisateurUsername(username.get()).isPresent()) {
            modeHashMap.put("userDTO",
                    this.utilisateurService.getUtilisateur(this.utilisateurService.findUtilisateurUsername(username.get()).get().getId()));
        }
        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
    }
    
}
