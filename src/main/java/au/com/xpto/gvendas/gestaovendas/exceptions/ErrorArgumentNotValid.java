package au.com.xpto.gvendas.gestaovendas.exceptions;

public class ErrorArgumentNotValid {

    private String msgUser;
    private String msgDeveloper;

    public ErrorArgumentNotValid(String msgUser, String msgDeveloper) {
        this.msgUser = msgUser;
        this.msgDeveloper = msgDeveloper;
    }

    public String getMsgUser() {
        return msgUser;
    }

    public String getMsgDeveloper() {
        return msgDeveloper;
    }
}
