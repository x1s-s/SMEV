package by.x1ss.smev.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String inn;
    @Column
    private String sts;
    @Column(nullable = false)
    private double accrualAmount;
    @Column(nullable = false)
    private double amountPay;
    @Column(nullable = false)
    private double resolutionNumber;
    @Column(nullable = false)
    private LocalDate resolutionDate;
    @Column(nullable = false)
    private String administrativeCode;
}
