package com.paytmjava.spring.bean.responses;

import com.paytmjava.spring.bean.dto.AccountDto;
import com.paytmjava.spring.bean.model.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {
    private String message;
    private AccountDto accountDto;
}
