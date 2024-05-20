package com.mftplus.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "bankEntity")
@Table(name = "bank_tbl")
@RequestScoped
public class Bank extends Base implements Serializable {

    @Id
    @SequenceGenerator(name = "bankSeq", sequenceName = "bank_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSeq")
    @Column(name = "bank_id", length = 20)
    private Long id;

    @Column(name = "bank_name", columnDefinition = "NVARCHAR2(20)")
//    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Name")
//    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
//    @NotBlank(message = "Should Not Be Null")
    private String name;// نام بانک

    @Column(name = "bank_accountNumber", columnDefinition = "NVARCHAR2(16)")
//    @Pattern(regexp = "^[0-9]{16}$", message = "Invalid Account Number")
//    @Size(min = 16, max = 16, message = " Account Number must be 16 characters")
//    @NotBlank(message = "Should Not Be Null")
    private String accountNumber;// شماره حساب

    @Column(name = "bank_branchCode", columnDefinition = "NVARCHAR2(5)")
//    @Pattern(regexp = "^[0-9]{1,5}$", message = "Invalid Branch Code")
//    @Size(min = 1, max = 5, message = " Branch Code must be between 1 and 5 characters")
//    @NotBlank(message = "Should Not Be Null")
    private int branchCode;// کد شعبه

    @Column(name = "bank_branchName", columnDefinition = "NVARCHAR2(20)")
//    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Branch Name")
//    @Size(min = 1, max = 20, message = " Branch Name must be between 1 and 20 characters")
//    @NotBlank(message = "Should Not Be Null")
    private String branchName;// نام شعبه

    @Column(name = "bank_accountType", columnDefinition = "NVARCHAR2(20)")
    private String accountType;// نوع حساب بانکی

    @Column(name = "bank_accountBalance", columnDefinition = "NVARCHAR2(15)")
//    @Pattern(regexp = "^[0-9]{1,15}$", message = "Invalid Account Balance")
//    @Size(min = 1, max = 15, message = " Account Balance must be between 1 and 15 characters")
//    @NotBlank(message = "Should Not Be Null")
    private Long accountBalance;// موجودی حساب

}


