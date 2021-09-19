package team.appsec.appsecbox.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartAssessmentRequest{
    Set<UUID> technicalComponents;
    Set<String> controls;
}