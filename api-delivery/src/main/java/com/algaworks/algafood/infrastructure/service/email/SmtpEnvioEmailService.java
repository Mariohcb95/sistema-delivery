package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(mensagem.getCorpo(), true);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Nao foi possivel enviar e-mail", e);
		}

	}

}
