import java.io.Serializable;

public class Message implements Serializable {
	protected static int count;
  	protected MsgType type;
   	protected MsgStatus status;
    	protected String text;
   	protected final int id;

    public Message(){
        this.type = "Undefined";
        this.status = "Undefined";
        this.text = "Undefined";
        this.id = ++count;
    }

    public Message(String type, String status, String text){
        this.type = type;
        this.status = status;
        this.text = text;
 	this.id = ++count;
    }

    private void setType(String type){
	    this.type = type
    }

    private void setStatus(String status){
	    this.status = status;
    }

    private void setText(String text){
	    this.text = text;
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
