///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.codification.conf;
//
//
//
//import com.base.frame.socle.core.codification.utils.CodificationConstant;
//import com.base.frame.socle.core.entity.ParamList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import org.reflections.Reflections;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author kouwonou
// */
//@Component
//@Scope(value = "singleton")
//public class ExtensionInterfaceConfig {
//
//    @Autowired
//    private ExtensionInterfaceRepository extensionInterfaceRepository;
//    @Autowired private CodifListRepository codifListRepository;
//    Reflections ref = new Reflections("com.ngs");
//    private static final Logger LOG = Logger.getLogger(ExtensionInterfaceConfig.class.getName());
//
//    @Autowired NProperties  nProperties;
//    @PostConstruct
//    public void init() {
//        
//        
//        ref.getTypesAnnotatedWith(ExtensionInterfaceImpl.class).stream().forEach((Class<?> cl) -> {
//            ExtensionInterfaceImpl w = cl.getAnnotation(ExtensionInterfaceImpl.class);
//            if (w != null && w.code() != null && !w.code().isEmpty()) {
//                Service s = cl.getAnnotation(Service.class);
//                Component c = cl.getAnnotation(Component.class);
//                Repository r = cl.getAnnotation(Repository.class);
//                String code = null;
//                if (s != null) {
//                    code = s.value();
//                    if (code == null || code.isEmpty()) {
//
//                        code = cl.getSimpleName().substring(0, 1).toLowerCase().concat(cl.getSimpleName().substring(1));
//                    }
//                }
//                if (c != null) {
//                    code = c.value();
//                    if (code == null || code.isEmpty()) {
//
//                        code = cl.getSimpleName().substring(0, 1).toLowerCase().concat(cl.getSimpleName().substring(1));
//                    }
//                }
//                if (r != null) {
//                    code = r.value();
//                    if (code == null || code.isEmpty()) {
//
//                        code = cl.getSimpleName().substring(0, 1).toLowerCase().concat(cl.getSimpleName().substring(1));
//                    }
//                }
//                ParamList conceptMetier=codifListRepository.findByCodificationAndCode(CodificationConstant.CONCEPT_METIER, w.comceptMetier());
//                
//                if(conceptMetier!=null){
//                       ExtensionInterface wc = new ExtensionInterface(conceptMetier,w.code(), w.libelle(), code,w.required(),w.hiden(),w.isFilter(),w.composant());
//                extensionInterfaceRepository.save(wc);
//                }else{
//                   LOG.log(Level.SEVERE, "Le conceptier metier de {0} n''existe pas. Ce n''est pas enregistr\u00e9 dans la base", w.libelle());
//                }
//             
//            }
//
//        });
//    }
//}
