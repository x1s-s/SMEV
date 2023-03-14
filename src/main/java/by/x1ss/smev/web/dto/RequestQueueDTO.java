package by.x1ss.smev.web.dto;

import by.x1ss.smev.domain.object.RequestQueue;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class RequestQueueDTO {
    private UUID uuid;
    @Pattern(regexp = "^[\\d+]{10}$|^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$",
            message = "Incorrect client identifier in request")
    private String clientIdentifier;
    private Boolean isJuridical;

    public static RequestQueue fromDTO(RequestQueueDTO requestQueueDTO){
        return RequestQueue.builder()
                .uuid(requestQueueDTO.getUuid())
                .clientIdentifier(requestQueueDTO.getClientIdentifier())
                .isJuridical(requestQueueDTO.getIsJuridical())
                .build();
    }
}
