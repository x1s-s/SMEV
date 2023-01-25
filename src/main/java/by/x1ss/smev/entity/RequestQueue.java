package by.x1ss.smev.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RequestQueue {
    @Id
    private String uuid;
    @Column(nullable = false)
    private String str;
    @Column(nullable = false)
    private Boolean isJuridical;

}
