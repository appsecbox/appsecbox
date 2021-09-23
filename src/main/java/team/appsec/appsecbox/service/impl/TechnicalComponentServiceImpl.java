package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.application.TechnicalComponent;
import team.appsec.appsecbox.domain.assessment.Control;
import team.appsec.appsecbox.repository.TechnicalComponentRepository;
import team.appsec.appsecbox.service.TechnicalComponentService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TechnicalComponentServiceImpl implements TechnicalComponentService {

    private final TechnicalComponentRepository technicalComponentRepository;

    @Override
    public TechnicalComponent createTechnicalComponent( String uri, Control.Type type) {
        return technicalComponentRepository.save(
                TechnicalComponent.builder()
                        .id(UUID.randomUUID())
                        .uri(uri)
                        .type(type)
                        .build()
        );
    }

    @Override
    public TechnicalComponent getTechnicalComponentById(UUID technicalComponentId) {
        return technicalComponentRepository.findById(technicalComponentId).orElseThrow(TechnicalComponentNotFoundException::new);
    }

    @Override
    public void addMetaToTechnicalComponent(UUID technicalComponentId, String key, String value) {
        TechnicalComponent technicalComponent = getTechnicalComponentById(technicalComponentId);
        Map<String,String> meta = technicalComponent.getMeta();
        if(meta==null){
            meta = new HashMap<>();
        }
        meta.put(key,value);
        technicalComponent.setMeta(meta);
        technicalComponentRepository.save(technicalComponent);
    }

    @Override
    public void destroyTechnicalComponent(UUID technicalComponentId) {
        technicalComponentRepository.deleteById(technicalComponentId);
    }


    public static class TechnicalComponentNotFoundException extends RuntimeException{}
}
