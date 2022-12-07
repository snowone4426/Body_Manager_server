package net.ict.bodymanager.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ict.bodymanager.entity.Member;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
  private String fileName;

  @NotEmpty
  private int grade;    //default 0

  private LocalDate created_at;


}
