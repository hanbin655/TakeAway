package test;

public class HelloWorld {
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String excute(){
        return "Hello"+getMessage();
    }
}