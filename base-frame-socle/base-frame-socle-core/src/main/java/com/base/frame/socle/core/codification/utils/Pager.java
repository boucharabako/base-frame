package com.base.frame.socle.core.codification.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to calculate span of buttons which will be displayed on a page
 *
 * @author Branislav Lazic
 * @author Bruno Raljic
 */
public class Pager {

    public static final int BUTTONS_TO_SHOW = 5;
    public static final int INITIAL_PAGE = 0;
    public static final int INITIAL_PAGE_SIZE = 5;

    private int buttonsToShow = 5;

    private int startPage;

    private int endPage;

    public static int[] getPageSize() {
        int[] PAGE_SIZES = {5, 10, 20,100,500,1000,10000};
        return PAGE_SIZES;
    }

    /**
     *
     * @param totalPages le nombre total de pages
     * @param currentPage la page courante
     * @param buttonsToShow le nombre de bouttons à afficher
     */
        public Pager(int totalPages, int currentPage, int buttonsToShow) {

        setButtonsToShow(buttonsToShow);

        int halfPagesToShow = getButtonsToShow() / 2;

        if (totalPages <= getButtonsToShow()) {
            setStartPage(1);
            setEndPage(totalPages);

        } else if (currentPage - halfPagesToShow <= 0) {
            setStartPage(1);
            setEndPage(getButtonsToShow());

        } else if (currentPage + halfPagesToShow == totalPages) {
            setStartPage(currentPage - halfPagesToShow);
            setEndPage(totalPages);

        } else if (currentPage + halfPagesToShow > totalPages) {
            setStartPage(totalPages - getButtonsToShow() + 1);
            setEndPage(totalPages);

        } else {
            setStartPage(currentPage - halfPagesToShow);
            setEndPage(currentPage + halfPagesToShow);
        }

    }

    public static Object[] customNumberSequence(Integer from, Integer to) {
        List<Integer> value = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            value.add(i);
        }
        return value.toArray();
    }

    public int getButtonsToShow() {
        return buttonsToShow;
    }

    public void setButtonsToShow(int buttonsToShow) {
        if (buttonsToShow % 2 != 0) {
            this.buttonsToShow = buttonsToShow;
        } else {
            throw new IllegalArgumentException("Must be an odd value!");
        }
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    @Override
    public String toString() {
        return "Pager [startPage=" + startPage + ", endPage=" + endPage + "]";
    }

}
