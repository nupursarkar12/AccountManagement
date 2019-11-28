package com.paytmjava.spring.bean.responses;

import com.paytmjava.spring.bean.dto.StatementDto;
import com.paytmjava.spring.bean.model.Statement;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatementResponse {
    private String account_id;
    private String message;
    private List<StatementDto> statements;
}
