package secret.santa.standalone;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Component
public class SecretSanta {
    private List<Member> members;
    private static final Logger LOGGER = LoggerFactory.getLogger(SecretSanta.class);

    @Autowired
    private NotificationService notificationService;

    public void says() {
        List<Member> membersOtherSide = new ArrayList<>(members);

        Collections.shuffle(members);
        List<ReceiverBean> receivers = new ArrayList<>();
        for (final Member member : members) {
            List<Member> membersWithoutCurrent = removeItem(membersOtherSide, member.getEmail());

            if (membersWithoutCurrent.size() == 0) {
                LOGGER.info("!!!Santa is resaying!!!");

                receivers = null;
                break;
            }

            Member memberReceiver = getRandom(membersWithoutCurrent);

            ReceiverBean receiverMail = new ReceiverBean();
            receiverMail.setMailReceiver(member.getEmail());
            receiverMail.setGiftReceiverName(memberReceiver.getNick());

            receivers.add(receiverMail);
            membersOtherSide = removeItem(membersOtherSide, memberReceiver.getEmail());
        }

        if (CollectionUtils.isEmpty(receivers)) {
            says();
        } else {

            for (ReceiverBean receiver : receivers) {
                notificationService.sendEmail(receiver);
//                notificationService.sendEmailTest(receiver);
            }
        }
    }

    private Member getRandom(List<Member> members) {
        Collections.shuffle(members);
        return members.get(new Random().nextInt(members.size()));
    }

    private List<Member> removeItem(List<Member> membersOtherSide, String email) {
        return (List<Member>) CollectionUtils.select(membersOtherSide, memberOtherSide -> !memberOtherSide.getEmail().equals(email));
    }

    @PostConstruct
    public void init() {
        try {
            Properties prop = new Properties();
            prop.load(SecretSanta.class.getResourceAsStream("/members.properties"));

            if (!prop.isEmpty()) {
                members = new ArrayList<>();
                for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                    Member member = new Member();
                    member.setEmail(entry.getKey().toString());
                    member.setNick(entry.getValue().toString());

                    members.add(member);
                }
            } else {
                throw new RuntimeException("members are emty");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
