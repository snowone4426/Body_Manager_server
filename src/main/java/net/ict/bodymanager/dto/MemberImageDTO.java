package net.ict.bodymanager.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberImageDTO {

    private String uuid;

    private String fileName;

    private int ord;
}
