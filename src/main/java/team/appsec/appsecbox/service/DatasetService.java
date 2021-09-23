package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.application.Dataset;
import team.appsec.appsecbox.domain.application.Dataset.Category;

import java.util.Set;
import java.util.UUID;

public interface DatasetService {
    Dataset createDataset(String name, String source, Set<Category> categories);
    Dataset getDatasetById(UUID id);
    Set<Dataset> getAllDatasets();
}
