package secret.santa.standalone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    @Value("${sender.mail}")
    private String senderMail;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(ReceiverBean receiver) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(senderMail);
            helper.setTo(receiver.getMailReceiver());

            helper.setSubject("Тайный Санта говорит...");
            helper.setText(MessageFormat.format("Тайный Санта говорит, что ты даришь подарок {0}", receiver.getGiftReceiverName()), false);

            javaMailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("sendAdminEmail", e);
        }
    }

    public void sendEmailTest(ReceiverBean receiver) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(senderMail);
            helper.setTo(receiver.getMailReceiver());

            helper.setSubject("[SECRET SANTA TEST]");
            helper.setText("TEST MAIL FROM SECRET SANTA", false);

            javaMailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("sendAdminEmail", e);
        }

    }
}
