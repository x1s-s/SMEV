package by.x1ss.smev.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseQueue {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;
    @Column(nullable = false)
    private String clientIdentifier;
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
    @Column
    private Boolean isJuridical;

    public ResponseQueue(RequestQueue requestQueue) {
        this.uuid = requestQueue.getUuid();
        this.clientIdentifier = requestQueue.getClientIdentifier();
        this.isJuridical = requestQueue.getIsJuridical();
        double hash = requestQueue.getClientIdentifier().hashCode();
        accrualAmount = hash;
        amountPay = hash;
        resolutionNumber = hash;
        resolutionDate = LocalDate.MAX;
        administrativeCode = hash + "";
    }
}
