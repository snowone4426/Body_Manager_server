package net.ict.bodymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InbodyDTO {


  @NotEmpty
  private double weight;
  @NotEmpty
  private double SMM;
  @NotEmpty
  private double BFM;
  @NotEmpty
  private double BMI;
  @NotEmpty
  private double PBF;
  @NotEmpty
  private double WHR;
  @NotEmpty
  private double BMR;
  @NotEmpty
  private double body_muscle;
  @NotEmpty
  private double left_hand_muscle;
  @NotEmpty
  private double right_hand_muscle;
  @NotEmpty
  private double left_leg_muscle;
  @NotEmpty
  private double right_leg_muscle;
  @NotEmpty
  private double body_fat;
  @NotEmpty
  private double left_hand_fat;
  @NotEmpty
  private double right_hand_fat;
  @NotEmpty
  private double left_leg_fat;
  @NotEmpty
  private double right_leg_fat;


}
