import java.io.Serializable;

public class Message implements Serializable {
	protected static int count;
    protected String type;
    protected String status;
    protected String text;
    protected int id;

    public Message(){
        this.type = "Undefined";
        this.status = "Undefined";
        this.text = "Undefined";
        this.id = count++;
    }

    public Message(String type, String status, String text){
        this.type = type;
        this.status = status;
        this.text = text;
    }

    private void setType(String type){
    }

    private void setStatus(String status){
    }

    private void setText(String text){
    }

    public int getID() {
    	return id;
    }
    
    public String getType(){
    	return type;
    }

    public String getStatus(){
    	return status;
    }

    public String getText(){
    	return text;
    }

}
