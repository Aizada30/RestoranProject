package global.entity;

import global.entity.enums.RestType;
import global.validations.LengthOfWaiters;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant  {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen",sequenceName = "restaurant_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String location;
    private RestType restType;
    private String numberOfEmployees;
    private String service;
    @OneToMany(mappedBy = "restaurant",cascade = {REFRESH,DETACH,PERSIST,MERGE})
    @LengthOfWaiters @Valid
    private List<User> userList;
    @OneToMany(mappedBy = "restaurant",cascade = {REFRESH,DETACH,PERSIST,MERGE})
    private List<Menu>menultemList;
}