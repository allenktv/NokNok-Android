package com.kbear.noknok.bo;

import com.kbear.noknok.service.ChatService;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.MessageCompletionHandler;
import com.kbear.noknok.service.completionhandlers.StringsCompletionHandler;

import javax.inject.Inject;

/**
 * Created by allen on 3/3/15.
 */
public class ChatBO {

    private final ChatService mChatService;

    @Inject
    public ChatBO(ChatService chatService) {
        mChatService = chatService;
    }

    //Emitters

    public void sendMessage(String message, BooleanCompletionHandler completionHandler) {
        mChatService.sendMessage(message, completionHandler);
    }

    public void sendTyping(boolean isTyping, BooleanCompletionHandler completionHandler) {
        mChatService.sendTyping(isTyping ? 1 : 0, completionHandler);
    }

    //Listeners

    public void onMessageReceived(MessageCompletionHandler completionHandler) {
        mChatService.receiveMessage(completionHandler);
    }

    public void onTypingReceived(StringsCompletionHandler completionHandler) {
        mChatService.receiveTypingEvent(completionHandler);
    }
}
