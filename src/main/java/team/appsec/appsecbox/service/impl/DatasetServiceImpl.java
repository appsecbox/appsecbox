package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.application.Dataset;
import team.appsec.appsecbox.repository.DatasetRepository;
import team.appsec.appsecbox.service.DatasetService;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;

    @Override
    public Dataset createDataset(String name, String source, Set<Dataset.Category> categories) {
        return datasetRepository.save(
                Dataset.builder()
                        .id(UUID.randomUUID())
                        .name(name)
                        .source(source)
                        .categories( categories )
                        .build()
        );
    }

    @Override
    public Dataset getDatasetById(UUID id) {
        return datasetRepository.findById(id).orElseThrow(DatasetNotFoundException::new);
    }

    @Override
    public Set<Dataset> getAllDatasets() {
        return datasetRepository.findAll().stream().collect(Collectors.toSet());
    }

    public static class DatasetNotFoundException extends RuntimeException{}
}
