package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.application.*;
import team.appsec.appsecbox.repository.ComponentRepository;
import team.appsec.appsecbox.service.ComponentService;
import team.appsec.appsecbox.service.TechnicalComponentService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;
    private final TechnicalComponentService technicalComponentService;

    @Transactional
    @Override
    public Component createComponent(String name) {
        return componentRepository.save( Component.builder()
                .id(UUID.randomUUID())
                .name(name)
                .build() );
    }

    @Transactional
    @Override
    public Component getComponentById(UUID componentId) {
        return componentRepository.findById(componentId).orElseThrow(ComponentNotFoundException::new);
    }

    @Override
    public Set<Component> getComponentsByIds(Set<UUID> componentsIds) {
        return componentRepository.findAllById(componentsIds).stream().collect(Collectors.toSet());
    }

    @Override
    public void addTechnicalComponentToComponent(UUID componentId, UUID technicalComponentId) {
        Component component = getComponentById(componentId);
        TechnicalComponent technicalComponent = technicalComponentService.getTechnicalComponentById(technicalComponentId);
        Set<TechnicalComponent> technicalComponents = component.getTechnicalComponents();
        if(technicalComponents==null){
            technicalComponents = new HashSet<>();
        }
        technicalComponents.add(technicalComponent);
        component.setTechnicalComponents(technicalComponents);
        componentRepository.save(component);
    }

    @Override
    public void addDatasetToComponent(UUID componentId, UUID datasetId) {

    }

    @Override
    public void addMetaToComponent(UUID componentId, String key, String value) {
        Component component = getComponentById(componentId);
        Map<String,String> meta = component.getMeta();
        if(meta==null){
            meta = new HashMap<>();
        }
        meta.put(key,value);
        component.setMeta(meta);
        componentRepository.save(component);
    }

    @Override
    public void removeTechnicalComponentFromComponent(UUID componentId, UUID technicalComponentId) {
        Component component = getComponentById(componentId);
        Set<TechnicalComponent> technicalComponents = component.getTechnicalComponents();
        if(technicalComponents==null){
            return;
        }
        Set<TechnicalComponent> newTechnicalComponents = technicalComponents.stream()
                .filter( t -> {
                    return !t.getId().equals(technicalComponentId);
                })
                .collect(Collectors.toSet());
        component.setTechnicalComponents(newTechnicalComponents);
        componentRepository.save(component);
    }

    @Override
    public void destroyComponent(UUID componentId) {
        componentRepository.deleteById(componentId);
    }


    public static class ComponentNotFoundException extends RuntimeException {}
}
