package team.appsec.appsecbox.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUseCaseRequest {
    private String name;
    private String actor;
    private String action;
    private Set<UUID> componentsIds;
}
