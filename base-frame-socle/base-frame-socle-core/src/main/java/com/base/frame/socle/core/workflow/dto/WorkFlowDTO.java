/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;


import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.PageParam;
import com.base.frame.socle.core.workflow.entity.WorkFlow;
import com.base.frame.socle.core.workflow.entity.WorkFlowEtape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author W.KOUWONOU
 * @since 22/03/2018
 * @version 1.0.0
 */
@Deprecated
public class WorkFlowDTO extends PageParam<WorkFlow> {

    private List<WorkFlow> content;

    private WorkFlow data;
    private WorkFlowEtape etape;
    private Integer current;

    private List<ParamList> cdListe;
    private List<WorkFlowEtape> wcListe;

    private String motCle;

    private boolean checked = false;
 private String workflowLabel;
    public WorkFlowDTO() {
    }

    public WorkFlowDTO(List<WorkFlow> content, Integer current) {
        this.content = content;
        this.current = current;
    }

    public List<WorkFlow> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return content;
    }

    public void setContent(List<WorkFlow> content) {
        this.content = content;
    }

    public List<ParamList> getCdListe() {
        return cdListe;
    }

    public void setCdListe(List<ParamList> cdListe) {
        this.cdListe = cdListe;
    }

    public List<WorkFlowEtape> getWcListe() {
        if(wcListe==null){
            wcListe=new ArrayList<>();
        }
        return wcListe;
    }

    public void setWcListe(List<WorkFlowEtape> wcListe) {
        this.wcListe = wcListe;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public WorkFlow getData() {
        return data;
    }

    public void setData(WorkFlow data) {
        this.data = data;
    }

    public WorkFlowEtape getEtape() {
        return etape;
    }

    public void setEtape(WorkFlowEtape etape) {
        this.etape = etape;
    }

    public boolean getChecked() {
        return current != null;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getWorkflowLabel() {
        return workflowLabel;
    }

    public void setWorkflowLabel(String workflowLabel) {
        this.workflowLabel = workflowLabel;
    }

    @Override
    public String toString() {
        return "WorkFlowDTO{" + "content=" + content + ", data=" + data + ", etape=" + etape + ", current=" + current + ", cdListe=" + cdListe + ", wcListe=" + wcListe + ", motCle=" + motCle + ", checked=" + checked + ", workflowLabel=" + workflowLabel + '}';
    }

   

}
