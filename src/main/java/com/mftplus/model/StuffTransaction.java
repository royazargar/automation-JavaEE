package com.mftplus.model;

import com.mftplus.model.enums.StuffTransactionType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "stuffTransactionEntity")
@Table(name = "stuff_transaction_tbl")
@RequestScoped
public class StuffTransaction {
    @Id
    @SequenceGenerator(name = "stuffTransactionSeq", sequenceName = "stuff_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stuffTransactionSeq")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    private Department department;

    @OneToOne(fetch = FetchType.EAGER)
    private Stuff stuff;

    @Column(name = "ss_dateTime")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.ORDINAL)
    private StuffTransactionType transactionType;
}