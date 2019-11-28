package com.paytmjava.spring.bean.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity(name = "Statement")
@Table(name="Statement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statement {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int statement_id;

    @Column(name="acc_no")
    private String acc_no;

    @Column(name="amount")
    private long amount;

    @Column(name="reference_id")
    private int reference_id;

    @Column(name="type")
    private String type;

    @Column(name="status")
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="create_date")
    private Date create_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="update_date")
    private Date update_date;

}
