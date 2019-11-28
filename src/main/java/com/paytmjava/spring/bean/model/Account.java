package com.paytmjava.spring.bean.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name="Account")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
    @Id
    private String acc_no;

    @Column(name="customer_name")
    private String customer_name;

    @Column(name="email")
    private String email;

    @Column(name="authorised_limit")
    private long authorized_limit;

    @Column(name="available_limit")
    private long available_limit;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="create_date")
    private Date create_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="update_date")
    private Date update_date;

}
