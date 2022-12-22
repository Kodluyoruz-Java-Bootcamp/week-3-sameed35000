package emlakcepte.dao;

import java.util.ArrayList;
import java.util.List;

import emlakcepte.model.Message;

public class MessageDao {

	  private static List<Message> messageList = new ArrayList<>();

	  public static void saveMessage(Message message) {
	    messageList.add(message);
	  }

	  public static List<Message> getAllMessages() {
	    return messageList;
	  }

	  public static Message getMessageById(long id) {
	    return messageList.stream()
	      .filter(message -> message.getId() == id)
	      .findFirst()
	      .orElse(null);
	  }

	  public static void updateMessage(long id, Message updatedMessage) {
	    Message message = getMessageById(id);
	    if (message != null) {
	      int index = messageList.indexOf(message);
	      messageList.set(index, updatedMessage);
	    }
	  }

	  public static void deleteMessage(long id) {
	    Message message = getMessageById(id);
	    if (message != null) {
	      messageList.remove(message);
	    }
	  }
	}
