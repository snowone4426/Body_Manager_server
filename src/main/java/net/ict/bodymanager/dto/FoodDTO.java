package net.ict.bodymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
  private Long food_id;

  private Long member_id;

  @NotEmpty
  private String time;

  @NotEmpty
  private String content;

  //첨부파일 이름
  @NotEmpty
  private List<String> food_img;

  @NotNull
  private int grade;    //default 0

  private LocalDateTime created_at;


}
