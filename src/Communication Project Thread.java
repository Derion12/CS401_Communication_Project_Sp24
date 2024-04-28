import java.util.ArrayList;
import java.util.Arrays;

public class Thread {

    // array of messages
    ArrayList<ChatMsg> chatMessages = new ArrayList<>();
    ArrayList<User> participants = new ArrayList<>();
    private int numMessages;

    // constructors
    public Thread() {
        numMessages = 0;
    }

    public Thread(ChatMsg[] messages, User[] participants) {
        this.chatMessages.addAll(Arrays.asList(messages));
        this.participants.addAll(Arrays.asList(participants));

        // this will save the current number of messages
        numMessages = chatMessages.size();
    }

    public void addMessage(ChatMsg message) {
        // Add the new message to the end of the array
        chatMessages.add(message);

        // Increment the number of messages
        numMessages++;
    }

    public void addParticipant(User participant) {
        participants.add(participant);
    }

    // returns number of messages in thread
    public int getNumMessages() {
        return numMessages;
    }

    // returns the entire thread
    public ArrayList<ChatMsg> getThread() {
        return chatMessages;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

}
