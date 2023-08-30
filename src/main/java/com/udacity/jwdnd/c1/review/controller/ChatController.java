package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getChatPage(@ModelAttribute("chatForm") ChatForm newChatForm, Model model) {
        model.addAttribute("chatMessages", this.messageService.getChatHistory());
        return "chat";
    }


    // in case of GET request=> Spring creates the form-backing object and add it to the model with the name specified in the form (as thymeleaf needs it to render the form whether the object has data or not) and then call the method.
    // in case of POST request=> Spring creates the form-backing object and set its fields with the data from the body of the request and add it to the model with the name specified in the form and then call the method.


    @PostMapping
    public String postChats(Authentication authentication, ChatForm chatForm, Model model) {  // ChatForm chatForm is same as writing @ModelAttribute ChatForm chatForm as form backing object name in the html form is same as declared here in the parameter (i.e., chatForm)
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // if we pass the Authentication type in the method parameter then Spring automatically inject the authentication object returned by our authentication service
        chatForm.setUsername(authentication.getName());
        this.messageService.addMessage(chatForm);
        model.addAttribute("chatMessages", this.messageService.getChatHistory());
        chatForm.setMessageText("");
        chatForm.setMessageType("Say");
        return "chat";
    }

    /*
    Instead of repeating it in every controller,
    this way spring make the always needed value available to the model.
    It is like bean declaration,
    but instead of values ending in spring application context it ends up in spring mvc model
     */
    @ModelAttribute("allMessageTypes")
    public String[] getAllMessageTypes() {
        return new String[]{"Say", "Shout", "Whisper"};
    }
}
