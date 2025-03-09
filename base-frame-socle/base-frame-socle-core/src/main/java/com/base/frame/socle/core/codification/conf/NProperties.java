///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.codification.conf;
//
//
//import com.base.frame.socle.core.codification.utils.CodificationConstant;
//import com.base.frame.socle.core.entity.ParamList;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import javax.annotation.PostConstruct;
//import javax.persistence.Parameter;
//import org.reflections.Reflections;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * Classe de configuration du socle pour initialiser certain parametres valide
// * dans toute l'applications
// *
// * @author WKOUWONOU
// * @version 1.0.0
// * @since 02/02/2018
// */
//@Component
//@Scope(value = "singleton")
//@ConfigurationProperties(prefix = "system", ignoreUnknownFields = true)
//public class NProperties {
//
//    public static final String ERROR_PARAM_NO_VALID_VALUE = "ERROR_PARAM_NO_VALID_VALUE";
//    private Parameter langue;
//    private Parameter pays;
//    private ParamList codeLangue;
//    private String errorPhrase;
//    private String deletePhrase;
//    private String datePattern = "dd/MM/yyyy";
//    private String dateHeurePattern = "dd/MM/yyyy  HH:mm";
//
//    /**
//     * Parametre d'activation de production
//     */
//    private String encryptionSecretKey;
//    /**
//     * Parametre d'activation de production
//     */
//    private boolean licenceParamActivated = false;
//
//    private List<CodifList> statuts = new ArrayList<>();
//    private List<CodifList> actions = new ArrayList<>();
//    private List<String> niveauHabilitations = new ArrayList<>();
//    private String infoPrase;
//    @Autowired
//    private CodifListRepository codifListRepository;
//    @Autowired
//    private ParameterRepository parameterRepository;
//    @Autowired
//    private CodificationService codificationService;
//    @Autowired
//    MessageApplicatifRepository messageApplicatifRepository;
//
//    private Map<String, Class> workflowImples = new HashMap<>();
//    private Map<String, Class> conceptClasses = new HashMap<>();
//    private Map<String, List<String>> fonctions = new HashMap<>();
//
//    @Autowired
//    private CallBackRepository callBackRepository;
//    @Autowired(required = false)
//    private GetLicenceService getLicenceService;
//    @Autowired
//    private LicenceService licenceService;
//
//    @PostConstruct
//    public void init() {
//
//        langue = parameterRepository.findSystemParamByCode(CodificationConstant.PARAM_LINGUSTIQUE);
//        pays = parameterRepository.findSystemParamByCode(CodificationConstant.PAYS);
//        if (langue != null) {
//            errorPhrase = messageApplicatifRepository
//                    .findValueByCodeAndCodeLangue(CodificationConstant.F_ERROR, langue.getId());
//            infoPrase = messageApplicatifRepository
//                    .findValueByCodeAndCodeLangue(CodificationConstant.F_INFO, langue.getId());
//            deletePhrase = messageApplicatifRepository
//                    .findValueByCodeAndCodeLangue(CodificationConstant.F_ERROR_DELETE, langue.getId());
//            codeLangue = codifListRepository.findByCodificationAndCode(CodificationConstant.LANGUE, langue.getValeur());
//
//            statuts = codifListRepository.findCodifListWithLangue(CodificationConstant.STATUT_PARAM, langue.getValeur());
//            actions = codifListRepository.findCodifListWithLangue(CodificationConstant.ACTION_PARAM, langue.getValeur());
//            niveauHabilitations = codifListRepository.findByCodificationOrder(CodificationConstant.ACTIONS_ECRAN);
//        } else {
//            langue = new Parameter();
//            langue.setId("0");
//            langue.setValeur(CodificationConstant.LANGUE_FR);
//        }
//
//        Reflections ref = new Reflections("com.ngs");
//        workflowImples.clear();
//        ref.getTypesAnnotatedWith(WorkflowImpl.class).stream().forEach(cl -> {
//            WorkflowImpl w = cl.getAnnotation(WorkflowImpl.class);
//            if (w != null && w.conceptMetier() != null && w.conceptMetier().length > 0) {
//
//                for (String s : w.conceptMetier()) {
//                    workflowImples.put(s, cl);
//                }
//            }
//
//        });
//        ref.getTypesAnnotatedWith(WorkflowCallback.class).stream().forEach(cl -> {
//            WorkflowCallback w = cl.getAnnotation(WorkflowCallback.class);
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
//
//                WCallBack wc = new WCallBack(w.code(), w.libelle(), code);
//                callBackRepository.save(wc);
//            }
//
//        });
//
//        ref.getTypesAnnotatedWith(ContextFonction.class)
//                .stream().forEach(cl -> {
//                    ContextFonction c = cl.getAnnotation(ContextFonction.class);
//
//                    if (c != null && c.code() != null && !c.code().isEmpty()) {
//                        RequestMapping r = cl.getAnnotation(RequestMapping.class);
//
//                        if (r != null && r.path() != null && r.path().length > 0) {
//
//                            List<String> paths = fonctions.get(c.code());
//
//                            if (paths == null) {
//
//                                paths = Arrays.asList(r.path());
//                                fonctions.put(c.code(), paths);
//
//                            } else {
//                                List<String> p = fonctions.get(c.code());
//                                List<String> l = Arrays.asList(r.path());
//                                List<String> k = new ArrayList<>();
//
//                                k.addAll(p);
//                                k.addAll(l);
//
//                                fonctions.put(c.code(), k);
//                            }
//                        }
//                    }
//
//                });
//    }
//
//    public Map<String, Class> getWorkflowImples() {
//        return workflowImples;
//    }
//
//    public void setWorkflowImples(Map<String, Class> workflowImples) {
//        this.workflowImples = workflowImples;
//    }
//
//    public Parameter getLangue() {
//        return langue;
//    }
//
//    public void setLangue(Parameter langue) {
//        this.langue = langue;
//    }
//
//    public String getErrorPhrase() {
//        return errorPhrase;
//    }
//
//    public void setErrorPhrase(String errorPhrase) {
//        this.errorPhrase = errorPhrase;
//    }
//
//    public String getInfoPrase() {
//        return infoPrase;
//    }
//
//    public void setInfoPrase(String infoPrase) {
//        this.infoPrase = infoPrase;
//    }
//
//    public CodifList getCodeLangue() {
//        return codeLangue;
//    }
//
//    public void setCodeLangue(CodifList codeLangue) {
//        this.codeLangue = codeLangue;
//    }
//
//    public String getDeletePhrase() {
//        return deletePhrase;
//    }
//
//    public void setDeletePhrase(String deletePhrase) {
//        this.deletePhrase = deletePhrase;
//    }
//
//    public Parameter getPays() {
//        return pays;
//    }
//
//    public void setPays(Parameter pays) {
//        this.pays = pays;
//    }
//
//    public List<CodifList> getStatuts() {
//        return statuts;
//    }
//
//    public void setStatuts(List<CodifList> statuts) {
//        this.statuts = statuts;
//    }
//
//    public List<CodifList> getActions() {
//        return actions;
//    }
//
//    public void setActions(List<CodifList> actions) {
//        this.actions = actions;
//    }
//
//    public CodifList findStatutByCode(String code) {
//        for (CodifList c : statuts) {
//            if (c.getIdeCode().equals(code)) {
//                return c;
//            }
//        }
//        return null;
//    }
//
//    public CodifList findActionByCode(String code) {
//        for (CodifList c : actions) {
//            if (c.getIdeCode().equals(code)) {
//                return c;
//            }
//        }
//        return null;
//    }
//
//    public String getParameterValidValue(String codification) {
//        Optional<Parameter> parameter = this.parameterRepository.findValidParamByCodification(
//                codification, new Date());
//        if (!parameter.isPresent()) {
//            throw new InternalServerException(ERROR_PARAM_NO_VALID_VALUE
//                    + "#" + codification, null);
//        }
//        return parameter.get().getValeur();
//    }
//
//    public List<String> getNiveauHabilitations() {
//        return niveauHabilitations;
//    }
//
//    public void setNiveauHabilitations(List<String> niveauHabilitations) {
//        this.niveauHabilitations = niveauHabilitations;
//    }
//
//    public List<Integer> findChildren(Integer i) {
//
//        List<Integer> l = new ArrayList<>();
//        if (getLicenceService != null) {
//            if (!licenceService.cleIsActivated()) {
//                l.add(0);
//                return l;
//            }
//        }
//        for (String a : niveauHabilitations) {
//            if (Integer.valueOf(a) <= i) {
//                l.add(Integer.valueOf(a));
//            } else {
//                return l;
//            }
//        }
//        return l;
//    }
//
//    /**
//     * Collecte toute les fonctions annotées avec @ContextFonction et recupere
//     * les URL
//     *
//     * @return
//     */
//    public Map<String, List<String>> getFonctions() {
//        return fonctions;
//    }
//
//    public void setFonctions(Map<String, List<String>> fonctions) {
//        this.fonctions = fonctions;
//    }
//
//    public String getDatePattern() {
//        return datePattern;
//    }
//
//    public void setDatePattern(String datePattern) {
//        this.datePattern = datePattern;
//    }
//
//    public String getDateHeurePattern() {
//        return dateHeurePattern;
//    }
//
//    public void setDateHeurePattern(String dateHeurePattern) {
//        this.dateHeurePattern = dateHeurePattern;
//    }
//
//    public String getDateToString(Instant instant) {
//        return instant != null ? DateUtils.getDateInPattern(this.datePattern, instant) : null;
//    }
//
//    public Instant getStringToDate(String str) {
//        return str != null ? DateUtils.getDateFromString(this.datePattern, str) : null;
//    }
//
//    public Instant getStringToDateHeure(String str) {
//        return str != null ? DateUtils.getDateFromString(this.dateHeurePattern, str) : null;
//    }
//
//    public String getDateHeureToString(Instant instant) {
//        return instant != null ? DateUtils.getDateInPattern(this.dateHeurePattern, instant) : null;
//    }
//
//    public Map<String, Class> getConceptClasses() {
//        return conceptClasses;
//    }
//
//    public void setConcept(String conceptMetier, Class c) {
//        this.getConceptClasses().put(conceptMetier, c);
//    }
//
//    public void setConceptClasses(Map<String, Class> conceptClasses) {
//        this.conceptClasses = conceptClasses;
//    }
//
//    /**
//     * Parametre d'activation de production
//     *
//     * @return
//     */
//    public boolean isLicenceParamActivated() {
//        return licenceParamActivated;
//    }
//
//    /**
//     * Parametre d'activation de production
//     *
//     * @param licenceParamActivated
//     */
//    public void setLicenceParamActivated(boolean licenceParamActivated) {
//        this.licenceParamActivated = licenceParamActivated;
//    }
//
//    public String getEncryptionSecretKey() {
//        return encryptionSecretKey;
//    }
//
//    public void setEncryptionSecretKey(String encryptionSecretKey) {
//        this.encryptionSecretKey = encryptionSecretKey;
//    }
//
//    public CodifListRepository getCodifListRepository() {
//        return codifListRepository;
//    }
//
//    public void setCodifListRepository(CodifListRepository codifListRepository) {
//        this.codifListRepository = codifListRepository;
//    }
//
//    public ParameterRepository getParameterRepository() {
//        return parameterRepository;
//    }
//
//    public void setParameterRepository(ParameterRepository parameterRepository) {
//        this.parameterRepository = parameterRepository;
//    }
//
//    public MessageApplicatifRepository getMessageApplicatifRepository() {
//        return messageApplicatifRepository;
//    }
//
//    public void setMessageApplicatifRepository(MessageApplicatifRepository messageApplicatifRepository) {
//        this.messageApplicatifRepository = messageApplicatifRepository;
//    }
//
//    public CallBackRepository getCallBackRepository() {
//        return callBackRepository;
//    }
//
//    public void setCallBackRepository(CallBackRepository callBackRepository) {
//        this.callBackRepository = callBackRepository;
//    }
//
//}
