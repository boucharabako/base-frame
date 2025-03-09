/*/
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * structure d'une pagination
 *
 * @author W.KOUWONOU
 * @param <T> le parametre
 * @date 19/03/2018
 *
 */
public  class PageParam<T> {

  
    /**
     * le nombre d'element dans la liste
     */
    private Integer size=5;
    private Integer sizeSelected=5;
    
    /**
     * le numero la page courante
     */
    private Integer page=1;
    /**
     * le nombre total de page
     */
    private Integer totalPage;
    /**
     * le nombre total d'elemnt
     */
    private Long totalElement;
    /**
     *le numero des  pages sous forme de tableau d'entier
     */
    private List<Integer> pages;
/**
 * Defini s'il y a d'action suivante pour le statut param courant
 */
      private boolean visible = true;
/**
 * Grise les actions en fonction du statut courant
 */
    private boolean visible2 = true;
  private Integer[] listPages=new Integer[]{5,10,15,20,30,40,50,60,70,100,150,200};
    public Integer getTotalPage() {
        if (totalElement != null) {
         
            totalPage = ((Long)(totalElement / size)).intValue();
            if (totalElement % size >= 1) {
                totalPage++;
            }
        }
        return totalPage;
    }
    public List<Integer> getPages() {
        pages = new ArrayList<>();
        if (totalPage != null) {
            for (int i = 1; i <= totalPage; i++) {
                pages.add(i);
            }
        }
        return pages;
    }


    /**
     * le numero la page courante
     * @return le numero la page courante
     */
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
 /**
     * le numero la page courante
     * @return le numero la page courante
     */
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
 /**
     * le nombre total d'elemnt
     * @return le nombre total d'elemnt
     */
    public Long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(Long totalElement) {
        this.totalElement = totalElement;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer[] getListPages() {
        return listPages;
    }

    public void setListPages(Integer[] listPages) {
        this.listPages = listPages;
    }

    public Integer getSizeSelected() {
        return sizeSelected;
    }

    public void setSizeSelected(Integer sizeSelected) {
        this.sizeSelected = sizeSelected;
    }
 public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible2() {
        return visible2;
    }

    public void setVisible2(boolean visible2) {
        this.visible2 = visible2;
    }
    @Override
    public String toString() {
        return "PageParam{" + "content="  + ", size=" + size + ", page=" + page + ", totalPage=" + totalPage + ", totalElement=" + totalElement + '}';
    }
    

}
