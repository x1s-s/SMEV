package by.x1ss.smev.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class ResponsePhysical {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
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

    public ResponsePhysical(Answer answer) {
        this.sts = answer.getSts();
        this.accrualAmount = answer.getAccrualAmount();
        this.amountPay = answer.getAmountPay();
        this.resolutionNumber = answer.getResolutionNumber();
        this.resolutionDate = answer.getResolutionDate();
        this.administrativeCode = answer.getAdministrativeCode();
    }
}
