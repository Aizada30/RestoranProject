package global.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
@Table(name = "menues")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu  {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "menu_gen")
    @SequenceGenerator(name = "menu_gen",sequenceName = "menu_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private boolean isVegetarian;
    @ManyToOne(cascade = {REFRESH,DETACH,PERSIST,MERGE})
    private Restaurant restaurant;
    @ManyToOne(cascade = {REFRESH,DETACH,PERSIST,MERGE})
    private Subcategory subcategory;
    @OneToOne (mappedBy = "menu",cascade = {REFRESH,DETACH,PERSIST,MERGE})
    private StopList stopList;
    @ManyToMany(cascade = {REFRESH,DETACH,PERSIST,MERGE})
    private List<Cheque> chequeList;


}
