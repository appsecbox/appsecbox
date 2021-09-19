package team.appsec.appsecbox.service.impl;

import team.appsec.appsecbox.domain.application.TechnicalComponent;
import team.appsec.appsecbox.domain.assessment.Control;
import team.appsec.appsecbox.domain.assessment.Issue;
import team.appsec.appsecbox.domain.assessment.Job;
import team.appsec.appsecbox.service.ControlService;

import java.util.Set;
import java.util.UUID;

public class RestAPIControlService implements ControlService {
    @Override
    public Job start(UUID assessmentId, TechnicalComponent technicalComponent, Control control) {
        return null;
    }

    @Override
    public Job.State getState(UUID jobId) {
        return null;
    }

    @Override
    public Set<Issue> getIssues(UUID jobId) {
        return null;
    }

    @Override
    public void stopJob(UUID jobId) {

    }
}
