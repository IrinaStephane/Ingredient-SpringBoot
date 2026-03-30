package school.hei.ingredientspringboot.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private Integer id;
    private String name;
    private CategoryEnum category;
    private Double price;
}