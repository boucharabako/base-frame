///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frameAncien.account.ressource;
//
//import com.base.frame.account.dto.ReinitPasswordConfirmationDTO;
//import com.base.frame.account.dto.UserProfilDTO;
//import com.base.frame.account.dto.UtilisateurDTO;
//import com.base.frame.account.dto.UtilisateurPasswordDTO;
//import com.base.frame.account.iservice.IProfilService;
//import com.base.frame.account.iservice.IUtilisateurService;
//import com.base.frame.account.utils.AccountConstant;
//import com.base.frame.socle.core.ISecurityUtils;
//import com.base.frame.socle.core.iservice.ISocleGenericService;
//import com.base.frame.socle.core.utils.SocleConstant;
//import com.base.frame.socle.utils.Constants;
//import com.base.frame.socle.utils.Pager;
//import com.base.frame.socle.utils.validators.MessageSourceKV;
//import java.util.HashMap;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.List;
//
///**
// *
// * @author Alassani
// */
//@RestController
//@RequestMapping(path = "gestion/utilisateursss")
////@PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_USER + "')")
//public class UtilisateurRessource {
//
//    @Autowired
//    private IProfilService profilService;
//    @Autowired
//    private IUtilisateurService utilisateurService;
//    @Autowired
//    private MessageSourceKV messageSource;
//
//    @Autowired
//    private ISocleGenericService socleGenericService;
//
//    @Autowired
//    private ISecurityUtils securityUtils;
//
//    /**
//     * Cette méthode récupère la liste des utilisateurs
//     *
//     * @param pageSize
//     * @param page
//     * @param mc
//     * @return
//     */
//    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_USER_LIRE + "')")
//    @RequestMapping(value = "/listUtilisateur/{pageSize}/{page}", method = RequestMethod.GET)
//    public ResponseEntity<HashMap<String, Object>> listUtilisateur(
//            @PathVariable("pageSize") Optional<Integer> pageSize, @PathVariable("page") Optional<Integer> page,
//            @RequestParam(required = false, name = "mc") Optional<String> mc) {
//        HashMap<String, Object> model = new HashMap<>();
//
//        int evalPageSize = pageSize.orElse(Pager.INITIAL_PAGE_SIZE);
//        int evalPage = 0;
//
//        if (page.isPresent()) {
//            evalPage = (page.orElse(0) < 1) ? Pager.INITIAL_PAGE : page.get() - 1;
//        }
//        Page<UtilisateurDTO> listUtilisateur = this.utilisateurService.findBySpecTerm(mc, PageRequest.of(evalPage, evalPageSize));
//        Pager pager = new Pager(listUtilisateur.getTotalPages(), listUtilisateur.getNumber(), Pager.BUTTONS_TO_SHOW);
//        model.put("pageSize", Pager.getPageSize());
//        model.put("pager", pager);
//        model.put("sequence", Pager.customNumberSequence(pager.getStartPage(), pager.getEndPage()));
//        model.put("selectedPageSize", evalPageSize);
//        model.put("listUtilisateur", listUtilisateur);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        System.out.println("======================== listUtilisateur " + listUtilisateur.getContent().size());
//        System.out.println("======================== listUtilisateur" + listUtilisateur.getContent().toString());
//        return ResponseEntity.accepted().headers(headers).body(model);
//
//    }
//    
////    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_USER_LIRE + "')")
//    @RequestMapping(value = "/listAllUtilisateur", method = RequestMethod.GET)
//    public ResponseEntity<HashMap<String, Object>> listAllUtilisateur() {
//        HashMap<String, Object> model = new HashMap<>();
//
//        
//        List<UtilisateurDTO> listUtilisateur = this.utilisateurService.getAllUser();
//       
//        
//        model.put("listUtilisateur", listUtilisateur);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        System.out.println("======================== listUtilisateur " + listUtilisateur.size());
//        System.out.println("======================== listUtilisateur" + listUtilisateur.toString());
//        return ResponseEntity.accepted().headers(headers).body(model);
//
//    }
//    /**
//     * Cette méthode supprime un utilisateur
//     *
//     * @param id
//     * @return
//     */
//    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_USER_SAISIR + "')")
//    @RequestMapping(value = "/deleteUtilisateur/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<HashMap<String, Object>> deleteUtilisateur(@PathVariable("id") String id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        System.out.println("DELETE UTILISATEUR CAS 1");
//        if (id != null && this.utilisateurService.existsUtilisateurById(id)) {
//            this.utilisateurService.deleteUtilisateur(id);
//            System.out.println("DELETE UTILISATEUR CAS 2");
//        }
//        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
//        return ResponseEntity.ok().headers(headers).build();
//    }
//
//    /**
//     * Cette méthode enregistre un utilisateur
//     *
//     * @param dto
//     * @return
//     */
//    @RequestMapping(value = "/saveUtilisateur", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, Object>> saveUtilisateur(@RequestBody UtilisateurDTO dto) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        UtilisateurDTO result = this.utilisateurService.saveUtilisateur(dto);
//        modeHashMap.put("userDTO", this.utilisateurService.getUtilisateur(result.getId()));
//        System.out.println("SAVE RESULT  ================" + this.utilisateurService.getUtilisateur(result.getId()));
//        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//
//    /**
//     * Cette méthode récupère la liste des états d'un objet
//     *
//     * @return
//     */
//    @RequestMapping(value = "/listEtat", method = RequestMethod.GET)
//    public ResponseEntity<HashMap<String, Object>> listEtat() {
//        HashMap<String, Object> model = new HashMap<>();
//        model.put("listEtat", this.socleGenericService.findParamListByCodeParamCode(SocleConstant.CODIFICATION_ETAT));
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        return ResponseEntity.accepted().headers(headers).body(model);
//    }
//
//    /**
//     * Cette méthode récupère les informations d'un utilisateur
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/getUtilisateur/{id}", method = RequestMethod.GET)
//    public ResponseEntity<HashMap<String, Object>> getUtilisateur(@PathVariable("id") String id) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        if (id != null && this.utilisateurService.findUtilisateurById(id).isPresent()) {
//            modeHashMap.put("userDTO", this.utilisateurService.getUtilisateur(id));
//            modeHashMap.put("utilisateurProfilDto", this.utilisateurService.getUserProfil(id));
//        }
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//
//    @RequestMapping(value = "/getUtilisateurConnect", method = RequestMethod.GET)
//    public ResponseEntity<HashMap<String, Object>> getUtilisateur() {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
////        String id = "eff89322-49f0-496b-9c06-f9fcce7b051a";
//
////
//        Optional<String> username = securityUtils.getCurrentUserLogin();
//        if (username.isPresent() && this.utilisateurService.findUtilisateurUsername(username.get()).isPresent()) {
//            modeHashMap.put("userDTO",
//                    this.utilisateurService.getUtilisateur(this.utilisateurService.findUtilisateurUsername(username.get()).get().getId()));
//        }
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//
//    /**
//     * Cette Méthode enregistre les mots de passes d'utilisateur
//     *
//     * @param dto
//     * @return
//     */
//    @RequestMapping(value = "/saveUtilisateurPassword", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, Object>> saveUtilisateurPassword(@RequestBody UtilisateurPasswordDTO dto) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        UtilisateurPasswordDTO result = this.utilisateurService.saveUtilisateurPassword(dto);
//        modeHashMap.put("idUserPassword", result.getId());
//        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//
//    @RequestMapping(value = "/updateUtilisateurPassword", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, Object>> updateUtilisateurPassword(@RequestBody UtilisateurPasswordDTO dto) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        UtilisateurPasswordDTO result = this.utilisateurService.updateUtilisateurPassword(dto);
//        modeHashMap.put("idUserPassword", result.getId());
//        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//
//    @RequestMapping(value = "/reinitUtilisateurPassword", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, Object>> reinitUtilisateurPasswordGeneration(@RequestBody UtilisateurDTO dto) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        if (dto.getId() != null && this.utilisateurService.findUtilisateurById(dto.getId()).isPresent()) {
//            this.utilisateurService.reinitUtilisateurPassword(dto.getId());
//            headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
//        }
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//    
//       @RequestMapping(value = "/reinitPassword/forgetPassword", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, Object>> forgetPasswordGeneration(  @RequestParam(name = "param") String param) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//     this.utilisateurService.forgetUtilisateurPassword(param);
//            headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
//      
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//
//    @RequestMapping(value = "/reinitPassword/reinitUtilisateurPasswordConfirmation", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, Object>> reinitUtilisateurPasswordConfirmation(@RequestBody ReinitPasswordConfirmationDTO dto) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        System.out.println("reinitUtilisateurPasswordConfirmation add");
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        this.utilisateurService.reinitUtilisateurPasswordConfirmation(dto);
//        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//
//
//    @RequestMapping(value = "/listProfilActif", method = RequestMethod.GET)
//    public ResponseEntity<HashMap<String, Object>> listProfilActif(@RequestParam(required = false, name = "me") Optional<String> me) {
//        HashMap<String, Object> model = new HashMap<>();
//        if (me.isPresent()) {
//            model.put("listProfil", this.profilService.findProfilCanAddByStatut(SocleConstant.CODIFICATION_ETAT_ACTIF, me.get()));
//        } else {
//            model.put("listProfil", this.profilService.findProfilByStatut(SocleConstant.CODIFICATION_ETAT_ACTIF));
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        return ResponseEntity.accepted().headers(headers).body(model);
//    }
//
//    @RequestMapping(value = "/getUserProfil/{idUser}", method = RequestMethod.GET)
//    public ResponseEntity<HashMap<String, Object>> getUserProfil(@PathVariable("idUser") String idUser) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        if (idUser != null && this.utilisateurService.findUtilisateurById(idUser).isPresent()) {
//            modeHashMap.put("utilisateurProfilDto", this.utilisateurService.getUserProfil(idUser));
//            modeHashMap.put("listProfil", this.profilService.findProfilCanAddByStatut(SocleConstant.CODIFICATION_ETAT_ACTIF, idUser));
//        }
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//    
//    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_USER_SAISIR + "')")
//    @RequestMapping(value = "/saveUserProfil", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, Object>> saveUserProfil(@RequestBody UserProfilDTO dto) {
//        HashMap<String, Object> modeHashMap = new HashMap<>();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Constants.PROPRIETE_HEADERS_RESPONDED, Constants.PROPRIETE_HEADERS_TIMEZONESCONTROLLER);
//        UserProfilDTO result = this.utilisateurService.saveUserProfil(dto);
//        modeHashMap.put("utilisateurProfilDto", this.utilisateurService.getUserProfil(result.getIdUtilisateur()));
//        modeHashMap.put("listProfil", this.profilService.findProfilCanAddByStatut(SocleConstant.CODIFICATION_ETAT_ACTIF, result.getIdUtilisateur()));
////        System.out.println("SAVE RESULT  ================" + this.utilisateurService.getUtilisateur(result.getId()));
//        headers.add("X-base.frame-alert", this.messageSource.getMessage(Constants.OP_SUCCESS_MSG_CODE, new String[]{}));
//        return ResponseEntity.accepted().headers(headers).body(modeHashMap);
//    }
//
//}
