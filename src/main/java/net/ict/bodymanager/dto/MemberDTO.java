package net.ict.bodymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

  private Long member_id;
  @NotEmpty
  private String email;
  @NotEmpty
  private String password;
  @NotEmpty
  private String name;
  @NotEmpty
  private String address;
  @NotEmpty
  private String phone;
  @NotEmpty
  private String gender;
  @NotEmpty
  private double height;
  @NotEmpty
  private String remark;
  @NotEmpty
  private LocalDate birth;
  @NotEmpty
  private String profile;
  @NotEmpty
  private String type;  //dormant(휴면), common, trainer , admin

  private LocalDateTime created_at;

}
