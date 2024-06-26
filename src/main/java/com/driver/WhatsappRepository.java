package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.

//    step1. Save user

    private HashMap<String,User> user = new HashMap<>();
    private HashMap<Group, List<User>> groupUserMap;

    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.user = new HashMap<String,User>();
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }
    public String createUser(String name, String mobile) throws Exception {
        if(userMobile.contains(mobile)){
             throw new Exception("User already exists");
        }else{
            User user1 = new User(name,mobile);
            user.put(mobile,user1);
            userMobile.add(mobile);
        }
        return "SUCCESS";
    }
    public Group createGroup(List<User> users){
        if(users.size()==2){
            // The list contains at least 2 users where the first user is the admin.
            // If there are only 2 users, the group is a personal chat and the group name should be kept as the name of the second user(other than admin)
            // If there are 2+ users, the name of group should be "Group #count". For example, the name of first group would be "Group 1", second would be "Group 2" and so on.
            // Note that a personal chat is not considered a group and the count is not updated for personal chats.
            User admin = users.get(0);
            String name = users.get(1).getName();
            Group group = new Group(name,users.size());
            groupUserMap.put(group,users);

            //Assignin admin to every group
            adminMap.put(group,admin);


            return group;
        }
        customGroupCount++;
        User admin = users.get(0);
        String name = "Group "+customGroupCount;
        Group group = new Group(name,users.size());
        groupUserMap.put(group,users);

        //Assignin admin to every group
        adminMap.put(group,admin);


        return group;

    }

    public int createMessage(String content){
        int messagecount = ++messageId;
        return messagecount;
    }
    public int sendMessage(Message message, User sender, Group group) throws Exception{
        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "You are not allowed to send message" if the sender is not a member of the group

        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }


        if(!groupUserMap.get(group).contains(sender)) {
            throw new Exception("You are not allowed to send message");
        }

        List<Message> messageList = groupMessageMap.getOrDefault(group,new ArrayList<>());
        messageList.add(message);
        groupMessageMap.put(group,messageList);
        int groupMessageCount = messageList.size();
        return groupMessageCount;
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception{
        //Change the admin of the group to "user".
        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "Approver does not have rights" if the approver is not the current admin of the group
        //Throw "User is not a participant" if the user is not a part of the group

//        HashMap<Group, User> adminMap;

        if(!adminMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }
        if(adminMap.get(group).getName()!=approver.getName()){
            throw new Exception("Approver does not have rights");
        }
        if(!groupUserMap.get(group).contains(user)){
            throw new Exception("User is not a participant");
        }

        adminMap.put(group,user);

       return "SUCCESS";
    }

    public int removeUser(User user) throws Exception{
        //If user is not found in any group, throw "User not found" exception
        //If user is found in a group and it is the admin, throw "Cannot remove admin" exception
        //If user is not the admin, remove the user from the group, remove all its messages from all the databases, and update relevant attributes accordingly.

        return 0;
    }

    public String findMessage(Date start, Date end, int K) throws Exception{
        // Find the Kth latest message between start and end (excluding start and end)
        // If the number of messages between given time is less than K, throw "K is greater than the number of messages" exception

        return null;
    }

}
