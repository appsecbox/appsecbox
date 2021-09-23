package team.appsec.appsecbox.domain.application;


import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usecases")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UseCase {
    @Id
    private UUID id;

    private String name;
    private String actor;
    private String action;

    @ToString.Exclude
    @BatchSize(size = 10)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "usecases_components",
            joinColumns = {@JoinColumn(name = "usecase_id")},
            inverseJoinColumns = {@JoinColumn(name = "component_id")}
    )
    private Set<Component> components;
}
