import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
	protected static int count = 0;
  	protected MsgType type;
   	protected MsgStatus status;
    protected String text;
    protected boolean online;
    protected ArrayList<Chatroom> rooms;
    
   	protected final int id;

    public Message(){
        this.type = MsgType.UNDEFINED;
        this.status = MsgStatus.UNDEFINED;
        this.text = null;
        this.rooms = null;
        this.id = ++count;
    }

    public Message(MsgType type, MsgStatus status, String text, ArrayList<Chatroom> rooms){
        this.type = type;
        this.status = status;
        this.text = text;
        this.rooms = rooms;
        this.id = ++count;
    }

    private void setType(MsgType type){
	    this.type = type;
    }

    private void setStatus(MsgStatus status){
	    this.status = status;
    }

    private void setText(String text){
	    this.text = text;
    }

    public ArrayList<Chatroom> getRooms(){
	    return rooms;
    }
    
    public int getID() {
    	return id;
    }
    
    public MsgType getType(){
    	return type;
    }

    public MsgStatus getStatus(){
    	return status;
    }

    public String getText(){
    	return text;
    }

}
