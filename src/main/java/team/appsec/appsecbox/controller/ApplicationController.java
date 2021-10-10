package team.appsec.appsecbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.appsec.appsecbox.controller.request.CreateApplicationMetaRequest;
import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.service.ApplicationService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/api/application/{id}")
    Application getApplicationById(@PathVariable(name = "id") UUID applicationId) {
        return applicationService.getApplicationById(applicationId);
    }

    @GetMapping("/api/application")
    Set<Application> getApplications() {
        return applicationService.getApplications();
    }

    @PostMapping("/api/application")
    Application createApplication(@RequestBody String name) {
        Application app = applicationService.createNewApplication(name);
        return app;
    }
    
    @PostMapping("/api/application/{applicationId}/meta")
    void createApplicationMeta(@RequestBody CreateApplicationMetaRequest request,
                               @PathVariable(name = "applicationId") UUID applicationId) {
        applicationService.addMetaToApplication(applicationId, request.key, request.value);
    }
}
