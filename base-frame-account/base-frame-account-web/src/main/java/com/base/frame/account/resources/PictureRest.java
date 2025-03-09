/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.resources;


;
import com.base.frame.account.entity.Utilisateur;
import com.base.frame.account.iservice.IUtilisateurService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Cette classe permet de gérer les informations concernant les photos 
 *
 * @author Adnaane
 * @version 1.0.0
 * @since 27/02/2018
 *
 */
@RestController
@RequestMapping("api/picture")
public class PictureRest {

    private static final String VALUE = "value";

//    @Autowired
    //NProperties properties;
    @Autowired
    private IUtilisateurService utilisateurService;

    
    //private MessageApplicatifRepository messageRepository;

    /**
     * Cette fonction permet d'uploader un fichier sur le net
     *
     * @param uploadfile
     * @return
     */
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Map<String, Object> uploadFile(
//            @RequestParam("uploadfile") MultipartFile uploadfile) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            InputStream in = new ByteArrayInputStream(uploadfile.getBytes());
//            map.put(VALUE, IOUtils.toByteArray(in));
//            return map;
//        } catch (Exception e) {
//            map.put(VALUE, "");
//            return map;
//        }
//    }

    /**
     * Cette méthode permet de persister ou de voir un photo de profil
     *
     * @param uploadfile le fichier uploadé
     * @param persist qui permet de persister ou non une photo de profil
     * @param idUtilisateur l'id de l'utilisateur
     * @return
     */
//    @RequestMapping(value = "/changeUserPicture/{id}/{persist}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Map<String, Object> changeUserPicture(
//            @RequestParam("uploadfile") MultipartFile uploadfile,
//            @PathVariable("persist") boolean persist,
//            @PathVariable("id") String idUtilisateur
//    ) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            InputStream in;
//            if (persist) {
//                in = new ByteArrayInputStream(this.utilisateurService.changeUserPicture(idUtilisateur, uploadfile));
//            } else {
//                in = new ByteArrayInputStream(uploadfile.getBytes());
//            }
//            map.put(VALUE, IOUtils.toByteArray(in));
//            map.put("success", true);
//            map.put("msg", this.messageRepository.findLibelleByCodeAndCodeLangue(ErrorConstant.OPERATION_SUCCESS_LABEL, properties.getLangue().getId()));
//            return map;
//        } catch (Exception e) {
//            map.put(VALUE, "");
//            map.put("success", false);
//            map.put("msg", this.messageRepository.findLibelleByCodeAndCodeLangue(ErrorConstant.OPERATION_ERROR_LABEL, properties.getLangue().getId()));
//            return map;
//        }
//    }

    /**
     * Cette méthode renvoie un fichier en convertie en base64
     *
     * @param id l'id de l'utilisateur
     * @return
     */
    @RequestMapping(value = "/viewFile/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object viewFileInBase64(
            @PathVariable("id") String id
    ) {
        try {
            Optional<Utilisateur> u = utilisateurService.findUtilisateurById(id);
            if(u.isPresent()){
                InputStream in = new ByteArrayInputStream(u.get().getPicture().getValeur());
                return IOUtils.toByteArray(in);
            }
            
            InputStream in = new ByteArrayInputStream(u.get().getPicture().getValeur());
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            return null;//utilisateurService.getUserDeafultPicture();
        }
    }
}
