package com.pts.Service;

import com.pts.entity.MailModel;

import javax.mail.MessagingException;

public interface MailService {
    /**
     * Gửi email
     * @param mail thông tin email
     * @throws MessagingException lỗi gửi email
     */
    void send(MailModel mail) throws MessagingException;
    /**
     * Gửi email đơn giản
     * @param to email người nhận
     * @param subject tiêu đề email
     * @param body nội dung email
     * @throws MessagingException lỗi gửi email
     */
    void send(String to, String subject, String body) throws MessagingException;
}
