package geek.prtns.geek_prtns.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeDTO {

    private Long id;

    private String code;

    private LocalDateTime createAt;
}
