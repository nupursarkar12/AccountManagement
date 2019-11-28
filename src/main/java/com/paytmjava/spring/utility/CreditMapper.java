package com.paytmjava.spring.utility;

import com.paytmjava.spring.bean.model.Credit;
import com.paytmjava.spring.bean.dto.CreditDto;
import com.paytmjava.spring.bean.request.CreditRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditMapper {

    CreditDto requestToDto(CreditRequest creditRequest);
    Credit dtoToEntity(CreditDto CreditDto);
    CreditDto EntityToDto(Credit credit);

}
