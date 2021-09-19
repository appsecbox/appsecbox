package team.appsec.appsecbox.domain.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.appsec.appsecbox.domain.assessment.Control;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "technical_components")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TechnicalComponent {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Control.Type type;
    private String name;
    private String uri;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @CollectionTable(name = "technical_components_meta", joinColumns = @JoinColumn(name = "technical_component_id"))
    private Map<String,String> meta;

}
