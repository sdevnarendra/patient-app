package com.clinic.patient.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pagination {
    private Integer page = 1;
    private Integer size = 5;
    private Sort.Direction dir = Sort.Direction.ASC;
    private String sort = "id";

    public Pageable getPageAble() {

        return PageRequest.of(page - 1, size, dir, sort);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Sort.Direction getDir() {
        return dir;
    }

    public void setDir(Sort.Direction dir) {
        this.dir = dir;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
