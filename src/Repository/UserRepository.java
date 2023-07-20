package Repository;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static List<User> userList=new ArrayList<>();
    public void save(User user){
        userList.add(user);
    }
    public List<User> getUserList(){
        return userList;
    }
}
