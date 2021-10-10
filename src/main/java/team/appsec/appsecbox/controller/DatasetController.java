package team.appsec.appsecbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.appsec.appsecbox.controller.request.AddDatasetToComponentRequest;
import team.appsec.appsecbox.controller.request.CreateDatasetRequest;
import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.domain.application.Dataset;
import team.appsec.appsecbox.service.DatasetService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DatasetController {

    private final DatasetService datasetService;

    @PostMapping("/api/dataset")
    Dataset createDataset(@RequestBody CreateDatasetRequest request) {
        return datasetService.createDataset(request.getName(), request.getSource(), request.getCategories());
    }
    @GetMapping("/api/dataset")
    Set<Dataset> getDatasets() {
        return datasetService.getAllDatasets();
    }


}
