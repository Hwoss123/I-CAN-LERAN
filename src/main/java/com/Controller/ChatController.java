package com.Controller;

import com.Service.ChatService;
import com.pojo.Chat_each;
import com.pojo.Result;
import com.utils.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
@Autowired
    private ChatService chatService;
@Autowired
    private HttpServletRequest req;
    @PostMapping
    public Result getChatHistory(@RequestBody Map<String, String> map){
        //    仍然从jwt里面获取id，但是需要前端传进来聊天对象的id
        String jwt = req.getHeader("token");
        String toIdStr = map.get("toId");
        int toId = Integer.parseInt(toIdStr);
        Chat_each chatRecords = chatService.getChatRecords(jwt, toId);
        return Result.success(Code.CHAT_HISTORY_OK, chatRecords);
    }
}
