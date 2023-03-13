package by.x1ss.smev.web.dto;

import by.x1ss.smev.domain.object.ResponseQueue;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResponseDTO {
    private String clientIdentifier;
    private double accrualAmount;
    private double amountPay;
    private double resolutionNumber;
    private LocalDate resolutionDate;
    private String administrativeCode;
    private Boolean isJuridical;

    public ResponseDTO(ResponseQueue responseQueue) {
        this.clientIdentifier = responseQueue.getClientIdentifier();
        this.isJuridical = responseQueue.getIsJuridical();
        accrualAmount = responseQueue.getAccrualAmount();
        amountPay = responseQueue.getAmountPay();
        resolutionNumber = responseQueue.getResolutionNumber();
        resolutionDate = responseQueue.getResolutionDate();
        administrativeCode = responseQueue.getAdministrativeCode();
    }
}
