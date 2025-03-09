//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.Classeur;
//import java.util.Optional;
//
///*
// * abs-bud-nomenclature
// *
// * @author Pierre NGS creée le 9 juil. 2018 17:49:50
// */
//public class ClassificationTree {
//
//    private String id;
//    private String path;
//    private String text;
//    private String parent;
//    private Object children;
//    private String libelle;
//    private String code;
//    private Object selected;
//    private State state = new State();
//    private Object imputable;
//
//    public ClassificationTree() {
//    }
//
//    public ClassificationTree(Classeur entite, Optional<String> idCla, boolean imputable) {
//        this.id = entite.getId();
//        this.parent = entite.getParent() == null ? "#" : entite.getParent().getId();
//        this.text = entite.getLibelle() == null ? "" : entite.getCode() + "-" + entite.getLibelle();
//        this.libelle = entite.getLibelle();
//        this.code = entite.getCode();
//        this.children = true;
//        this.selected = true;
//        this.imputable = imputable;
//
//    }
//
//    public ClassificationTree(Classeur entite, boolean imputable) {
//        this.id = entite.getId();
//        this.parent = entite.getParent() == null ? "#" : entite.getParent().getId();
//        this.text = entite.getLibelle() == null ? "" : entite.getCode() + "-" + entite.getLibelle();
//        this.libelle = entite.getLibelle();
//        this.code = entite.getCode();
//        this.children = true;
//        this.selected = true;
//        this.imputable = imputable;
//    }
//
//    public ClassificationTree(Classeur entite) {
//        this.id = entite.getId();
//        this.parent = entite.getParent() == null ? "#" : entite.getParent().getId();
//        this.text = entite.getLibelle() == null ? "" : entite.getCode() + "-" + entite.getLibelle();
//        this.libelle = entite.getLibelle();
//        this.code = entite.getCode();
//        this.children = true;
//        this.selected = true;
//    }
//
//    public ClassificationTree(String id, String path, String nale) {
//        this.id = id;
//        this.path = path;
//        this.text = nale;
//        this.children = true;
//        this.selected = false;
//    }
//
//    public String getParent() {
//        return parent;
//    }
//
//    public void setParent(String parent) {
//        this.parent = parent;
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
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public Object getChildren() {
//        return children;
//    }
//
//    public void setChildren(Object children) {
//        this.children = children;
//    }
//
//    public String getLibelle() {
//        return libelle;
//    }
//
//    public void setLibelle(String libelle) {
//        this.libelle = libelle;
//    }
//
//    public Object getSelected() {
//        return selected;
//    }
//
//    public void setSelected(Object selected) {
//        this.selected = selected;
//    }
//
//    public State getState() {
//        return state;
//    }
//
//    public void setState(State state) {
//        this.state = state;
//    }
//
//    public Object getImputable() {
//        return imputable;
//    }
//
//    public void setImputable(Object imputable) {
//        this.imputable = imputable;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public class State {
//
//        private boolean opened;
//        private boolean disabled;
//        private boolean selected;
//
//        public State() {
//        }
//
//        public State(boolean opened, boolean selected) {
//            this.opened = opened;
//
//            this.selected = selected;
//        }
//
//        public State(boolean opened, boolean disabled, boolean selected) {
//            this.opened = opened;
//            this.disabled = disabled;
//            this.selected = selected;
//        }
//
//        public boolean isOpened() {
//            return opened;
//        }
//
//        public void setOpened(boolean opened) {
//            this.opened = opened;
//        }
//
//        public boolean isDisabled() {
//            return disabled;
//        }
//
//        public void setDisabled(boolean disabled) {
//            this.disabled = disabled;
//        }
//
//        public boolean isSelected() {
//            return selected;
//        }
//
//        public void setSelected(boolean selected) {
//            this.selected = selected;
//        }
//
//    }
//
//    @Override
//    public String toString() {
//        return "ClassificationTree{" + "id=" + id + ", path=" + path + ", text=" + text + ", parent=" + parent + ", children=" + children + ", libelle=" + libelle + ", code=" + code + ", selected=" + selected + ", state=" + state + ", imputable=" + imputable + '}';
//    }
//    
//}
