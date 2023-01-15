package by.x1ss.smev.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class RequestQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String str;
    @Column(nullable = false)
    private Boolean isJuridical;

    public RequestQueue() {
    }
}
