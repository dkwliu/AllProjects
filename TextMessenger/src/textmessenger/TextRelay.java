package textmessenger;

public class TextRelay {
    
    private static String message;
    private static int msgCode;
    
    public void setMsgString(String str) {
        message = str;
    }
    
    public String getMsgString() {
        return message;
    }
    
    public void setMsgCode(int code) {
        msgCode = code;
    }
    
    public int getMsgCode() {
        return msgCode;
    }
}
