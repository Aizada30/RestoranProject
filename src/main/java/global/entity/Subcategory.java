package global.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "subcategories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subcategory{
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "subcategory_gen")
    @SequenceGenerator(name = "subcategory_gen",sequenceName = "subcategory_seq",allocationSize = 1)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "subcategory",cascade = {REFRESH,DETACH,PERSIST,MERGE})
    private List<Menu> menuList;
    @ManyToOne(cascade = {REFRESH,DETACH,PERSIST,MERGE})
    private Category category;
}