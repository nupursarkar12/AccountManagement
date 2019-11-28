package com.paytmjava.spring.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatementDto {
    private int statement_id;

    private String acc_no;

    private long amount;

    private int reference_id;

    private String type;

    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date create_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date update_date;

}
