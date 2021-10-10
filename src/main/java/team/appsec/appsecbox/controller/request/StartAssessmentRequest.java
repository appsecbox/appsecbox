package team.appsec.appsecbox.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartAssessmentRequest{
    public Set<UUID> technicalComponents;
    public Set<String> controls;
}