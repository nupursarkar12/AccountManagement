package com.paytmjava.spring.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CreditDto {

    private int credit_id;

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
