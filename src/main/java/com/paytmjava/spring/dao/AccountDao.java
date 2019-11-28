package com.paytmjava.spring.dao;

import com.paytmjava.spring.bean.model.Account;
import com.paytmjava.spring.bean.model.Credit;
import com.paytmjava.spring.bean.model.Debit;
import com.paytmjava.spring.bean.model.Statement;

import java.util.List;

public interface AccountDao {
    public void saveAccount(Account acc);
    public Account getAccountByAccountNumber(String acc_no);
    public List<Statement> generateStatement(String acc_no);
    public int saveDebit(Debit debit);
    public int saveCredit(Credit credit);
    public void saveStatement(Statement statement);
    public Account getAccountByEmail(String email);
}
