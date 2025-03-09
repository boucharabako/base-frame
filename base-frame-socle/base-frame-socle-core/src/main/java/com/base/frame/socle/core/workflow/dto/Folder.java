/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 *
 * @author kouwonou
 */
public class Folder {

    private String id;
    private String code;
    private String text;
    private String parent;
    private Object children;
    private State state = new State();
    private String libelleParent;

    public Folder(String id, String code, String text, String parent, Object children, State state) {
        this.id = id;
        this.code = code;
        this.text = text;
        this.parent = parent;
        this.children = children;
        this.state = state;
    }

    public Folder(String id, String code, String text, String parent, Object children) {
        this.id = id;
        this.code = code;
        this.text = text;
        this.parent = parent;
        this.children = children;
    }

    public Folder(String id, String code, String text, String parent, Object children, String libelleParent) {
        this.id = id + "-" + parent;
        this.code = code;
        this.text = code + " : " + text;
        this.parent = parent;
        this.children = children;
        this.libelleParent = libelleParent;
    }

    public Folder(String id, String code, String text, String parent, Object children, boolean disabledParent) {
        this.id = id;
        this.code = code;
        this.text = text;
        this.parent = parent;
        this.children = children;
        this.state.setDisabled(disabledParent);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getLibelleParent() {
        return libelleParent;
    }

    public void setLibelleParent(String libelleParent) {
        this.libelleParent = libelleParent;
    }

    public class State {

        private Boolean opened = false;
        private Boolean selected = false;
        private Boolean disabled = false;

        public State() {
        }

        public State(Boolean opened, Boolean selected) {
            this.opened = opened;
            this.selected = selected;
        }

        public boolean getOpened() {
            return opened;
        }

        public void setOpened(boolean opened) {
            this.opened = opened;
        }

        public boolean getSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public Boolean getDisabled() {
            return disabled;
        }

        public void setDisabled(Boolean disabled) {
            this.disabled = disabled;
        }

    }

}
