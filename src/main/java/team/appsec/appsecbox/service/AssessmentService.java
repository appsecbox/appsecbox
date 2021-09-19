package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.assessment.Assessment;
import team.appsec.appsecbox.domain.assessment.Issue;

import java.util.Set;
import java.util.UUID;

public interface AssessmentService {
    Assessment startAssessment(Set<UUID> technicalComponents, Set<String> controls);
    Assessment getAssessmentById(UUID id);
    Set<Issue> getIssues(UUID assessmentId);
    Assessment.State getState(UUID assessmentId);
    Assessment stopAssessment(UUID assessmentId);
}
