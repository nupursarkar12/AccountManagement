package com.paytmjava.spring.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DebitDto {
    private int debit_id;

    private String acc_no;

    private long amount;

    private int order_id;

    private int status;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date create_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date update_date;

}
