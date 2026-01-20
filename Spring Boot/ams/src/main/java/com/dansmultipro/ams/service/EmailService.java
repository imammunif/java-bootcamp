package com.dansmultipro.ams.service;

import com.dansmultipro.ams.util.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);

}
