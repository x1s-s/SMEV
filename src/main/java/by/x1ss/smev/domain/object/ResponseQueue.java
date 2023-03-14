package by.x1ss.smev.domain.object;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    public ResponseQueue(Penalty penalty, UUID uuid) {
        this.uuid = uuid;
        this.clientIdentifier = penalty.getClientIdentifier();
        this.isJuridical = penalty.getIsJuridical();
        accrualAmount = penalty.getAccrualAmount();
        amountPay = penalty.getAmountPay();
        resolutionNumber = penalty.getResolutionNumber();
        resolutionDate = penalty.getResolutionDate();
        administrativeCode = penalty.getAdministrativeCode();
    }
}
