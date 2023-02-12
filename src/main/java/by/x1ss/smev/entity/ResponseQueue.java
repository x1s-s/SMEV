package by.x1ss.smev.entity;

import lombok.*;

import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[\\d+]{10}$|^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$",
        message = "Incorrect client identifier in response")
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
