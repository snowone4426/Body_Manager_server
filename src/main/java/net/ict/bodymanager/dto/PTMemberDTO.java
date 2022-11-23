package net.ict.bodymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PTMemberDTO {
  private Long pt_member_id;
  private Long member_id;
  @NotEmpty
  private int pt_total_count;
  private Long pt_id;
}
