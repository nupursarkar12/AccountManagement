package com.paytmjava.spring.service;

import com.paytmjava.spring.bean.dto.AccountDto;
import com.paytmjava.spring.bean.dto.CreditDto;
import com.paytmjava.spring.bean.dto.DebitDto;
import com.paytmjava.spring.bean.responses.AccountGenerationResponse;
import com.paytmjava.spring.bean.responses.AccountResponse;
import com.paytmjava.spring.bean.responses.CommonResponse;
import com.paytmjava.spring.bean.responses.StatementResponse;

public interface AccountService {
    AccountGenerationResponse createAccount(AccountDto accountDto);
    AccountResponse getAccount(String acc_no);
    CommonResponse debitEntry(DebitDto db);
    CommonResponse creditEntry(CreditDto creditDto);
    StatementResponse generateStatement(String acc_no);

}
