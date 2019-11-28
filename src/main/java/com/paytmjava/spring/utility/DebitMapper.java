package com.paytmjava.spring.utility;
import com.paytmjava.spring.bean.dto.DebitDto;
import com.paytmjava.spring.bean.model.Debit;
import com.paytmjava.spring.bean.request.DebitRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebitMapper {

    DebitDto requestToDto(DebitRequest debitRequest);
    Debit dtoToEntity(DebitDto debitDto);
    DebitDto EntityToDto(Debit debit);
}
