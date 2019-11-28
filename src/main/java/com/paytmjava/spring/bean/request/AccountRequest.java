package com.paytmjava.spring.bean.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AccountRequest {

    private String acc_no;

    private String customer_name;

    @Email(message = "Please provide a valid email id")
    private String email;

    private long authorized_limit;

    private long available_limit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_date;

}