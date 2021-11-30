/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.model;

import java.util.ArrayList;
import java.util.List;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author PC HP
 */
public class MailSender extends Thread{
    static {
       MailSender sender = new MailSender();
        sender.start();
    }
    static final List<MimeMessage> queue = new ArrayList<>();

    public static void queue(MimeMessage mail) {
        synchronized (queue) {
            queue.add(mail);
            queue.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    if (queue.size() > 0) {
                        try {
                            MimeMessage mail = queue.remove(0);
                            Transport.send(mail);
                            JOptionPane.showMessageDialog(null, "The mail was sent");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    } else {
                        queue.wait();
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
    }
}
