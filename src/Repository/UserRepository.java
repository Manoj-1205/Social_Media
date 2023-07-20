package Repository;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static List<User> userList=new ArrayList<>();
    public static void save(User user){
        userList.add(user);
    }
    public static  List<User> getUserList(){
        return userList;
    }
}
