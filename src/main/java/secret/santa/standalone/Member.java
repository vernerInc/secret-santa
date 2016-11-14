package secret.santa.standalone;

import java.io.Serializable;

class Member implements Serializable {

    private String nick;
    private String email;

    String getNick() {
        return nick;
    }

    void setNick(String nick) {
        this.nick = nick;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
