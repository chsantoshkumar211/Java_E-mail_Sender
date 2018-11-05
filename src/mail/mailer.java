package mail;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class mailer {
private String email;
private String password;
private String to_email;
private String subject;
private String msg;

public mailer() {
    JFrame main = new JFrame("Java E-mail sender");
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setResizable(false);
    main.setLayout(null);
    main.setPreferredSize(new Dimension(500, 500));
    main.setLocation(400, 200);

    // Heading: LOGIN
    JLabel heading = new JLabel("Java E-mail sender");
    heading.setBounds(80, 20,200, 20);
    main.add(heading);

    // Label email
    JLabel email_label = new JLabel("E-mail id");
    email_label.setBounds(5, 70, 80, 20);
    main.add(email_label);
    // Textfield email
    final JTextField email_field = new JTextField();
    email_field.setBounds(110, 70, 250, 20);
    main.add(email_field);
    this.email = email_field.getText();
    
    // Label Password
    JLabel password_label = new JLabel("Password");
    password_label.setBounds(5, 95, 80, 20);
    main.add(password_label);
    // Textfield Password
    final JPasswordField password_field = new JPasswordField();
    password_field.setBounds(110, 95, 250, 20);
    main.add(password_field);
    this.password = String.valueOf(password_field.getPassword());
    
    // Label to_email_label
    JLabel to_email_label = new JLabel("To E-mail id");
    to_email_label.setBounds(5, 120, 85, 20);
    main.add(to_email_label);
    // Textfield to_email_field
    final JTextField to_email_field = new JTextField();
    to_email_field.setBounds(110, 120, 250, 20);
    main.add(to_email_field);
    this.to_email = to_email_field.getText();
    
    // Label subject_label
    JLabel subject_label = new JLabel("Subject");
    subject_label.setBounds(5, 145, 85, 20);
    main.add(subject_label);
    // Textfield subject_field
    final JTextField subject_field = new JTextField();
    subject_field.setBounds(110, 145, 250, 20);
    main.add(subject_field);
    this.subject = subject_field.getText();
    
    // Label message
    JLabel message_label = new JLabel("Message");
    message_label.setBounds(5, 170, 85, 20);
    main.add(message_label);
    // Textfield message_field
    final JTextArea message_field = new JTextArea();
    message_field.setBounds(110, 170, 350, 250);
    main.add(message_field);
    this.msg = message_field.getText();

    // Button Login
    JButton loginBtn = new JButton("Send");
    loginBtn.setBounds(40, 430, 120, 25);
    main.add(loginBtn);
    main.pack();
    main.setVisible(true);
    
    // Label status
    JLabel status_label = new JLabel("Status : Enter the details");
    status_label.setBounds(250, 430, 250, 20);
    main.add(status_label);


    loginBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            status_label.setText("Status : Processing your request...");
            email = email_field.getText();
            password = String.valueOf(password_field.getPassword());
            to_email = to_email_field.getText();
            subject = subject_field.getText();
            msg = message_field.getText();
            if(mailer.this.send()==1) status_label.setText("Status : E-mail sent"); 
            else status_label.setText("Status : E-mail not sent");
        }
    });
}

    public int send(){  
        //Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(email,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to_email));    
         message.setSubject(subject);    
         message.setText(msg);    
         //send message  
         Transport.send(message);  
         return 1;
        } catch (MessagingException e) {return 0;}    
           
  } 

}


