package by.x1ss.smev.DTO;

import by.x1ss.smev.entity.ResponseQueue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseList {
    private List<ResponseQueue> responses;
}
