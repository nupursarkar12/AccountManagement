package com.paytmjava.spring.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountDto {

    private String acc_no;

    private String customer_name;

    private String email;

    private long authorized_limit;

    private long available_limit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_date;

}