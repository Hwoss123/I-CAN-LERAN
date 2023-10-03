package com.Service.impl;

import com.Mapper.ChatMapper;
import com.Service.ChatService;
import com.pojo.Chat_each;
import com.pojo.Chat_records;
import com.utils.JwtUtils;
import com.utils.TimestampComparator;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatMapper chatMapper;
    @Override
    public Chat_each getChatRecords(String jwt, Integer toId) {
        Claims claims = JwtUtils.parseJwt(jwt);
        Integer fromId = (Integer) claims.get("id");
        List<Chat_records> sendList = chatMapper.getChatRecords(fromId, toId);
        sendList.sort(new TimestampComparator());
        List<Chat_records> recipientList = chatMapper.getChatRecords(toId, fromId);
        recipientList.sort(new TimestampComparator());

        Chat_each chat_each = new Chat_each();
        chat_each.setSenders(sendList);
        chat_each.setRecipients(recipientList);
        return chat_each;
    }
}