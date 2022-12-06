package net.ict.bodymanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDTO {
    private String email;
    private String code;
    private String password;
}
