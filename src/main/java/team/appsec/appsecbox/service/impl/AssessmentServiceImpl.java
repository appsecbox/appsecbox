package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.application.TechnicalComponent;
import team.appsec.appsecbox.domain.assessment.Assessment;
import team.appsec.appsecbox.domain.assessment.Control;
import team.appsec.appsecbox.domain.assessment.Issue;
import team.appsec.appsecbox.repository.AssessmentRepository;
import team.appsec.appsecbox.service.AssessmentService;
import team.appsec.appsecbox.service.ControlOrchestratorService;
import team.appsec.appsecbox.service.ControlService;
import team.appsec.appsecbox.service.TechnicalComponentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final ControlOrchestratorService controlOrchestratorService;
    private final ControlService controlService;
    private final TechnicalComponentService technicalComponentService;
    private final AssessmentRepository assessmentRepository;

    @Override
    public Assessment startAssessment(Set<UUID> technicalComponents, Set<String> controls) {
        Set<TechnicalComponent> technicalComponentsList = technicalComponents.stream()
                .map(technicalComponentService::getTechnicalComponentById)
                .collect(Collectors.toSet());

        Set<Control> controlsList = controls.stream()
                .map(controlOrchestratorService::getControlByName)
                .collect(Collectors.toSet());

        UUID assessmentId = UUID.randomUUID();
        return assessmentRepository.save(
                Assessment.builder()
                .id(assessmentId)
                .jobs(
                        technicalComponentsList.stream()
                                .flatMap( tc -> {
                                    return controlsList.stream()
                                            .filter(
                                                    c -> {
                                                        return c.getType().equals(tc.getType());
                                                    }
                                            )
                                            .map( c -> {
                                                return controlService.start(assessmentId,tc,c);
                                            } );
                                } )
                                .collect(Collectors.toSet())
                )
                .build());
    }


    @Override
    public Assessment getAssessmentById(UUID id) {
        return null;
    }

    @Override
    public Set<Issue> getIssues(UUID assessmentId) {
        return null;
    }

    @Override
    public Assessment.State getState(UUID assessmentId) {
        return null;
    }

    @Override
    public Assessment stopAssessment(UUID assessmentId) {
        return null;
    }

    public static class AssessmentNotFoundException extends RuntimeException {
    }
}
