package com.dansmultipro.ops.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailUpdateStatusPoJo {

    private String emailAddress;
    private String emailBody;
    private String username;
    private String status;

}
