package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.application.Component;
import team.appsec.appsecbox.domain.application.UseCase;
import team.appsec.appsecbox.repository.UseCaseRepository;
import team.appsec.appsecbox.service.ComponentService;
import team.appsec.appsecbox.service.UseCaseService;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UseCaseServiceImpl implements UseCaseService {

    private final UseCaseRepository useCaseRepository;
    private final ComponentService componentService;

    @Override
    public UseCase createUseCase(String name, String actor, String action, Set<UUID> componentsIds) {
        Set<Component> components = componentService.getComponentsByIds(componentsIds);

        return useCaseRepository.save(UseCase.builder()
                .id(UUID.randomUUID())
                .name(name)
                .action(action)
                .actor(actor)
                .components(components)
                .build());
    }

    @Override
    public UseCase getById(UUID id) {
        return useCaseRepository.findById(id).orElseThrow(UseCaseNotFoundException::new);
    }

    public static class UseCaseNotFoundException extends RuntimeException{}
}
