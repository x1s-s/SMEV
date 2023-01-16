package by.x1ss.smev.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
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

    public ResponsePhysical() {
    }

}
