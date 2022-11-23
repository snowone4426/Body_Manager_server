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
public class PriceDTO {
  private Long price_id;
  @NotEmpty
  private String price_name;
  @NotEmpty
  private int price_info;
}
