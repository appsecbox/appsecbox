package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.application.UseCase;

import java.util.Set;
import java.util.UUID;

public interface UseCaseService {
    UseCase createUseCase(String name, String actor, String action, Set<UUID> componentsIds);
    UseCase getById(UUID id);
}
