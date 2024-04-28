import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ThreadTest {

    private Thread thread;
    private User[] participants;
    private ChatMsg[] messages;

    @Before
    public void setUp() {
        // Create sample users
        participants = new User[] { new User("User1", null, null, null), new User("User2", null, null, null) };

        // Create sample messages
        messages = new ChatMsg[] { 
            new ChatMsg(1, participants[0], participants[1], "Hello"), 
            new ChatMsg(2, participants[1], participants[0], "Hi there")
        };

        // Create a new thread with the sample messages and participants
        thread = new Thread(messages, participants);
    }

    @Test
    public void testConstructorAndGetters() {
        // Check if the constructor properly initializes the thread with messages and participants
        assertEquals(messages.length, thread.getNumMessages());
        assertEquals(participants.length, thread.getParticipants().size());
        assertEquals(messages.length, thread.getThread().size());
    }

    @Test
    public void testAddMessage() {
        // Add a new message to the thread
        ChatMsg newMessage = new ChatMsg(3, participants[0], participants[1], "How are you?");
        thread.addMessage(newMessage);

        // Check if the message was added successfully
        assertEquals(messages.length + 1, thread.getNumMessages());
        assertEquals(newMessage, thread.getThread().get(messages.length));
    }

    @Test
    public void testAddParticipant() {
        // Add a new participant to the thread
        User newParticipant = new User("User3", null, null, null);
        thread.addParticipant(newParticipant);

        // Check if the participant was added successfully
        assertEquals(participants.length + 1, thread.getParticipants().size());
        assertEquals(newParticipant, thread.getParticipants().get(participants.length));
    }
}
