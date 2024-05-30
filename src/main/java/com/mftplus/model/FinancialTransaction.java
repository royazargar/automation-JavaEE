package com.mftplus.model;

import com.github.mfathi91.time.PersianDate;
import com.mftplus.model.enums.FinancialTransactionType;
import com.mftplus.model.enums.PaymentType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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

@Entity(name = "financialTransactionEntity")
@Table(name = "financial_transaction_tbl")
@RequestScoped
public class FinancialTransaction extends Base {

    @Id
    @SequenceGenerator(name = "financialTransactionSeq", sequenceName = "financial_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialTransactionSeq")
    @Column(name = "financialTransaction_id", length = 20)
    private Long id;

    @Column(name = "financialTransaction_dateTime")
    @PastOrPresent(message = "Invalid Date")
    private LocalDate date; //تاریخ

    @ManyToOne
    private User user; // پرداخت کننده یا دریافت کننده

    @ManyToOne
    private Department referringDepartment; // واحد ارجاع کننده

    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

    @Column(name = "financialTransaction_amount", length = 10)
    @Positive(message = "The amount must be a positive number.")
    @Min(value = 1, message = "The amount must be at least 1.")
    @Max(value = 1999999999, message = "The amount cannot exceed 1999999999.")
    private Long amount; // مقدار پول معامله شده

    @Column(name = "financialTransaction_trackingCode", length = 20, unique = true)
    private int trackingCode; // کد تراکنش

    @Enumerated(EnumType.ORDINAL)
    private FinancialTransactionType transactionType;

    @Transient
    private String faDate;

    public String getFaDate() {
        return String.valueOf(PersianDate.fromGregorian(date));
    }

    public void setFaDate(String faDate) {
        this.date = PersianDate.parse(faDate).toGregorian();
    }
}