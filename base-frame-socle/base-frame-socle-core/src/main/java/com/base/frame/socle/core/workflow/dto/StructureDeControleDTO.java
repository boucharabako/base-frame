///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.Libelle;
//import com.ngs.core.codification.entities.SousZone;
//import com.ngs.core.codification.entities.StructureDeControle;
//import com.ngs.core.codification.entities.Zone;
//import com.ngs.core.codification.utils.Pager;
//import com.ngs.core.utils.pagination.PageParam;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author Sewa
// */
//public class StructureDeControleDTO extends PageParam<StructureDeControle> {
//
//    private StructureDeControle structureDeControle;
//
//    private Zone zone;
//
//    private SousZone sousZone;
//
//    private List<StructureDeControle> structureDeControles = new ArrayList<>();
//
//    private List<Zone> zones = new ArrayList<>();
//
//    private List<SousZone> sousZones = new ArrayList<>();
//
//    private String currentStructureDeControle;
//
//    private String currentZone;
//    private String currentSousZone;
//
//    private String currentImage;
//
//    private String libelleSeparateur;
//
//    private String valeurSeparateur;
//
//    private Integer longeurTotalZone;
//
//    private Integer longeurZone;
//
//    private Pager pager;
//
//    private Boolean showZone = Boolean.FALSE;
//
//    private Boolean showSousZone = Boolean.FALSE;
//
//    private boolean show = true;
//
//    private boolean show2 = true;
//
//    private boolean showListControle = true;
//
//    private String newSeparateur;
//
//    private String currentZoneName;
//
//    private String currentStructureDeControleName;
//
//    private String idS;
//
//    private List<StructureDeControleDTO> listStructureDeControleDTO = new ArrayList<>();
//
//    private PageParam zonePageParam;
//
//    private String libelleTypeDeDonnee;
//
//    private SousZone sousZoneToAdd;
//
//    private List<SousZone> temporalListSousZone = new ArrayList<>();
//
//    private List<Libelle> listCodifTypeDonnesFiltre = new ArrayList<>();
//
//    private String domaine;
//
//    private String id;
//
//    private String sousZoneListDeControle;
//    private String sousZoneClassification;
//    private String codeListCiblee;
//    private Integer niveauListeCiblee;
//
//    private String codeListCibleeSZ;
//    private Integer niveauListeCibleeSZ;
//
//    private boolean listCiblee = false;
//
//    private String zoneListDeControle;
//    private String zoneClassification;
//    private String idZone;
//    private String idSousZone;
//    private String numOrdre;
//    private String intitule;
//    private String type;
//    private String listControle;
//    private String longeur;
//
//    public StructureDeControleDTO() {
//    }
//
//    public StructureDeControleDTO(StructureDeControle structureDeControle, boolean show) {
//        this.structureDeControle = structureDeControle;
//        this.show = show;
//    }
//
//    public StructureDeControle getStructureDeControle() {
//        return structureDeControle;
//    }
//
//    public void setStructureDeControle(StructureDeControle structureDeControle) {
//        this.structureDeControle = structureDeControle;
//    }
//
//    public Zone getZone() {
//        return zone;
//    }
//
//    public void setZone(Zone zone) {
//        this.zone = zone;
//    }
//
//    public SousZone getSousZone() {
//        return sousZone;
//    }
//
//    public void setSousZone(SousZone sousZone) {
//        this.sousZone = sousZone;
//    }
//
//    public List<StructureDeControle> getStructureDeControles() {
//        return structureDeControles;
//    }
//
//    public void setStructureDeControles(List<StructureDeControle> structureDeControles) {
//        this.structureDeControles = structureDeControles;
//    }
//
//    public List<Zone> getZones() {
//        return zones;
//    }
//
//    public void setZones(List<Zone> zones) {
//        this.zones = zones;
//    }
//
//    public List<SousZone> getSousZones() {
//        return sousZones;
//    }
//
//    public void setSousZones(List<SousZone> sousZones) {
//        this.sousZones = sousZones;
//    }
//
//    public String getCurrentStructureDeControle() {
//        return currentStructureDeControle;
//    }
//
//    public void setCurrentStructureDeControle(String currentStructureDeControle) {
//        this.currentStructureDeControle = currentStructureDeControle;
//    }
//
//    public String getCurrentZone() {
//        return currentZone;
//    }
//
//    public void setCurrentZone(String currentZone) {
//        this.currentZone = currentZone;
//    }
//
//    public String getCurrentSousZone() {
//        return currentSousZone;
//    }
//
//    public void setCurrentSousZone(String currentSousZone) {
//        this.currentSousZone = currentSousZone;
//    }
//
//    public String getLibelleSeparateur() {
//        return libelleSeparateur;
//    }
//
//    public void setLibelleSeparateur(String libelleSeparateur) {
//        this.libelleSeparateur = libelleSeparateur;
//    }
//
//    public String getValeurSeparateur() {
//        return valeurSeparateur;
//    }
//
//    public void setValeurSeparateur(String valeurSeparateur) {
//        this.valeurSeparateur = valeurSeparateur;
//    }
//
//    public Integer getLongeurTotalZone() {
//        return longeurTotalZone == null ? 0 : longeurTotalZone;
//    }
//
//    public void setLongeurTotalZone(Integer longeurTotalZone) {
//        this.longeurTotalZone = longeurTotalZone;
//    }
//
//    public Integer getLongeurZone() {
//        return longeurZone == null ? 0 : longeurZone;
//    }
//
//    public void setLongeurZone(Integer longeurZone) {
//        this.longeurZone = longeurZone;
//    }
//
//    public String getCurrentImage() {
//        return currentImage;
//    }
//
//    public void setCurrentImage(String currentImage) {
//        this.currentImage = currentImage;
//    }
//
//    public String getCodeListCiblee() {
//        return codeListCiblee;
//    }
//
//    public void setCodeListCiblee(String codeListCiblee) {
//        this.codeListCiblee = codeListCiblee;
//    }
//
//    public Pager getPager() {
//        return pager;
//    }
//
//    public void setPager(Pager pager) {
//        this.pager = pager;
//    }
//
//    public Boolean getShowZone() {
//        return showZone;
//    }
//
//    public void setShowZone(Boolean showZone) {
//        this.showZone = showZone;
//    }
//
//    public Boolean getShowSousZone() {
//        return showSousZone;
//    }
//
//    public void setShowSousZone(Boolean showSousZone) {
//        this.showSousZone = showSousZone;
//    }
//
//    public boolean getShow() {
//        return show;
//    }
//
//    public void setShow(boolean show) {
//        this.show = show;
//    }
//
//    public boolean getShow2() {
//        return show2;
//    }
//
//    public void setShow2(boolean show2) {
//        this.show2 = show2;
//    }
//
//    public List<StructureDeControleDTO> getListStructureDeControleDTO() {
//        return listStructureDeControleDTO;
//    }
//
//    public void setListStructureDeControleDTO(List<StructureDeControleDTO> listStructureDeControleDTO) {
//        this.listStructureDeControleDTO = listStructureDeControleDTO;
//    }
//
//    public String getNewSeparateur() {
//        return newSeparateur;
//    }
//
//    public void setNewSeparateur(String newSeparateur) {
//        this.newSeparateur = newSeparateur;
//    }
//
//    public PageParam getZonePageParam() {
//        return zonePageParam;
//    }
//
//    public void setZonePageParam(PageParam zonePageParam) {
//        this.zonePageParam = zonePageParam;
//    }
//
//    public String getCurrentZoneName() {
//        return currentZoneName;
//    }
//
//    public void setCurrentZoneName(String currentZoneName) {
//        this.currentZoneName = currentZoneName;
//    }
//
//    public String getIdS() {
//        return idS;
//    }
//
//    public void setIdS(String idS) {
//        this.idS = idS;
//    }
//
//    public String getLibelleTypeDeDonnee() {
//        return libelleTypeDeDonnee;
//    }
//
//    public void setLibelleTypeDeDonnee(String libelleTypeDeDonnee) {
//        this.libelleTypeDeDonnee = libelleTypeDeDonnee;
//    }
//
//    public String getCurrentStructureDeControleName() {
//        return currentStructureDeControleName;
//    }
//
//    public void setCurrentStructureDeControleName(String currentStructureDeControleName) {
//        this.currentStructureDeControleName = currentStructureDeControleName;
//    }
//
//    public SousZone getSousZoneToAdd() {
//        return sousZoneToAdd;
//    }
//
//    public void setSousZoneToAdd(SousZone sousZoneToAdd) {
//        this.sousZoneToAdd = sousZoneToAdd;
//    }
//
//    public List<SousZone> getTemporalListSousZone() {
//        return temporalListSousZone;
//    }
//
//    public void setTemporalListSousZone(List<SousZone> temporalListSousZone) {
//        this.temporalListSousZone = temporalListSousZone;
//    }
//
//    public List<Libelle> getListCodifTypeDonnesFiltre() {
//        return listCodifTypeDonnesFiltre;
//    }
//
//    public void setListCodifTypeDonnesFiltre(List<Libelle> listCodifTypeDonnesFiltre) {
//        this.listCodifTypeDonnesFiltre = listCodifTypeDonnesFiltre;
//    }
//
//    public String getDomaine() {
//        return domaine;
//    }
//
//    public void setDomaine(String domaine) {
//        this.domaine = domaine;
//    }
//
//    public String getSousZoneListDeControle() {
//        return sousZoneListDeControle;
//    }
//
//    public void setSousZoneListDeControle(String sousZoneListDeControle) {
//        this.sousZoneListDeControle = sousZoneListDeControle;
//    }
//
//    public String getZoneListDeControle() {
//        return zoneListDeControle;
//    }
//
//    public void setZoneListDeControle(String zoneListDeControle) {
//        this.zoneListDeControle = zoneListDeControle;
//    }
//
//    public boolean isShowListControle() {
//        return showListControle;
//    }
//
//    public void setShowListControle(boolean showListControle) {
//        this.showListControle = showListControle;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getIdSousZone() {
//        return idSousZone;
//    }
//
//    public void setIdSousZone(String idSousZone) {
//        this.idSousZone = idSousZone;
//    }
//
//    public String getNumOrdre() {
//        return numOrdre;
//    }
//
//    public void setNumOrdre(String numOrdre) {
//        this.numOrdre = numOrdre;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getListControle() {
//        return listControle;
//    }
//
//    public void setListControle(String listControle) {
//        this.listControle = listControle;
//    }
//
//    public String getLongeur() {
//        return longeur;
//    }
//
//    public void setLongeur(String longeur) {
//        this.longeur = longeur;
//    }
//
//    public String getIntitule() {
//        return intitule;
//    }
//
//    public void setIntitule(String intitule) {
//        this.intitule = intitule;
//    }
//
//    public String getIdZone() {
//        return idZone;
//    }
//
//    public void setIdZone(String idZone) {
//        this.idZone = idZone;
//    }
//
//    public boolean isListCiblee() {
//        return listCiblee;
//    }
//
//    public void setListCiblee(boolean listCiblee) {
//        this.listCiblee = listCiblee;
//    }
//
//    public Integer getNiveauListeCiblee() {
//        return niveauListeCiblee;
//    }
//
//    public void setNiveauListeCiblee(Integer niveauListeCiblee) {
//        this.niveauListeCiblee = niveauListeCiblee;
//    }
//
//    public String getCodeListCibleeSZ() {
//        return codeListCibleeSZ;
//    }
//
//    public void setCodeListCibleeSZ(String codeListCibleeSZ) {
//        this.codeListCibleeSZ = codeListCibleeSZ;
//    }
//
//    public Integer getNiveauListeCibleeSZ() {
//        return niveauListeCibleeSZ;
//    }
//
//    public void setNiveauListeCibleeSZ(Integer niveauListeCibleeSZ) {
//        this.niveauListeCibleeSZ = niveauListeCibleeSZ;
//    }
//
//    public String getZoneClassification() {
//        return zoneClassification;
//    }
//
//    public void setZoneClassification(String zoneClassification) {
//        this.zoneClassification = zoneClassification;
//    }
//
//    public String getSousZoneClassification() {
//        return sousZoneClassification;
//    }
//
//    public void setSousZoneClassification(String sousZoneClassification) {
//        this.sousZoneClassification = sousZoneClassification;
//    }
//
//}
