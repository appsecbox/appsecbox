package team.appsec.appsecbox.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.appsec.appsecbox.domain.application.Dataset;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDatasetRequest {
    private String name;
    private String source;
    private Set<Dataset.Category> categories;
}
