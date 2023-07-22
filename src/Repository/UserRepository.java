package Repository;

import lombok.Getter;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class UserRepository {
    private static List<User> userList=new ArrayList<>();
    private static Map<String, User> userMap=new HashMap<>();


    public  void save(User user){
        userList.add(user);
        userMap.put(user.getUserName(), user);
    }
    public  List<User> getUserList(){
        return userList;
    }

    public Map<String, User>  getUserMap(){
        return userMap;
    }


    public void follow(User user, User target){
        if(user==target) {
            System.out.println("Following action cannot be performed, You cannot follow yourself ");
            return;
        }
        if(user.getFollowers().contains(target)){
            System.out.println(user.getUserName()+" is already following "+target.getUserName());
            return;
        }
        target.getFollowers().add(user);
        user.getFollowing().add(target);
        System.out.println("You are following "+target.getUserName()+" now.");
    }
}
