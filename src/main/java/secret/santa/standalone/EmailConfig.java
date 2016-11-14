package secret.santa.standalone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender(
            @Value("${email.host}") String host
            , @Value("${email.port}") String port
            , @Value("${email.username}") String username
            , @Value("${email.password}") String password
            , @Value("${mail.smtp.auth}") String smtpAuth
            , @Value("${mail.smtp.starttls.enable}") String smtpStarttlsEnable

    ) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPort(Integer.parseInt(port));
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", smtpAuth);
        props.setProperty("mail.smtp.starttls.enable", smtpStarttlsEnable);
        javaMailSender.setJavaMailProperties(props);


        return javaMailSender;
    }


}
