import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

public class ChatMsgTest {

    @Test
    public void testConstructorAndGetters() {
        // Create sample users
        User sender = new User("Sender", null, null, null);
        User receiver = new User("Receiver", null, null, null);
        
        // Create a ChatMsg object
        int messageId = 1;
        String message = "Hello, World!";
        ChatMsg chatMsg = new ChatMsg(messageId, sender, receiver, message);
        
        // Check if the constructor properly sets the fields
        assertEquals(messageId, chatMsg.getMessageId());
        assertEquals(sender, chatMsg.getSender());
        assertEquals(receiver, chatMsg.getReceiver());
        assertEquals(message, chatMsg.getmessagec());
        assertNotNull(chatMsg.getcurrentDate());
    }
}
