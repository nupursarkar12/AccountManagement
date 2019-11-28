package com.paytmjava.spring.utility;

import com.paytmjava.spring.bean.model.Account;
import com.paytmjava.spring.bean.dto.AccountDto;
import com.paytmjava.spring.bean.request.AccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountMapper {
    AccountDto requestToDto(final AccountRequest accountRequest);
    Account dtoToEntity(AccountDto AccountDto);
    AccountDto EntityToDto(Account account);

}
