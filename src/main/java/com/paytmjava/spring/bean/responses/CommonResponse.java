package com.paytmjava.spring.bean.responses;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CommonResponse {
    private int id;
    private String message;
}
