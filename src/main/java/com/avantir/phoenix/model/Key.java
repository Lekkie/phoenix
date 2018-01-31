package com.avantir.phoenix.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Entity
@DynamicInsert
@Table(name = "tbl_keys")
@SuppressWarnings("serial")
public class Key extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "mode", nullable = false)
    private Long mode; // 0 = LMK, 1 = ZMK (KEK), 2 = ZPK (KWP), 3 = PVK
    @Column(name = "type", nullable = false)
    private Long type; // 0 = Symmetric (Shared Secret Cryptography), 1 = RSA (Public Key Cryptography)
    @Column(name = "parent_key_id", nullable = true)
    private Long parentKeyId;
    @Column(name = "encrypt_algo_id", nullable = true)
    private Long encryptionAlgoId;
    @Column(name = "value", nullable = false)
    private Long value;
    @Column(name = "check_digit", nullable = false)
    private Long checkDigit;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "status", nullable = false)
    private int status = 1;

    public Key(){

    }
}
