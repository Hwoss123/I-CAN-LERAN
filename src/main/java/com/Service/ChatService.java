package com.service;

import com.pojo.Chat_each;

public interface ChatService {
    Chat_each getChatRecords(String jwt, Integer toId);
}
