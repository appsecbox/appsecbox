package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.application.TechnicalComponent;
import team.appsec.appsecbox.domain.assessment.Control;
import team.appsec.appsecbox.domain.assessment.Issue;
import team.appsec.appsecbox.domain.assessment.Job;

import java.util.Set;
import java.util.UUID;

public interface ControlService {
    Job start(UUID assessmentId, TechnicalComponent technicalComponent, Control control);
    Job.State getState(UUID jobId);
    Set<Issue> getIssues(UUID jobId);
    void stopJob(UUID jobId);
}
