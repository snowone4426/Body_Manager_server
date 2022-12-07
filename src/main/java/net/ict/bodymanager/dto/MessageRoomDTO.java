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
public class MessageRoomDTO {


    private Long roomId;

    private LocalDateTime createdAt;

    @NotEmpty
    private int memberId;

    @NotEmpty
    private int trainerId;


}
