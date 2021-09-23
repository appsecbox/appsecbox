package team.appsec.appsecbox.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;
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
