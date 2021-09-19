package team.appsec.appsecbox.domain.assessment;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "assessments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Assessment {
    @Id
    private UUID id;

    private Long timeStart;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany
    @JoinColumn(name = "assessment_id")
    private Set<Job> jobs;

    public enum State{
        NEW,
        IN_PROGRESS,
        DONE
    }
}
