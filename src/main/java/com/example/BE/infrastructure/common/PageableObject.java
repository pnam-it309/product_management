package com.example.BE.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableObject<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;
}
