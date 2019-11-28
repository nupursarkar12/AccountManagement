package com.paytmjava.spring.bean.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Table(name="Debit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Debit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int debit_id;

    @Column(name="acc_no")
    private String acc_no;

    @Column(name="amount")
    private long amount;

    @Column(name="order_id")
    private int order_id;

    @Column(name="status")
    private int status;

    @Column(name="descptn")
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="create_date")
    private Date create_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="update_date")
    private Date update_date;

}
