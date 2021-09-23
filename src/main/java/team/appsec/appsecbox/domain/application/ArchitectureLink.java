package team.appsec.appsecbox.domain.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "architecture_links")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArchitectureLink {
    @Id
    private UUID id;

    private UUID sourceComponentId;
    private UUID destinationComponentId;
}
