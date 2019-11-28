package com.paytmjava.spring.dao;

import com.paytmjava.spring.bean.model.Account;
import com.paytmjava.spring.bean.model.Credit;
import com.paytmjava.spring.bean.model.Debit;
import com.paytmjava.spring.bean.model.Statement;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AccountDaoImp implements AccountDao {
    @Autowired
    private SessionFactory sessionFactory;
    static org.apache.log4j.Logger logger = Logger.getLogger(AccountDaoImp.class);

//    static int debit_id=0;
//    static int credit_id=0;
  //  static int order_id=0;
//    static int statement_id=0;

    @PersistenceContext
    EntityManager entityManager;

    public void saveAccount(Account acc){
        Session session = sessionFactory.getCurrentSession();
        session.save(acc);

    }
    public Account getAccountByAccountNumber(String acc_no){
        Session session = sessionFactory.getCurrentSession();
        return (Account) session.get(Account.class,acc_no);
    }


    public List<Statement> generateStatement(String acc_no){
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Statement> criteriaQuery = criteriaBuilder.createQuery(Statement.class);
            Root<Statement> rootTable = criteriaQuery.from(Statement.class);
            Predicate accountPredicate = criteriaBuilder.equal(rootTable.get("acc_no"), acc_no);
            criteriaQuery.where(accountPredicate);
            TypedQuery<Statement> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }catch(NoResultException exp){
            return null;
        }
    }

    public Account getAccountByEmail(String email){
        try {
            logger.info("Start of getAccountByMail for : " + email);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
            Root<Account> rootTable = criteriaQuery.from(Account.class);
            Predicate accountPredicate = criteriaBuilder.equal(rootTable.get("email"), email);
            criteriaQuery.where(accountPredicate);
            TypedQuery<Account> query = entityManager.createQuery(criteriaQuery);
            logger.info(" Account by mail " + email + " = " + query.getSingleResult());
            return query.getSingleResult();
        }catch(NoResultException exp){
            logger.info("**************************************"+exp.fillInStackTrace());
            return null;
        }
    }

    public int saveDebit(Debit debit) {
        Session session = sessionFactory.getCurrentSession();
        session.save(debit);
        return debit.getDebit_id();
    }

    public int saveCredit(Credit credit) {
        Session session = sessionFactory.getCurrentSession();
        session.save(credit);
        return credit.getCredit_id();
    }

    public void saveStatement(Statement statement){
        Session session = sessionFactory.getCurrentSession();
        session.save(statement);
    }


}
