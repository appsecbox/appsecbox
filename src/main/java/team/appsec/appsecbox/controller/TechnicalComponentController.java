package team.appsec.appsecbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.appsec.appsecbox.controller.request.CreateApplicationMetaRequest;
import team.appsec.appsecbox.controller.request.CreateTechComponentRequest;
import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.domain.application.Component;
import team.appsec.appsecbox.domain.application.TechnicalComponent;
import team.appsec.appsecbox.service.ApplicationService;
import team.appsec.appsecbox.service.ComponentService;
import team.appsec.appsecbox.service.TechnicalComponentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TechnicalComponentController {

    private final ApplicationService applicationService;
    private final ComponentService componentService;
    private final TechnicalComponentService technicalComponentService;

    @PostMapping("/api/application/{applicationId}/component/{componentId}/technical-component/{technicalComponentId}/meta")
    void createTechnicalComponentMeta(@RequestBody CreateApplicationMetaRequest request,
                                      @PathVariable(name = "technicalComponentId") UUID technicalComponentId,
                                      @PathVariable(name = "componentId") UUID componentId,
                                      @PathVariable(name = "applicationId") UUID applicationId) {
        technicalComponentService.addMetaToTechnicalComponent(technicalComponentId, request.key, request.value);
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
}
