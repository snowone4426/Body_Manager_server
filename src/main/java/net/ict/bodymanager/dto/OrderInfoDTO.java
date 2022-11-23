package net.ict.bodymanager.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO {
  private Long order_id;
  private Long member_id;
  private LocalDate created_at;
}
