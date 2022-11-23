package net.ict.bodymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PTProgramDTO {
  private Long program_id;
  private Long pt_member_id;
  @NotEmpty
  private int pt_count;
  @NotEmpty
  private String title;
  @NotEmpty
  private double weight;
  @NotEmpty
  private int count;
  private LocalDate created_at;
}
