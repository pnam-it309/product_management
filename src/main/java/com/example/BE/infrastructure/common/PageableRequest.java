package com.example.BE.infrastructure.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableRequest {
    private int page = 0;
    private int size = 10;
}
