package by.x1ss.smev.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResponseQueue {
    private UUID uuid;
    private String clientIdentifier;
    private double accrualAmount;
    private double amountPay;
    private double resolutionNumber;
    private LocalDate resolutionDate;
    private String administrativeCode;
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
