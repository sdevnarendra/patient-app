package com.clinic.patient.dto;

public class QuickSearchDTO extends Pagination {
    private String search = "";

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
