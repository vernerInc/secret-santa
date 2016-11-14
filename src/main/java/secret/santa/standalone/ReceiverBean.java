package secret.santa.standalone;

public class ReceiverBean {
    private String mailReceiver;
    private String giftReceiverName;

    public String getMailReceiver() {
        return mailReceiver;
    }

    public void setMailReceiver(String mailReceiver) {
        this.mailReceiver = mailReceiver;
    }

    public String getGiftReceiverName() {
        return giftReceiverName;
    }

    public void setGiftReceiverName(String giftReceiverName) {
        this.giftReceiverName = giftReceiverName;
    }

    @Override
    public String toString() {
        return "ReceiverBean{" +
                "mailReceiver='" + mailReceiver + '\'' +
                ", giftReceiverName='" + giftReceiverName + '\'' +
                '}';
    }
}
