package team.appsec.appsecbox.domain.assessment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.appsec.appsecbox.domain.application.TechnicalComponent;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private State state;

    private String meta;

    @OneToMany
    @JoinColumn(name = "job_id")
    private Set<Issue> issues;

    @ManyToOne
    @JoinColumn(name = "control_id")
    private Control control;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "technical_component_id")
    private TechnicalComponent technicalComponent;


    public enum State{
        TO_START,
        IN_PROGRESS,
        DONE,
        STOPPED,
        ERROR
    }
}
