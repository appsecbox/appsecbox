package team.appsec.appsecbox.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.appsec.appsecbox.controller.request.CreateUseCaseRequest;
import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.domain.application.UseCase;
import team.appsec.appsecbox.service.ApplicationService;
import team.appsec.appsecbox.service.UseCaseService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UseCaseController {

    private final ApplicationService applicationService;
    private final UseCaseService useCaseService;

    @PostMapping("/api/application/{applicationId}/use-case")
    Application createUseCase(@PathVariable(name = "applicationId") UUID applicationId, @RequestBody CreateUseCaseRequest request) {
        UseCase useCase = useCaseService.createUseCase(request.getName(), request.getActor(), request.getAction(), request.getComponentsIds());
        applicationService.addUseCaseToApplication(applicationId, useCase.getId());
        return applicationService.getApplicationById(applicationId);
    }
}
