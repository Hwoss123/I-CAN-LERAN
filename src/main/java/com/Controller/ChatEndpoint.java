package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.FromMessage;
import com.pojo.Message;
import com.utils.GetHttpSessionConfigurator;
import com.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/instant/chat",configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {

    //用来存储每一个客户端对象对应的ChatEndpoint对象,还有对应的id
    private static final Map<Integer,ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    //和某个客户端连接对象，需要通过他来给客户端发送数据
    private Session session;
//
//    @Autowired
//    private HttpServletRequest req;
    @OnOpen
    //连接建立成功调用
    public void onOpen(Session session, EndpointConfig config) {
            //需要通知其他的客户端，将所有的用户的用户名发送给客户端
            this.session = session;

        HttpSession httpSession =
                (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
            //获取用户id
        System.out.println(httpSession);
//        Map<String, List<String>> headers = handshakeRequest.getHeaders();
//
//        List<String> stringList = headers.get("token");
//        String jwt = stringList.get(0);
//        String jwt = req.getHeader("jwt");
//        Claims claims = JwtUtils.parseJwt(jwt);
//            Integer user_id = (Integer) claims.get("id");

            //存储该链接对象
//            onlineUsers.put(user_id,this);
    }
//
//    @OnMessage
//    //接收到消息时调用
//    public void onMessage(String message,Session session) {
//        try {
//            //获取客户端发送来的数据  {"toId":"张三","message":"你好"}String
//            ObjectMapper mapper = new ObjectMapper();
//            Message mess = mapper.readValue(message, Message.class);
//
//            //获取用户id
//            String jwt = req.getHeader("jwt");
//            Claims claims = JwtUtils.parseJwt(jwt);
//            Integer user_id = (Integer) claims.get("id");
//
//            FromMessage fromMessage = new FromMessage();
//            fromMessage.setFromId(user_id);
//            fromMessage.setMessage(message);
//            String FromMessageStr = mapper.writeValueAsString(fromMessage);
//
//            //将数据推送给指定的客户端
//            ChatEndpoint chatEndpoint = onlineUsers.get(mess.getToId());
//            chatEndpoint.session.getBasicRemote().sendText(FromMessageStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//  @OnClose
//    //连接关闭时调用,建议当用户退出聊天的时候进行主动关闭
//    public void onClose(Session session) {
//        //获取用户id
//        String jwt = req.getHeader("jwt");
//        Claims claims = JwtUtils.parseJwt(jwt);
//        Integer user_id = (Integer) claims.get("id");
//        //移除连接对象
//        onlineUsers.remove(user_id);
//    }


}
