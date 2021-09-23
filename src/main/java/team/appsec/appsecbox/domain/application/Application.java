package team.appsec.appsecbox.domain.application;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "applications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application {
    @Id
    private UUID id;
    private String name;

    @OneToOne(mappedBy = "application")
    private Architecture architecture;

    @ToString.Exclude
    @BatchSize(size = 10)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "applications_components",
            joinColumns = {@JoinColumn(name = "application_id")},
            inverseJoinColumns = {@JoinColumn(name = "component_id")}
    )
    private Set<Component> components;

    @OneToMany
    @JoinColumn(name = "application_id")
    private Set<UseCase> useCases;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @CollectionTable(name = "applications_meta", joinColumns = @JoinColumn(name = "application_id"))
    private Map<String, String> meta;
}
