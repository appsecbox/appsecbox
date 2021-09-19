package team.appsec.appsecbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.domain.application.Component;
import team.appsec.appsecbox.domain.application.TechnicalComponent;
import team.appsec.appsecbox.domain.assessment.Assessment;
import team.appsec.appsecbox.service.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ApplicationService applicationService;
    private final ComponentService componentService;
    private final TechnicalComponentService technicalComponentService;
    private final AssessmentService assessmentService;

    @PostMapping("/api/application")
    Application createApplication(@RequestBody String name) {
        Application app = applicationService.createNewApplication(name);
        return app;
    }

    @GetMapping("/api/application")
    Set<Application> getApplications() {
        return applicationService.getApplications();
    }

    @PostMapping("/api/application/{applicationId}/component")
    Application createComponent(@RequestBody String name,
                                @PathVariable(name = "applicationId") UUID applicationId) {
        Component component = componentService.createComponent(name);
        Application app = applicationService.getApplicationById(applicationId);
        applicationService.addComponentToApplication(app.getId(), component.getId());
        return applicationService.getApplicationById(app.getId());
    }

    @PostMapping("/api/application/{applicationId}/component/{componentId}/technical-component")
    Application createTechComponent(@RequestBody CreateTechComponentRequest req,
                                    @PathVariable(name = "applicationId") UUID applicationId,
                                    @PathVariable(name = "componentId") UUID componentId) {

        TechnicalComponent technicalComponent = technicalComponentService.createTechnicalComponent(req.getUri(), req.getType());
        Component component = componentService.getComponentById(componentId);
        Application app = applicationService.getApplicationById(applicationId);

        componentService.addTechnicalComponentToComponent(component.getId(), technicalComponent.getId());
        applicationService.addComponentToApplication(app.getId(), component.getId());

        return applicationService.getApplicationById(app.getId());
    }

    @PostMapping("/api/assessment")
    Assessment startAssessment(@RequestBody StartAssessmentRequest req) {
        return assessmentService.startAssessment(req.technicalComponents,req.controls);
    }



}
