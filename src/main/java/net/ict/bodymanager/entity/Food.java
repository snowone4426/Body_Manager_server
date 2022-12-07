package net.ict.bodymanager.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "food_img")
public class Food {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long food_id;

  @ManyToOne
  @JoinColumn(name="member_id")
  private Member member;

  @Column(nullable = false)
  private String time;

  @Column(length = 2000, nullable = false)
  private String content;

  @Column(nullable = false)
  private int grade;

  @Column(nullable = false)
  private LocalDateTime created_at;

  public void change(String time, String content){
    this.time = time;
    this.content=content;
}

  @OneToMany(mappedBy = "food",
          cascade = {CascadeType.ALL},
          fetch = FetchType.LAZY,
          orphanRemoval = true)
  @Builder.Default
  @BatchSize(size = 20)
  private Set<File> food_img = new HashSet<>();

  public void addImage(String file_id, String fileName){
        File file = File.builder()
                .file_id(file_id)
                .fileName(fileName)
                .food(this)
                .build();
        food_img.add(file);

  }

  public void clearImage(){
      food_img.forEach(file -> file.changeFood(null));
      this.food_img.clear();

  }
}
