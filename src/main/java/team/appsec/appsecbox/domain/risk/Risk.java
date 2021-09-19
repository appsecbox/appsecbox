package team.appsec.appsecbox.domain.risk;

import java.util.List;
import java.util.UUID;

public class Risk {
    private UUID id;
    private Double rank;
    private Criticality criticality;
    private List<Asset> assets;
    private List<ThreatActor> threatActors;
    private List<Vulnerability> vulnerabilities;

    public enum Criticality{
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }
}
