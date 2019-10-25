/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Silvester
 */
@ApplicationScoped
@ServerEndpoint(value="/echo2/{chatroom}",configurator=ChatroomServerConfigurator.class)
public class ChatroomServerEndpoint {
    
     
    
    static Map<String,Set<Session>> chatrooms=(Map<String,Set<Session>>)Collections.synchronizedMap(new HashMap<String,Set<Session>>());
    
    public Set<Session>getChatroom(String chatroomName){
        Set<Session> chatroom=chatrooms.get(chatroomName);
        if(chatroom==null)
        {
            chatroom=Collections.synchronizedSet(new HashSet<Session>());
            chatrooms.put(chatroomName, chatroom);
        }
        return chatroom;
    }
    @OnOpen
    public void handleOpen(EndpointConfig config,Session userSession,@PathParam("chatroom") String chatroom){
        userSession.getUserProperties().put("username", config.getUserProperties().get("username"));
        userSession.getUserProperties().put("chatroom", chatroom);
        Set<Session> chatroomUsers=getChatroom(chatroom);
        chatroomUsers.add(userSession);
    }
    @OnClose
    public void handeClose(Session userSession){
        String chatroom=(String)userSession.getUserProperties().get("chatroom");
        Set<Session> chatroomUsers=getChatroom(chatroom);
        chatroomUsers.remove(userSession);
    }
    
    @OnError
    public void handleError(Throwable t){
        System.out.println(t+"::");
    }
    
    @OnMessage
    public void handleMessage(String message,Session userSession) {
        String username=(String)userSession.getUserProperties().get("username");
        String chatroom=(String)userSession.getUserProperties().get("chatroom");
        Set<Session>chatroomUsers=getChatroom(chatroom);
        chatroomUsers.stream().forEach( x-> {
        
            try{
                x.getBasicRemote().sendText(buildJsonData(username,message));
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        });
    }

    private String buildJsonData(String username, String message) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String fecha_hora= sdf.format(cal.getTime());

        JsonObject jsonObject =Json.createObjectBuilder().add("message"," "+username+": "+message+"    "+fecha_hora).build();
        StringWriter stringWriter=new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stringWriter)){
            jsonWriter.write(jsonObject);
        }
        System.out.print(stringWriter.toString());
        return stringWriter.toString();
    }
    
}
