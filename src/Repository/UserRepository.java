package Repository;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static List<User> userList=new ArrayList<>();
    public  void save(User user){
        userList.add(user);
    }
    public   List<User> getUserList(){
        return userList;
    }
}
