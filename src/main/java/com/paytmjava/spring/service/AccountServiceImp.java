package com.paytmjava.spring.service;


import com.paytmjava.spring.bean.dto.AccountDto;
import com.paytmjava.spring.bean.dto.CreditDto;
import com.paytmjava.spring.bean.dto.DebitDto;
import com.paytmjava.spring.bean.dto.StatementDto;
import com.paytmjava.spring.bean.model.Account;
import com.paytmjava.spring.bean.model.Credit;
import com.paytmjava.spring.bean.model.Debit;
import com.paytmjava.spring.bean.model.Statement;
import com.paytmjava.spring.bean.responses.AccountGenerationResponse;
import com.paytmjava.spring.bean.responses.AccountResponse;
import com.paytmjava.spring.bean.responses.CommonResponse;
import com.paytmjava.spring.bean.responses.StatementResponse;
import com.paytmjava.spring.dao.AccountDao;
import com.paytmjava.spring.utility.AccountMapper;
import com.paytmjava.spring.utility.CreditMapper;
import com.paytmjava.spring.utility.DebitMapper;
import com.paytmjava.spring.utility.StatementMapper;
import org.apache.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    @Autowired
    AccountDao accountDao;
    AccountMapper accountMapper= Mappers.getMapper(AccountMapper.class);
    DebitMapper debitMapper= Mappers.getMapper(DebitMapper.class);
    CreditMapper creditMapper= Mappers.getMapper(CreditMapper.class);
    StatementMapper statementMapper= Mappers.getMapper(StatementMapper.class);
    static int order_id=0;
    public final static String DEBIT_ENTRY_SUCCESSFUL="Debit done successfully";
    public final static String INVALID_ACCOUNT="Account does not exist";
    public final static String DEBIT_ENTRY_FAILURE_WHEN_AVAILABLE_LIMIT_INSUFFICIENT="Insufficient available balance";
    public final static String CREDIT_ENTRY_SUCCESSFUL="Credit done successfully";
 //   public final static String CREDIT_ENTRY_FAILURE_WHEN_INVALID_ACCOUNT="Account does not exist";
    public final static String ACCOUNT_CREATED="Account created successfully";
    public final static String ACCOUNT_NOT_CREATED="Account with same email already exists";
    public final static String ACCOUNT_FETCHED="Account fetched successfully";

    static org.apache.log4j.Logger logger = Logger.getLogger(AccountServiceImp.class);

    @Override
    public AccountGenerationResponse createAccount(AccountDto accountDto) {
        try {

            if (accountDao.getAccountByEmail(accountDto.getEmail()) == null) {
                Random r = new Random();
                long acc_no1 = 10000000000L + (long) (r.nextDouble() * 9999999999L);
                String st2 = String.valueOf(acc_no1);
                accountDto.setAcc_no(st2);
                accountDto.setAvailable_limit(0);
                accountDto.setCreate_date(Calendar.getInstance().getTime());
                accountDto.setUpdate_date(Calendar.getInstance().getTime());
                final Account account= accountMapper.dtoToEntity(accountDto);
                accountDao.saveAccount(account);
                logger.info("Inside if");
                return AccountGenerationResponse.builder().message(ACCOUNT_CREATED).acc_no(st2).build();
            } else {
                logger.info("inside else");
                return AccountGenerationResponse.builder().message(ACCOUNT_NOT_CREATED).acc_no(accountDao.getAccountByEmail(accountDto.getEmail()).getAcc_no()).build();
            }
        }
        catch (Exception exp) {
            logger.error("inside catch" + exp.fillInStackTrace());
            return AccountGenerationResponse.builder().message(exp.getMessage()).build();
        }

    }

    @Override
    public AccountResponse getAccount(String acc_no){
        try {
            if (accountDao.getAccountByAccountNumber(acc_no) == null) {
                return AccountResponse.builder().message(INVALID_ACCOUNT).build();
            } else {
                AccountDto accountDto = accountMapper.EntityToDto(accountDao.getAccountByAccountNumber(acc_no));
                return AccountResponse.builder().message(ACCOUNT_FETCHED).accountDto(accountDto).build();
            }
        }
        catch (Exception exp) {
            return AccountResponse.builder().message(exp.getMessage()).build();
        }
    }

    @Override
    public CommonResponse debitEntry(DebitDto debitDto){

        try {
            Account userAccount = accountDao.getAccountByAccountNumber(debitDto.getAcc_no());
            logger.info("****************888888888"+userAccount.getAcc_no());
            if(userAccount != null)
            {
                if(userAccount.getAvailable_limit()>debitDto.getAmount())
                {
                    Statement st = new Statement();
                    debitDto.setOrder_id(++order_id);
                    st.setReference_id(order_id);
                    st.setAcc_no(debitDto.getAcc_no());
                    st.setAmount(debitDto.getAmount());
                    st.setType("Debit");
                    userAccount.setAvailable_limit(userAccount.getAvailable_limit()-debitDto.getAmount());
                    debitDto.setStatus(1);
                    st.setStatus("Success");
                    debitDto.setCreate_date(Calendar.getInstance().getTime());
                    debitDto.setUpdate_date(Calendar.getInstance().getTime());
                    st.setCreate_date(Calendar.getInstance().getTime());
                    st.setUpdate_date(Calendar.getInstance().getTime());
                    userAccount.setUpdate_date(Calendar.getInstance().getTime());
                    final Debit debit= debitMapper.dtoToEntity(debitDto);
                    int debit_id=accountDao.saveDebit(debit);
                    accountDao.saveStatement(st);
                    accountDao.saveAccount(userAccount);
                    return CommonResponse.builder().message(DEBIT_ENTRY_SUCCESSFUL).id(debit_id).build();
                }
                else{
                    return CommonResponse.builder().message(DEBIT_ENTRY_FAILURE_WHEN_AVAILABLE_LIMIT_INSUFFICIENT).build();
                }

            }
            else {
                return CommonResponse.builder().message(INVALID_ACCOUNT).build();
            }
        } catch (Exception exp){
           return CommonResponse.builder().message(exp.getMessage()).build();
        }
    }
    @Override
    public CommonResponse creditEntry(CreditDto creditDto) {
        logger.info("inside function");
        Account userAccount = accountDao.getAccountByAccountNumber(creditDto.getAcc_no());
        logger.info("*********************"+userAccount);
        try {

            if (userAccount != null) {
                Statement st = new Statement();
                creditDto.setOrder_id(++order_id);
                st.setReference_id(order_id);
                st.setAcc_no(creditDto.getAcc_no());
                st.setAmount(creditDto.getAmount());
                st.setType("Credit");
                userAccount.setAvailable_limit(userAccount.getAvailable_limit() + creditDto.getAmount());
                creditDto.setStatus(1);
                st.setStatus("Success");
                creditDto.setCreate_date(Calendar.getInstance().getTime());
                creditDto.setUpdate_date(Calendar.getInstance().getTime());
                st.setCreate_date(Calendar.getInstance().getTime());
                st.setUpdate_date(Calendar.getInstance().getTime());
                userAccount.setUpdate_date(Calendar.getInstance().getTime());
                final Credit credit= creditMapper.dtoToEntity(creditDto);
                int credit_id=accountDao.saveCredit(credit);
                accountDao.saveStatement(st);
                accountDao.saveAccount(userAccount);
                return CommonResponse.builder().message(CREDIT_ENTRY_SUCCESSFUL).id(credit_id).build();
            } else {
                return CommonResponse.builder().message(INVALID_ACCOUNT).build();
            }

        }
        catch (Exception exp){
            return CommonResponse.builder().message(exp.getMessage()).build();
        }
    }
    @Override
    public StatementResponse generateStatement(String acc_no){
        try {
            if (accountDao.generateStatement(acc_no) == null) {
                return StatementResponse.builder().message(INVALID_ACCOUNT).build();
            } else {
                List<Statement> list=accountDao.generateStatement(acc_no);
                Iterator iterator = list.iterator();
                List<StatementDto> listDto = new ArrayList<StatementDto>() ;

                while(iterator.hasNext())
                {
                    StatementDto statementDto=statementMapper.EntityToDto((Statement)iterator.next());
                    listDto.add(statementDto);
                }

                return StatementResponse.builder().statements(listDto).build();
            }
        }
        catch (Exception exp) {
            return StatementResponse.builder().message(exp.getMessage()).build();
        }

    }
}
