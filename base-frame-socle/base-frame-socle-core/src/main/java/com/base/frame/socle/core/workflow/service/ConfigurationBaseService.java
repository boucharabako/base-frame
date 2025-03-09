///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.InternalServerException;
//import com.ngs.core.codification.dto.ClasseurDTO;
//import com.ngs.core.codification.dto.CodifEtiquetteSimpleObjectDTO;
//import com.ngs.core.codification.dto.CodifItemSimpleObjectDTO;
//import com.ngs.core.codification.dto.CodifListeSimpleDTO;
//import com.ngs.core.codification.dto.CodifParamSimpleDTO;
//import com.ngs.core.codification.dto.CodificationSimpleDTO;
//import com.ngs.core.codification.dto.StatutDto;
//import com.ngs.core.codification.dto.StatutResponce;
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Codification;
//import com.ngs.core.codification.entities.Etiquette;
//import com.ngs.core.codification.entities.Libelle;
//import com.ngs.core.codification.entities.MessageApplicatif;
//import com.ngs.core.codification.entities.Parameter;
//import com.ngs.core.codification.functionsInterface.TwoArgFunction;
//import static com.ngs.core.codification.services.WorkFlowConstants.OPERATION_NON_AUTORISEE;
//import com.ngs.core.codification.utils.ApiError;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.MessageParam;
//import com.ngs.core.codification.utils.RestConstants;
//import com.ngs.core.codification.utils.TypeError;
//import java.time.Instant;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Consumer;
//import java.util.function.Function;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author kouwonou
// */
//@Service
//@Transactional
//public class ConfigurationBaseService extends WorkFlowConstants {
//
//
//    private static final String PERSIST_TYPE_UPDATE = "up";
//    private static final String PERSIST_TYPE_INSERT = "in";
//    private static final String LIBELLE_REQUIS = "Le libelle est requis";
//    private static final String CODIFICATION = "Cette codification n'existe pas";
//    private static final String AJOUT_ITEM = "Veillez ajouter au moins un item à la liste";
//    private static final String ITEM_EXISTE_PAS = "Cette  item n'existe pas";
//    private static final String DATE_FIN_VALIDITE = "Date de fin de validté invalide";
//    @Autowired
//    private ClasseurService classeurService;
//
//    public Object changerStatut(Codification wk, StatutDto w, Consumer<Codification> v) {
//
//        StatutResponce s = processStatut(wk == null ? null : wk.getStatut().getIdeCode(),
//                w.getIdeCodeStatutSuivant()
//        );
//
//        if (s.isError() || wk == null) {
//            return s.getModel();
//        }
//
//        v.accept(wk);
//        wk.setStatut(properties.findStatutByCode(w.getIdeCodeStatutSuivant()));
//
//        codificationRepository.saveAndFlush(wk);
//        trackingParamObjetService.saveStatusTracking(w.getId(), CodificationConstant.CONCEPT_METIER_CODIFICATION, wk.getStatut());
//
//        StatutDto st = new StatutDto();
//        st.setIdeCode(wk.getStatut().getIdeCode());
//        st.setIdeCodeStatutSuivant(st.getNextStatutIdeCode());
//
//        st.setLibelle(properties.findStatutByCode(st.getIdeCode()) != null
//                ? properties.findStatutByCode(st.getIdeCode()).getLibelle() : "");
//        st.setLibelleStatutSuivant(properties.findActionByCode(st.getIdeCode()) != null
//                ? properties.findActionByCode(st.getIdeCode()).getLibelle() : "");
//
//        s.getModel().put(CodificationConstant.DATA_KEY, st);
//        return s.getModel();
//    }
//
//    public Object changerStatut(StatutDto w) {
//        Codification wk = codificationRepository.findOne(w.getId());
//        if (wk == null) {
//            throw new InternalServerException(CODIFICATION, null);
//
//        }
//        switch (wk.getTypeCodif().getIdeCode()) {
//            case CodificationConstant.TYPE_CODIF_LISTE:
//                return this.changerStatut(wk, w, x -> {
//
//                    if (codifListRepository.findByCodificationOrder(x.getCode()).isEmpty()) {
//                        throw new InternalServerException(AJOUT_ITEM, null);
//                    }
//
//                });
//            case CodificationConstant.TYPE_CODIF_PARAMETRE:
//                return this.changerStatut(wk, w, x -> {
//
//                    if (parameterRepository.findByCodification(x.getCode()).isEmpty()) {
//                        throw new InternalServerException(AJOUT_ITEM, null);
//                    }
//
//                    List<Parameter> l = parameterRepository.findByCodification(x.getCode());
//
//                    if (wk.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//                        if (l.stream().filter(z -> z.getDateFinValidite() == null).count() > 2) {
//                            throw new InternalServerException("Date fin de validité invalide pour certains parametres", null);
//
//                        }
//
//                    }
//
//                });
//            case CodificationConstant.TYPE_CODIF_ETIQUETTE:
//                return this.changerStatut(wk, w, x -> {
//
//                    if (etiquetteRepository.findByCodification(x.getCode()).isEmpty()) {
//                        throw new InternalServerException(AJOUT_ITEM, null);
//                    }
//
//                });
//            case CodificationConstant.TYPE_CODIF_CLASSEUR:
//                return this.changerStatut(wk, w, x -> {
//
//                    if (classeurRepository.findClasseurIdByCodif(x.getCode()).isEmpty()) {
//                        throw new InternalServerException(AJOUT_ITEM, null);
//                    }
//
//                });
//            default:
//                throw new InternalServerException("Type de codification nom prise en charge", null);
//
//        }
//
//    }
//
//    public Object deleteCodification(Codification wk, Consumer<Codification> v) {
//        HashMap<String, MessageParam> errors = new HashMap<>();
//        HashMap<String, Object> model = new HashMap<>();
//
//        if (wk.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//
//            v.accept(wk);
//
//            codificationRepository.delete(wk.getCode());
//            ApiError apiError = this.errorMessage.getApiError("400", TypeError.INFO,
//                    properties.getInfoPrase(), errors);
//            model.put(RestConstants.MSG_KEY, apiError);
//            return model;
//        }
//
//        MessageApplicatif p = messageRepository.findByCodeAndCodeLangue(OPERATION_NON_AUTORISEE, properties.getLangue().getId());
//
//        ApiError apiError = this.errorMessage.getApiError("400", TypeError.DANGER,
//                p == null ? "" : p.getLibelle(), errors);
//        model.put(RestConstants.MSG_KEY, apiError);
//
//        return model;
//    }
//
//    public Object deleteCodification(CodificationSimpleDTO w) {
//        Codification wk = codificationRepository.findOne(w.getCode());
//        if (wk == null) {
//            throw new InternalServerException(CODIFICATION, null);
//
//        }
//        switch (wk.getTypeCodif().getIdeCode()) {
//            case CodificationConstant.TYPE_CODIF_LISTE:
//                return this.deleteCodification(wk, x -> {
//
//                    try {
//                        codifListRepository.findLibelleByCodification(x.getCode()).stream().map(z -> z.getId()).forEach(z -> {
//                            libelleRepository.delete(z);
//                        });
//                        codifListRepository.findByCodification(x.getCode()).stream().map(z -> z.getId()).forEach(z -> {
//                            codifListRepository.delete(z);
//                        });
//                    } catch (Exception e) {
//                        throw new InternalServerException(properties.getDeletePhrase(), null);
//                    }
//
//                });
//            case CodificationConstant.TYPE_CODIF_PARAMETRE:
//                return this.deleteCodification(wk, x -> {
//
//                    try {
//                        parameterRepository.findByCodification(x.getCode()).stream().map(z -> z.getId()).forEach(z -> {
//
//                            parameterRepository.delete(z);
//                        });
//
//                    } catch (Exception e) {
//                        throw new InternalServerException(properties.getDeletePhrase(), null);
//                    }
//
//                });
//            case CodificationConstant.TYPE_CODIF_ETIQUETTE:
//                return this.deleteCodification(wk, x -> {
//                    try {
//                        etiquetteRepository.findByCodification(x.getCode()).stream().map(z -> z.getId()).forEach(z -> {
//                            etiquetteRepository.delete(z);
//                        });
//
//                    } catch (Exception e) {
//                        throw new InternalServerException(properties.getDeletePhrase(), null);
//                    }
//
//                });
//            case CodificationConstant.TYPE_CODIF_CLASSEUR:
//                return this.deleteCodification(wk, x -> {
//                    try {
//                        classeurService.deleteClasseurItems(x.getCode());
//
//                    } catch (Exception e) {
//                        throw new InternalServerException(properties.getDeletePhrase(), null);
//                    }
//
//                });
//            default:
//                throw new InternalServerException("Type de codification nom prise en charge", null);
//
//        }
//
//    }
//
//    public Object saveCodification(CodificationSimpleDTO dto, String type) {
//
//        HashMap<String, MessageParam> errors = new HashMap<>();
//        HashMap<String, Object> model = new HashMap<>();
//        if (dto.getCode() == null || dto.getCode().isEmpty()) {
//            throw new InternalServerException("Le code est requis", null);
//        }
//        if (!dto.getCode().matches(CodificationConstant.ALPHA_NUMERIQUE_REGEX)) {
//            throw new InternalServerException("Le code n'est pas valide", null);
//        }
//
//        if (dto.getLibelle() == null || dto.getLibelle().isEmpty()) {
//            throw new InternalServerException(LIBELLE_REQUIS, null);
//        }
//
//        if (dto.getPorte() == null || dto.getPorte().getIdeCode() == null || dto.getPorte().getIdeCode().isEmpty()) {
//            throw new InternalServerException("La portée est réquise", null);
//        }
//
//        if (dto.getType() == null || dto.getType().getIdeCode() == null || dto.getType().getIdeCode().isEmpty()) {
//            throw new InternalServerException("Le type de codification  est réquise", null);
//        }
//        if (dto.getDomaine() == null || dto.getDomaine().getIdeCode() == null || dto.getDomaine().getIdeCode().isEmpty()) {
//            throw new InternalServerException("Le domaine est", null);
//        }
//
//        CodifList porte = codifListRepository.findByCodificationAndCode(CodificationConstant.PORTEE, dto.getPorte().getIdeCode());
//        CodifList domaine = codifListRepository.findByCodificationAndCode(CodificationConstant.DOMAINES, dto.getDomaine().getIdeCode());
//
//        if (porte == null) {
//            throw new InternalServerException("La portée est réquise", null);
//        }
//        if (domaine == null) {
//            throw new InternalServerException("Le domaine est", null);
//        }
//        ApiError apiError = this.errorMessage.getApiError("400", TypeError.INFO, properties.getInfoPrase(), errors);
//        model.put(RestConstants.MSG_KEY, apiError);
//
//        if (type.equals(PERSIST_TYPE_UPDATE)) {
//            Codification c = codificationRepository.findOne(dto.getCode());
//
//            if (c == null) {
//                throw new InternalServerException("Le code est invalide", null);
//
//            }
//
//            if (!c.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//                throw new InternalServerException("Opération impossible. L'élément est deja activé", null);
//            }
//            c.setDomaine(domaine);
//            c.setPorte(porte);
//            c.setLibelle(dto.getLibelle());
//            codificationRepository.saveAndFlush(c);
//
//            return model;
//        } else if (type.equals(PERSIST_TYPE_INSERT)) {
//            Codification c = codificationRepository.findOne(dto.getCode());
//            if (c != null) {
//                throw new InternalServerException("Cette codification existe deja", null);
//            }
//
//            c = new Codification(dto.getCode(), dto.getLibelle());
//            c.setDomaine(domaine);
//            c.setPorte(porte);
//            c.setTypeCodif(codifListRepository.findByCodificationAndCode(CodificationConstant.TYPE_CODIF,
//                    dto.getType().getIdeCode()));
//            c.setStatut(properties.findStatutByCode(CodificationConstant.STATUT_PARAM_EN_SAISIE));
//
//            c = codificationRepository.save(c);
//            trackingParamObjetService.saveStatusTracking(c.getCode(), CodificationConstant.CONCEPT_METIER_CODIFICATION, c.getStatut());
//
//            return model;
//        }
//        throw new InternalServerException("Type d'pération non pris en compte", null);
//    }
//
//    public <T extends CodifItemSimpleObjectDTO> Object saveItemes(T dto, TwoArgFunction<T, Codification, Object> save) {
//
//        System.out.println("Liste " + dto);
//        if (dto.getCode() == null || dto.getCode().isEmpty()) {
//            throw new InternalServerException("Le code est requis", null);
//        }
//        if (!dto.getCode().matches(CodificationConstant.ALPHA_NUMERIQUE_REGEX)) {
//            throw new InternalServerException("Le code n'est pas valide", null);
//        }
//        if (dto.getCodification() == null || dto.getCodification().isEmpty()) {
//            throw new InternalServerException("La codification est requise", null);
//        }
//        Codification codif = codificationRepository.findOne(dto.getCodification());
//
//        if (codif == null) {
//            throw new InternalServerException(CODIFICATION, null);
//        }
//        if (!codif.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//            throw new InternalServerException("Opération impossible. La codification  est deja activée", null);
//
//        }
//        return save.apply(dto, codif);
//
//    }
//
//    public Object saveList(CodifListeSimpleDTO dto) {
//
//        return this.saveItemes(dto, (x, codif) -> {
//            HashMap<String, MessageParam> errors = new HashMap<>();
//            HashMap<String, Object> model = new HashMap<>();
//
//            if (dto.getLibelle() == null || dto.getLibelle().isEmpty()) {
//                throw new InternalServerException(LIBELLE_REQUIS, null);
//            }
//
//            System.out.println("DTO " + dto);
//            CodifList cl = codifListRepository.findByCodificationAndCode(dto.getCodification(), dto.getCode());
//
//            if (dto.getId() == null && cl != null || dto.getId() != null && cl != null && !dto.getId().equals(cl.getId())) {
//                throw new InternalServerException("Ce code à ete deja utilisé pour cette codification", null);
//            }
//            ApiError apiError = this.errorMessage.getApiError("400", TypeError.INFO, properties.getInfoPrase(), errors);
//            model.put(RestConstants.MSG_KEY, apiError);
//            if (dto.getId() != null && !dto.getId().isEmpty()) {
//
//                CodifList c = codifListRepository.findOne(dto.getId());
//                c.setLibelle(dto.getLibelle());
//                c.setIdeCode(dto.getCode());
//                c = codifListRepository.save(c);
//
//                Libelle l = libelleRepository.findByCodificationAndListAndCodeLangue(c.getCodification().getCode(),
//                        c.getIdeCode(), properties.getCodeLangue().getIdeCode());
//                if (l != null) {
//                    l.setLibelle(dto.getLibelle());
//                } else {
//                    l = new Libelle();
//                    l.setCodifList(c);
//                    l.setCodeLangue(properties.getCodeLangue());
//                    l.setLibelle(dto.getLibelle());
//                }
//                libelleRepository.save(l);
//                return model;
//            } else {
//                CodifList c = new CodifList();
//
//                c.setIdeCode(dto.getCode());
//                c.setCodification(codif);
//                c.setLibelle(dto.getLibelle());
//
//                codifListRepository.save(c);
//                Libelle l = new Libelle();
//                l.setCodifList(c);
//                l.setCodeLangue(properties.getCodeLangue());
//                l.setLibelle(dto.getLibelle());
//                libelleRepository.save(l);
//
//                System.out.println(c);
//                model.put("id", c.getId());
//                return model;
//            }
//
//        });
//    }
//
//    public Object saveEtiquette(CodifEtiquetteSimpleObjectDTO dto) {
//
//        return this.saveItemes(dto, (x, codif) -> {
//            HashMap<String, MessageParam> errors = new HashMap<>();
//            HashMap<String, Object> model = new HashMap<>();
//
//            if (dto.getLibelle() == null || dto.getLibelle().isEmpty()) {
//                throw new InternalServerException(LIBELLE_REQUIS, null);
//            }
//
//            Etiquette cl = etiquetteRepository.findByCodificationAndCode(dto.getCodification(), dto.getCode());
//
//            if (dto.getId() == null && cl != null || dto.getId() != null && cl != null && !dto.getId().equals(cl.getId())) {
//                throw new InternalServerException("Ce code à ete deja utilisé pour cette codification", null);
//            }
//            if (dto.getDateDebutValidite() != null && dto.getDateFinValidite() != null
//                    && Instant.parse(dto.getDateDebutValidite()).isAfter(Instant.parse(dto.getDateFinValidite()))) {
//                throw new InternalServerException("Les dates de vadité sont incorreces", null);
//
//            }
//            if (dto.getDateDebutValidite() != null && dto.getDateFinValidite() != null
//                    && Instant.parse(dto.getDateDebutValidite()).isAfter(Instant.parse(dto.getDateFinValidite()))) {
//                throw new InternalServerException("Les dates de vadité sont incorreces", null);
//
//            }
//            ApiError apiError = this.errorMessage.getApiError("400", TypeError.INFO, properties.getInfoPrase(), errors);
//            model.put(RestConstants.MSG_KEY, apiError);
//            if (dto.getId() != null && !dto.getId().isEmpty()) {
//
//                Etiquette c = etiquetteRepository.findOne(dto.getId());
//                c.setLibelle(dto.getLibelle());
//                c.setCode(dto.getCode());
//                
//               if(dto.getDateFinValidite()!=null){
//                    c.setDateFinValidite(Date.from(Instant.parse(dto.getDateFinValidite())));
//                }
//
//                return model;
//            } else {
//                Etiquette c = new Etiquette();
//
//                c.setCode(dto.getCode());
//                c.setCodification(codif);
//                c.setLibelle(dto.getLibelle());
//                if (dto.getDateDebutValidite() == null) {
//                    c.setDateDebutValidite(new Date());
//                }else{
//                    c.setDateDebutValidite(Date.from(Instant.parse(dto.getDateDebutValidite())));
//                }
//                if(dto.getDateFinValidite()!=null){
//                    c.setDateFinValidite(Date.from(Instant.parse(dto.getDateFinValidite())));
//                }
//               c= etiquetteRepository.save(c);
//                
//                model.put("id", c.getId());
//                return model;
//            }
//
//        });
//    }
//
//    public Object saveParam(CodifParamSimpleDTO dto) {
//        return this.saveItemes(dto, (x, codif) -> {
//            if (dto.getValeur() == null || dto.getValeur().isEmpty()) {
//                throw new InternalServerException(LIBELLE_REQUIS, null);
//            }
//            HashMap<String, MessageParam> errors = new HashMap<>();
//            HashMap<String, Object> model = new HashMap<>();
//            ApiError apiError = this.errorMessage.getApiError("400", TypeError.INFO, properties.getInfoPrase(), errors);
//            model.put(RestConstants.MSG_KEY, apiError);
//            List<Parameter> l = parameterRepository.findByCodification(codif.getCode());
//
//            if (dto.getId() == null || dto.getId().isEmpty()) {
//
//                if (l.stream().filter(z -> z.getDateFinValidite() == null).count() > 0) {
//                    throw new InternalServerException("Veuillez definir une date de fin de vallidité pour le paremetre en actif en cours", null);
//
//                }
//
//                Optional<Parameter> last = l.stream().max(Comparator.comparing(t -> t.getDateFinValidite().getTime()));
//                if (dto.getDateFinValidite() != null) {
//                    if (l.isEmpty() && Date.from(Instant.parse(dto.getDateFinValidite())).before(new Date())
//                            || last.isPresent() && last.get().getDateFinValidite().after(Date.from(Instant.parse(dto.getDateFinValidite())))) {
//                        throw new InternalServerException(DATE_FIN_VALIDITE, null);
//
//                    }
//                }
//
//                Parameter p = new Parameter();
//                p.setValeur(dto.getValeur());
//                if (l.isEmpty()) {
//                    p.setDateDebutValidite(new Date());
//
//                } else {
//                    p.setDateDebutValidite(new Date(last.get().getDateFinValidite().getTime() + 24 * 3600000));
//                }
//                p.setCodification(codif);
//                if (dto.getDateFinValidite() != null) {
//                    p.setDateFinValidite(Date.from(Instant.parse(dto.getDateFinValidite())));
//                }
//                parameterRepository.save(p);
//                model.put("id", p.getId());
//                return model;
//
//            } else {
//                Optional<Parameter> last = l.stream().max(Comparator.comparing(t -> t.getDateDebutValidite().getTime()));
//                if (last.isPresent() && dto.getDateFinValidite() == null && !last.get().getId().equals(dto.getId())) {
//                    throw new InternalServerException(DATE_FIN_VALIDITE, null);
//
//                }
//
//                if (dto.getDateFinValidite() != null && dto.getDateDebutValidite()!=null) {
//                    List<Parameter> ps = parameterRepository.findParamActivatedByCodification(codif.getCode(), Date.from(Instant.parse(dto.getDateFinValidite())));
//                    if ((ps!=null && ps.size() > 2) || Instant.parse(dto.getDateDebutValidite()).isAfter(Instant.parse(dto.getDateFinValidite()))) {
//                        throw new InternalServerException(DATE_FIN_VALIDITE, null);
//
//                    }
//
//                }
//                Parameter parm = parameterRepository.findOne(dto.getId());
//                if (parm == null) {
//                    throw new InternalServerException("Ce parametre n'existe pas", null);
//
//                }
//                if (dto.getDateFinValidite() != null) {
//                    parm.setDateFinValidite(Date.from(Instant.parse(dto.getDateFinValidite())));
//                }
//                parm.setValeur(dto.getValeur());
//                parameterRepository.save(parm);
//                return model;
//
//            }
//
//        });
//    }
//
//    public <T> Object checkCodificationBeforeDelete(Codification c, T dto, Function<T, Object> v) {
//
//        if (!c.getStatut().getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)) {
//            throw new InternalServerException("Opération impossible. La codification  est deja activée", null);
//
//        }
//        return v.apply(dto);
//    }
//
//    public Object delete(CodifItemSimpleObjectDTO dto) {
//        HashMap<String, MessageParam> errors = new HashMap<>();
//        HashMap<String, Object> model = new HashMap<>();
//
//        ApiError apiError = this.errorMessage.getApiError("400", TypeError.INFO, properties.getInfoPrase(), errors);
//        model.put(RestConstants.MSG_KEY, apiError);
//        try {
//            switch (dto.getType().getIdeCode()) {
//                case CodificationConstant.TYPE_CODIF_LISTE:
//                    CodifList c = codifListRepository.findOne(dto.getId());
//                    if (c == null) {
//                        throw new InternalServerException(ITEM_EXISTE_PAS, null);
//                    }
//                    return this.checkCodificationBeforeDelete(c.getCodification(), dto, (x) -> {
//                        Libelle l = libelleRepository.findByIdAndLangue(x.getId(), properties.getCodeLangue().getIdeCode());
//                        if (l != null) {
//                            libelleRepository.delete(l.getId());
//                        }
//                        codifListRepository.delete(c.getId());
//                        return model;
//
//                    });
//
//                case CodificationConstant.TYPE_CODIF_PARAMETRE:
//                    Parameter p = parameterRepository.findOne(dto.getId());
//                    if (p == null) {
//                        throw new InternalServerException(ITEM_EXISTE_PAS, null);
//                    }
//                    return this.checkCodificationBeforeDelete(p.getCodification(), dto, x -> {
//                        parameterRepository.delete(x.getId());
//                        return model;
//                    });
//                case CodificationConstant.TYPE_CODIF_ETIQUETTE:
//                    Etiquette e = etiquetteRepository.findOne(dto.getId());
//                    if (e == null) {
//                        throw new InternalServerException(ITEM_EXISTE_PAS, null);
//                    }
//
//                    return this.checkCodificationBeforeDelete(e.getCodification(), dto, x -> {
//                        etiquetteRepository.delete(e.getId());
//                        return model;
//                    });
//
//                case CodificationConstant.TYPE_CODIF_CLASSEUR:
//                    ClasseurDTO cl = new ClasseurDTO();
//                    cl.setId(dto.getId());
//                    return classeurService.deleteClasseur(cl);
//                    default:
//            }
//
//        } catch (Exception e) {
//
//        }
//        throw new InternalServerException(properties.getDeletePhrase(), null);
//
//    }
//}
