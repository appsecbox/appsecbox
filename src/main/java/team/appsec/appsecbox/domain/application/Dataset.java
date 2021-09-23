package team.appsec.appsecbox.domain.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "datasets")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Dataset {
    @Id
    private UUID id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "dataset_categories", joinColumns = @JoinColumn(name = "dataset_id")) // 2
    @Column(name = "category")
    private Set<Category> categories;

    private String source;

    public static enum Category{
        PUBLIC,
        INTERNAL,
        PERSONAL,
        RESTRICTED,
        SENSITIVE
    }
}
