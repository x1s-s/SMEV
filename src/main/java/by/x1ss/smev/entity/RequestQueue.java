package by.x1ss.smev.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RequestQueue {
    private UUID uuid;
    private String clientIdentifier;
    private Boolean isJuridical;

}
