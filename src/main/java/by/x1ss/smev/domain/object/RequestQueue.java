package by.x1ss.smev.domain.object;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class RequestQueue {
    private UUID uuid;
    private String clientIdentifier;
    private Boolean isJuridical;

}
