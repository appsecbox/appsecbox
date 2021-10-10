package team.appsec.appsecbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.appsec.appsecbox.controller.request.AddDatasetToComponentRequest;
import team.appsec.appsecbox.controller.request.CreateApplicationMetaRequest;
import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.domain.application.Component;
import team.appsec.appsecbox.service.ApplicationService;
import team.appsec.appsecbox.service.ComponentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ComponentController {

    private final ApplicationService applicationService;
    private final ComponentService componentService;

    @PostMapping("/api/application/{applicationId}/component")
    Application createComponent(@RequestBody String name,
                                @PathVariable(name = "applicationId") UUID applicationId) {
        Component component = componentService.createComponent(name);
        Application app = applicationService.getApplicationById(applicationId);
        applicationService.addComponentToApplication(app.getId(), component.getId());
        return applicationService.getApplicationById(app.getId());
    }

    @PostMapping("/api/application/{applicationId}/component/{componentId}/meta")
    void createComponentMeta(@RequestBody CreateApplicationMetaRequest request,
                             @PathVariable(name = "componentId") UUID componentId,
                             @PathVariable(name = "applicationId") UUID applicationId) {
        componentService.addMetaToComponent(componentId, request.key, request.value);
    }

    @GetMapping("/api/application/{applicationId}/component/{componentId}")
    Component getComponent(@PathVariable(name = "applicationId") UUID applicationId,
                           @PathVariable(name = "componentId") UUID componentId) {
        return componentService.getComponentById(componentId);
    }

    @PutMapping("/api/application/{applicationId}/component/{componentId}/dataset")
    Application addDatasetToComponent(@PathVariable(name = "applicationId") UUID applicationId,
                                      @PathVariable(name = "componentId") UUID componentId,
                                      @RequestBody AddDatasetToComponentRequest request) {
        componentService.addDatasetToComponent(componentId, request.getDatasetsIds());
        return applicationService.getApplicationById(applicationId);
    }
}
