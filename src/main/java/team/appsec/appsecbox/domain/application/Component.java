package team.appsec.appsecbox.domain.application;

import lombok.*;
import org.hibernate.annotations.BatchSize;


import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "components")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Component {
    @Id
    private UUID id;
    private String name;

    @ToString.Exclude
    @BatchSize(size = 10)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "components_technical_components",
            joinColumns = {@JoinColumn(name = "component_id")},
            inverseJoinColumns = {@JoinColumn(name = "technical_component_id")}
    )
    private Set<TechnicalComponent> technicalComponents;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @CollectionTable(name = "components_meta", joinColumns = @JoinColumn(name = "component_id"))
    private Map<String,String> meta;

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meta=" + meta +
                '}';
    }
}
