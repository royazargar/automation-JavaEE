package com.mftplus.model;

import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianDateTime;
import com.mftplus.model.enums.FinancialTransactionType;
import com.mftplus.model.enums.PaymentType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@RequestScoped
@Entity(name = "financialTransactionEntity")
@Table(name = "financial_transaction_tbl")
public class FinancialTransaction extends Base{
    @Id
    @SequenceGenerator(name = "financialTransactionSeq", sequenceName = "financial_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialTransactionSeq")
    @Column(name = "financialTransaction_id",length = 20)
    private Long id;

    @Column(name ="financialTransaction_dateTime")
//    @PastOrPresent(message = "Invalid Date")
    private LocalDate dateTime; //تاریخ

    @OneToOne
    private User user; // پرداخت کننده یا دریافت کننده

    @OneToOne
    private Department referringDepartment; // واحد ارجاع کننده

    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private CardPayment cardPayment;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private CheckPayment checkPayment;

//    @Pattern(regexp = "^{1,15}$",message = "Invalid Amount")
    @Column(name ="financialTransaction_amount" ,length =15)
    private Long amount; // مقدار پول معامله شده

//    @Pattern(regexp = "^{1,20}$",message = "Invalid Tracking Code")
    @Column(name = "financialTransaction_trackingCode",length = 20,unique = true)
    private int trackingCode; // کد تراکنش

    @Enumerated(EnumType.ORDINAL)
    private FinancialTransactionType transactionType;

    @Transient
    private String faDateTime;

    public String getFaDateTime() {
        return  String.valueOf(PersianDate.fromGregorian(dateTime));
    }

    public void setFaDateTime(String faDateTime) {
        this.dateTime =PersianDate.parse(faDateTime).toGregorian();
    }
}