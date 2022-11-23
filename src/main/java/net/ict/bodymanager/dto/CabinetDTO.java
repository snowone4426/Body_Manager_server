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
public class CabinetDTO {
  private Long cab_id;
  @NotEmpty
  private int cab_num;
  @NotEmpty
  private LocalDate start_date;
  @NotEmpty
  private LocalDate end_date;
  @NotEmpty
  private int cab_pwd;

  private Long member_id;

}
