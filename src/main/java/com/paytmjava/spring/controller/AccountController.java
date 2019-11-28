package com.paytmjava.spring.controller;

import com.paytmjava.spring.bean.dto.AccountDto;
import com.paytmjava.spring.bean.dto.CreditDto;
import com.paytmjava.spring.bean.dto.DebitDto;
import com.paytmjava.spring.bean.model.Credit;
import com.paytmjava.spring.bean.request.AccountRequest;
import com.paytmjava.spring.bean.request.CreditRequest;
import com.paytmjava.spring.bean.request.DebitRequest;
import com.paytmjava.spring.bean.responses.AccountGenerationResponse;
import com.paytmjava.spring.bean.responses.AccountResponse;
import com.paytmjava.spring.bean.responses.CommonResponse;
import com.paytmjava.spring.bean.responses.StatementResponse;
import com.paytmjava.spring.service.AccountService;
import com.paytmjava.spring.service.AccountServiceImp;
import com.paytmjava.spring.utility.AccountMapper;
import com.paytmjava.spring.utility.CreditMapper;
import com.paytmjava.spring.utility.DebitMapper;
import org.apache.log4j.Logger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/")
public class AccountController {
    @Autowired
    AccountService accountService;
    AccountMapper accountMapper= Mappers.getMapper(AccountMapper.class);
    DebitMapper debitMapper= Mappers.getMapper(DebitMapper.class);
    CreditMapper creditMapper= Mappers.getMapper(CreditMapper.class);
    static org.apache.log4j.Logger logger = Logger.getLogger(AccountServiceImp.class);
    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<AccountGenerationResponse> createUser(@Valid @RequestBody AccountRequest accountRequest) {
       final AccountDto accountDto= accountMapper.requestToDto(accountRequest);
        return new ResponseEntity<AccountGenerationResponse> (accountService.createAccount(accountDto),HttpStatus.OK);
    }

    @GetMapping(value="/getAccount/{acc_no}", headers="Accept=application/json")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("acc_no") String acc_no) {
        AccountResponse acc=accountService.getAccount(acc_no);
        return new ResponseEntity<AccountResponse>(acc, HttpStatus.OK);
    }

    @PostMapping(value="/debit",headers="Accept=application/json")
    public ResponseEntity<CommonResponse> debitEntry(@Valid @RequestBody DebitRequest debitRequest) {
        final DebitDto debitDto = debitMapper.requestToDto(debitRequest);
        CommonResponse commonResponse = accountService.debitEntry(debitDto);
        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

    }
    @PostMapping(value="/credit",headers="Accept=application/json")
    public ResponseEntity<CommonResponse> createTransactionEntry(@Valid @RequestBody CreditRequest creditRequest) {
        //logger.info("**************inside create transction entry************");
        final CreditDto creditDto = creditMapper.requestToDto(creditRequest);
       CommonResponse commonResponse = accountService.creditEntry(creditDto);
       // logger.info("**************after credit entry function call************");
        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);
    }
    @GetMapping(value="/generateStatement",headers="Accept=application/json")
    public ResponseEntity<StatementResponse> generateStatement(@RequestParam("acc_no") String acc_no) {
        StatementResponse sr = accountService.generateStatement(acc_no);
        return new ResponseEntity<StatementResponse>(sr,HttpStatus.OK);

    }

}
