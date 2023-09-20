package global.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Abdyrazakova Aizada
 */
@Getter
@Setter
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "category_gen")
    @SequenceGenerator(name = "category_gen",sequenceName = "category_seq",allocationSize = 1)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category",cascade = {REFRESH,DETACH,PERSIST,MERGE})
    private List<Subcategory> subcategory;
}
