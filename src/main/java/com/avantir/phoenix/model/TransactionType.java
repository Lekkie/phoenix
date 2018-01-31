package com.avantir.phoenix.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Entity
@DynamicInsert
@Table(name = "tbl_transaction_types")
@SuppressWarnings("serial")
public class TransactionType extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "code", nullable = false)
    private String code; // 00 = purchase, 01 = cashwithdrawal, etc
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "status", nullable = false)
    private int status = 1;

    public TransactionType(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
