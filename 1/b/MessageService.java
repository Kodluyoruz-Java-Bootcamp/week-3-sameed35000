package emlakcepte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import emlakcepte.model.Message;

@Service
public class MessageService {
	private List<Message> messageList = new ArrayList<>();

	public void createMessage(Message message) {
		messageList.add(message);
	}

	public List<Message> getAllMessages() {
		return messageList;
	}

	/*public Message getMessageById(int id) {
		for (Message message : messageList) {
			if (message.getId() == id) {
				return message;
			}
		}
		return null;
	}

	public void updateMessage(int id, Message message) {
		for (int i = 0; i < messageList.size(); i++) {
			if (messageList.get(i).getId() == id) {
				messageList.set(i, message);
				break;
			}
		}
	}

	public void deleteMessage(int id) {
		for (int i = 0; i < messageList.size(); i++) {
			if (messageList.get(i).getId() == id) {
				messageList.remove(i);
				break;
			}
		}
	} */

}