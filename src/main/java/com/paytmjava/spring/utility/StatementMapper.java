package com.paytmjava.spring.utility;
import com.paytmjava.spring.bean.dto.StatementDto;
import com.paytmjava.spring.bean.model.Statement;
import com.paytmjava.spring.bean.request.StatementRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatementMapper {
    StatementDto requestToDto(StatementRequest statementRequest);
    Statement dtoToEntity(StatementDto statementDto);
    StatementDto EntityToDto(Statement statement);
}
