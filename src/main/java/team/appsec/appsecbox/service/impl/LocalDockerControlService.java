package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.application.TechnicalComponent;
import team.appsec.appsecbox.domain.assessment.Control;
import team.appsec.appsecbox.domain.assessment.Issue;
import team.appsec.appsecbox.domain.assessment.Job;
import team.appsec.appsecbox.repository.JobRepository;
import team.appsec.appsecbox.service.ControlService;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalDockerControlService implements ControlService {

    private final JobRepository jobRepository;

    @Override
    public Job start(UUID assessmentId, TechnicalComponent technicalComponent, Control control) {
        return jobRepository.save(Job.builder()
                .id(UUID.randomUUID())
                .control(control)
                .state(Job.State.TO_START)
                .technicalComponent(technicalComponent)
                .build());
    }

    @Override
    public Job.State getState(UUID jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        return job.getState();
    }

    @Override
    public Set<Issue> getIssues(UUID jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        return job.getIssues();
    }

    @Override
    public void stopJob(UUID jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        job.setState(Job.State.STOPPED);
        jobRepository.save( job );
    }

    public static class JobNotFoundException extends RuntimeException{}

}
