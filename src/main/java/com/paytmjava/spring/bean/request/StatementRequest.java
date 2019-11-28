package com.paytmjava.spring.bean.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatementRequest {
    private int statement_id;

    @Length(min=11,max=18,message="Account number should have 11 to 18 digits")
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
