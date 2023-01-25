package by.x1ss.smev.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseJuridical {
    @Id
    private String uuid;
    @Column(nullable = false)
    private String inn;
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
