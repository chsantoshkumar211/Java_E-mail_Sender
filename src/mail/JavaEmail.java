package mail;

import java.util.Properties;
import javax.swing.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaEmail extends JFrame{

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	 
	public static void main(String args[]) throws AddressException,
			MessagingException {
		String from = JOptionPane.showInputDialog("Enter Your Email-id: ");
	    String pwd = JOptionPane.showInputDialog("Enter Your password: ");
		String to = JOptionPane.showInputDialog("Enter to Email-id: ");
		String sub = JOptionPane.showInputDialog("Enter subject: ");
	    String msg = JOptionPane.showInputDialog("Enter body: ");


		JavaEmail javaEmail = new JavaEmail();
		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(to,sub,msg);
		javaEmail.sendEmail(from,pwd);
		 
	}

	public void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage(String to,String sub,String msg) throws AddressException,
			MessagingException {
		String[] toEmails = { to };
		String emailSubject = sub;
		String emailBody = msg;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");//for a html email
		//emailMessage.setText(emailBody);// for a text email

	}

	public void sendEmail(String from,String pwd) throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = from;//just the id alone without @gmail.com
		String fromUserEmailPassword = pwd;

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");

	}

}