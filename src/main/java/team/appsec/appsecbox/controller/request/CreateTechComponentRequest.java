package team.appsec.appsecbox.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.appsec.appsecbox.domain.assessment.Control;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTechComponentRequest {
    private String uri;
    private Control.Type type;
}
