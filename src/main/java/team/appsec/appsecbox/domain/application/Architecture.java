package team.appsec.appsecbox.domain.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "architecture")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Architecture {
    @Id
    private UUID id;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "application_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Application application;

    @OneToMany
    @JoinColumn(name = "architecture_id")
    private Set<ArchitectureLink> destinationsComponents;
}
