package team.appsec.appsecbox.domain.assessment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "controls")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Control {
    @Id
    private UUID id;
    private String name;
    private String dockerCommand;

    @Enumerated(EnumType.STRING)
    private Type type;


    public enum Type{
        SOURCE_CODE,
        ARTIFACT,
        SERVER,
        INFRASTRUCTURE
    }

    public enum State{
        IN_PROGRESS,
        DONE
    }
}
