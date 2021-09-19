package team.appsec.appsecbox.domain.assessment;

import lombok.*;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "issues")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Issue {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    private State state;

    private String name;
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    @CollectionTable(name = "issues_meta", joinColumns = @JoinColumn(name = "issue_id"))
    private Map<String,String> meta;

    public enum Severity{
        INFO,
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }
    public enum State{
        ANALYSIS,
        FALSE,
        TO_FIX,
        CONFIRMATION,
        CLOSED
    }
}
