package com.paytmjava.spring.bean.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditRequest {
    private int credit_id;

    @NotNull
    @Size(min=11,max=18,message="Account number should have 11 to 18 digits")
    private String acc_no;

    @NotNull
    private long amount;

    private int order_id;

    private int status;

    @NotNull
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date create_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date update_date;
}

