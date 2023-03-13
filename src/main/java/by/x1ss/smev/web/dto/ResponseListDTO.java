package by.x1ss.smev.web.dto;

import by.x1ss.smev.domain.object.ResponseList;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JacksonXmlRootElement(localName = "ResponseList")
public class ResponseListDTO {
    @JacksonXmlProperty(localName = "responses")
    private List<ResponseDTO> responseDTOList;

    public static ResponseListDTO fromResponseList(ResponseList responseList) {
        if(responseList != null) {
            return new ResponseListDTO(responseList);
        } else {
            return null;
        }
    }

    public ResponseListDTO(ResponseList responseList) {
        this.responseDTOList = responseList.getResponses().stream().map(ResponseDTO::new).collect(Collectors.toList());
    }
}
