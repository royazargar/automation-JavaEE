package com.mftplus.model;

import com.github.mfathi91.time.PersianDate;
import com.mftplus.model.enums.FinancialTransactionType;
import com.mftplus.model.enums.PaymentType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


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
    private LocalDate date; //تاریخ

    @ManyToOne(fetch = FetchType.EAGER)
    private User user; // پرداخت کننده یا دریافت کننده

    @ManyToOne
    private Department referringDepartment; // واحد ارجاع کننده

    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

//    @Pattern(regexp = "^{1,15}$",message = "Invalid Amount")
    @Column(name ="financialTransaction_amount" ,length =15)
    private Long amount; // مقدار پول معامله شده

//    @Pattern(regexp = "^{1,20}$",message = "Invalid Tracking Code")
    @Column(name = "financialTransaction_trackingCode",length = 20,unique = true)
    private int trackingCode; // کد تراکنش

    @Enumerated(EnumType.ORDINAL)
    private FinancialTransactionType transactionType;

    @Transient
    private String faDate;

    public String getFaDate() {
        return String.valueOf(PersianDate.fromGregorian(date));
    }

    public void setFaDate(String faDate) {
        this.date =PersianDate.parse(faDate).toGregorian();
    }
}