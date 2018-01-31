package com.avantir.phoenix.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lekanomotayo on 01/01/2018.
 *
 * Encryption Algo
 * e.g
 * ISO-0,ISO-1,IBM,Visa PIN block
 */

@Entity
@DynamicInsert
@Table(name = "tbl_encrypt_algorithm")
@SuppressWarnings("serial")
public class EncryptAlgorithm extends BaseModel implements Serializable {

    // https://www.eftlab.co.uk/index.php/site-map/knowledge-base/261-complete-list-of-pin-blocks-in-payments
    // http://www.toptenreviews.com/software/articles/encryption-algorithms/
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "format", nullable = false, unique = true)
    private String format; //ISO-0, ISO-1
    @Column(name = "algorithm", nullable = false, unique = true)
    private String algorithm; // DES,3DES,AES,Blowfish,HMAC,SHA256, TwoFish
    @Column(name = "mode_id", nullable = false)
    private Long modeId; // 0 = LMK, 1 = ZMK (KEK), 2 = ZPK (KWP), 3 = PVK
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "status", nullable = false)
    private int status = 1;

    public EncryptAlgorithm(){

    }


}
