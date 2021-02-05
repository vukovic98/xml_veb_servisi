package com.ftn.xml.email.service;

import java.util.Base64;
import java.util.Base64.Decoder;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ftn.xml.email.dto.ObavestenjeDTO;
import com.ftn.xml.email.dto.OdbijenZahtevDTO;
import com.ftn.xml.email.dto.ResenjeDTO;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	public void sendMail(ObavestenjeDTO dto) throws Exception {
		try {
			Decoder decoder = Base64.getDecoder();
	        byte[] decodedByte_pdf = decoder.decode(dto.getPdf().split(",")[1].getBytes());
	        byte[] decodedByte_html = decoder.decode(dto.getHtml().split(",")[1].getBytes());
	        
	        System.out.println(dto.getHtml());
	        System.out.println(decodedByte_html);
	        
			MimeMessage mimeMessage = this.sender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setTo(dto.getTo());
			mimeMessageHelper.setFrom("mrs.isa2020@gmail.com");
			mimeMessageHelper.setSubject(dto.getNaslov());

			DataSource dataSource = new ByteArrayDataSource(decodedByte_pdf, "application/pdf");
			DataSource dataSource_html = new ByteArrayDataSource(decodedByte_html, "text/html");
			
			mimeMessageHelper.addAttachment(MimeUtility.encodeText(""), dataSource);
			mimeMessageHelper.addAttachment(MimeUtility.encodeText(""), dataSource_html);

			mimeMessageHelper.setText("", true);

			this.sender.send(mimeMessageHelper.getMimeMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Async
	public void odbijenZahtev(OdbijenZahtevDTO dto) {
		try {
			MimeMessage msg = this.sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(dto.getTo());
			helper.setSubject(dto.getNaslov());
			
			helper.setText(dto.getSadrzaj(), true);
			this.sender.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void posaljiResenje(ResenjeDTO dto) {
		try {
			Decoder decoder = Base64.getDecoder();
	        byte[] decodedByte_pdf = decoder.decode(dto.getPdf().split(",")[1].getBytes());
	        byte[] decodedByte_html = decoder.decode(dto.getHtml().split(",")[1].getBytes());
	        
	        System.out.println(dto.getHtml());
	        System.out.println(decodedByte_html);
	        
			MimeMessage mimeMessage = this.sender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setTo(dto.getTo());
			mimeMessageHelper.setFrom("mrs.isa2020@gmail.com");
			mimeMessageHelper.setSubject(dto.getNaslov());

			DataSource dataSource = new ByteArrayDataSource(decodedByte_pdf, "application/pdf");
			DataSource dataSource_html = new ByteArrayDataSource(decodedByte_html, "text/html");
			
			mimeMessageHelper.addAttachment(MimeUtility.encodeText(""), dataSource);
			mimeMessageHelper.addAttachment(MimeUtility.encodeText(""), dataSource_html);

			mimeMessageHelper.setText("", true);

			this.sender.send(mimeMessageHelper.getMimeMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
