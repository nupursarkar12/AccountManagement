package com.paytmjava.spring.bean.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountGenerationResponse {
    private String message;
    private String acc_no;

}
