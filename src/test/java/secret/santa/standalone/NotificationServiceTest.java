package secret.santa.standalone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void sendEmail() throws Exception {
        ReceiverBean receiver = new ReceiverBean();
        receiver.setMailReceiver("verner@csltd.com.ua");
        receiver.setGiftReceiverName("Женьке");

        notificationService.sendEmail(receiver);
    }

}