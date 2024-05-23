package com.mftplus.model;

import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianDateTime;
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
@Entity(name = "financialDocEntity")
@Table(name = "financial_doc_tbl")
public class FinancialDoc extends Base{
    @Id
    @SequenceGenerator(name = "financialDocSeq", sequenceName = "financial_doc_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocSeq")
    @Column(name = "financialDoc_id",length = 20)
    private Long id;

//    @Pattern(regexp = "^{1,5}$",message = "Invalid Doc Number")
    @Column(name ="financialDoc_docNumber" ,length =5, unique = true)
    private Long docNumber;//شماره سند

    @Column(name ="financialDoc_dateTime")
//    @PastOrPresent(message = "Invalid Date")
    private LocalDate date;//تاریخ

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Description")
    @Column(name ="financialDoc_description" ,length =20 )
    private String description; //بابت

    @OneToOne
    private FinancialTransaction financialTransaction;

    @Transient
    private String faDate;

    public String getFaDate() {
        return String.valueOf(PersianDate.fromGregorian(date));
    }

    public void setFaDate(String faDate) {
        this.date =PersianDate.parse(faDate).toGregorian();
    }
}