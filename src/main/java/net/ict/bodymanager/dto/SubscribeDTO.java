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
public class SubscribeDTO {

  private Long sub_id;

  private Long member_id;
  @NotEmpty
  private LocalDate membership_start;
  @NotEmpty
  private LocalDate membership_end;
  @NotEmpty
  private LocalDate suit_start;
  @NotEmpty
  private LocalDate suit_end;
}
