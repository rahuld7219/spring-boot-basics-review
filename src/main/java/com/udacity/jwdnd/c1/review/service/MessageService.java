package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.MessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

//    -- No longer needed ----> TAKE I
//
//    private String message;
//
//    public MessageService(String message) {
//        System.out.println("Creating MessageService bean.");
//        this.message = message;
//    }
//
//    public String uppercase() {
//        return this.message.toUpperCase();
//    }
//
//    public String lowercase() {
//        return this.message.toLowerCase();
//    }
//
//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("Created MessageService bean.");
//    }


//  -- No longer needed ----> TAKE II
//
//    private List<ChatMessage> messageHistory;
//
//    @PostConstruct
//    private void postConstruct() {
//        System.out.println("Creating  MessageService bean.");
//        this.messageHistory = new ArrayList<>();
//    }
//
//    public void addMessage(ChatForm chatForm) {
//
//        ChatMessage newMessage = new ChatMessage();
//        newMessage.setUsername(chatForm.getUsername());
//
//        switch (chatForm.getMessageType()) {
//            case "Say":
//                newMessage.setMessageText(chatForm.getMessageText());
//                break;
//            case "Shout":
//                newMessage.setMessageText(chatForm.getMessageText().toUpperCase());
//                break;
//            case "Whisper":
//                newMessage.setMessageText(chatForm.getMessageText().toLowerCase());
//                break;
//        }
//
//        this.messageHistory.add(newMessage);
//    }
//
//    public List<ChatMessage> getChatHistory() {
//        return new ArrayList<>(messageHistory);
//    }

    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messagesMapper) {
        this.messageMapper = messagesMapper;
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());

        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessageText(chatForm.getMessageText());
                break;
            case "Shout":
                newMessage.setMessageText(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newMessage.setMessageText(chatForm.getMessageText().toLowerCase());
                break;
        }

        this.messageMapper.saveMessage(newMessage);
    }

    public List<ChatMessage> getChatHistory() {
        return this.messageMapper.getChatMessages();
    }
}
