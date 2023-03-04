package portfolio.me.model;

public class EmailRequest {

    private String from;
    private String subject;
    private String text;

    public EmailRequest() {
    }

    public EmailRequest(String from, String subject, String text) {
        this.from = from;
        this.subject = subject;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
