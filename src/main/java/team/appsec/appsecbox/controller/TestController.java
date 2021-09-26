package team.appsec.appsecbox.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.appsec.appsecbox.domain.application.*;
import team.appsec.appsecbox.domain.assessment.Assessment;
import team.appsec.appsecbox.domain.assessment.Control;
import team.appsec.appsecbox.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ApplicationService applicationService;
    private final ComponentService componentService;
    private final TechnicalComponentService technicalComponentService;
    private final AssessmentService assessmentService;
    private final UseCaseService useCaseService;
    private final DatasetService datasetService;

    @PostMapping("/api/application")
    Application createApplication(@RequestBody String name) {
        Application app = applicationService.createNewApplication(name);
        return app;
    }

    @GetMapping("/api/application")
    Set<Application> getApplications() {
        return applicationService.getApplications();
    }

    @GetMapping("/api/application/{id}")
    Application getApplicationById(@PathVariable(name = "id") UUID applicationId) {
        return applicationService.getApplicationById(applicationId);
    }

    @PostMapping("/api/application/{applicationId}/component")
    Application createComponent(@RequestBody String name,
                                @PathVariable(name = "applicationId") UUID applicationId) {
        Component component = componentService.createComponent(name);
        Application app = applicationService.getApplicationById(applicationId);
        applicationService.addComponentToApplication(app.getId(), component.getId());
        return applicationService.getApplicationById(app.getId());
    }

    @PostMapping("/api/application/{applicationId}/meta")
    void createApplicationMeta(@RequestBody CreateApplicationMetaRequest request,
                               @PathVariable(name = "applicationId") UUID applicationId) {
        applicationService.addMetaToApplication(applicationId, request.key, request.value);
    }

    @PostMapping("/api/application/{applicationId}/component/{componentId}/meta")
    void createComponentMeta(@RequestBody CreateApplicationMetaRequest request,
                             @PathVariable(name = "componentId") UUID componentId,
                             @PathVariable(name = "applicationId") UUID applicationId) {
        componentService.addMetaToComponent(componentId, request.key, request.value);
    }

    @PostMapping("/api/application/{applicationId}/component/{componentId}/technical-component/{technicalComponentId}/meta")
    void createTechnicalComponentMeta(@RequestBody CreateApplicationMetaRequest request,
                                      @PathVariable(name = "technicalComponentId") UUID technicalComponentId,
                                      @PathVariable(name = "componentId") UUID componentId,
                                      @PathVariable(name = "applicationId") UUID applicationId) {
        technicalComponentService.addMetaToTechnicalComponent(technicalComponentId, request.key, request.value);
    }

    @GetMapping("/api/dictionary/control/type")
    List<Control.Type> getControlTypes() {
        return Arrays.asList(Control.Type.values());
    }
    @GetMapping("/api/dictionary/dataset/category")
    List<Dataset.Category> getDatasetCategories() {
        return Arrays.asList(Dataset.Category.values());
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

    @PostMapping("/api/application/{applicationId}/use-case")
    Application createUseCase(@PathVariable(name = "applicationId") UUID applicationId, @RequestBody CreateUseCaseRequest request) {
        UseCase useCase = useCaseService.createUseCase(request.getName(), request.getActor(), request.getAction(), request.getComponentsIds());
        applicationService.addUseCaseToApplication(applicationId, useCase.getId());
        return applicationService.getApplicationById(applicationId);
    }

    @PostMapping("/api/dataset")
    Dataset createDataset(@RequestBody CreateDatasetRequest request) {
        return datasetService.createDataset(request.getName(), request.getSource(), request.getCategories());
    }

    @PutMapping("/api/application/{applicationId}/component/{componentId}/dataset")
    Application addDatasetToComponent(@PathVariable(name = "applicationId") UUID applicationId,
                                      @PathVariable(name = "componentId") UUID componentId,
                                      @RequestBody AddDatasetToComponentRequest request) {
        componentService.addDatasetToComponent(componentId, request.getDatasetsIds());
        return applicationService.getApplicationById(applicationId);
    }

    @GetMapping("/api/dataset")
    Set<Dataset> getDatasets() {
        return datasetService.getAllDatasets();
    }

    @GetMapping("/api/application/{applicationId}/component/{componentId}")
    Component getComponent(@PathVariable(name = "applicationId") UUID applicationId,
                           @PathVariable(name = "componentId") UUID componentId) {
        return componentService.getComponentById(componentId);
    }

    @PostMapping("/api/assessment")
    Assessment startAssessment(@RequestBody StartAssessmentRequest req) {
        return assessmentService.startAssessment(req.technicalComponents, req.controls);
    }


}
