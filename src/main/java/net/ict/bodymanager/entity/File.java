package net.ict.bodymanager.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "food" )
public class File {

    @Id
    private String file_id;

    @Column(nullable = false)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    public void changeFood(Food food){
        this.food = food;
    }



}
