package Repository;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class SessionRepository {
    private static Boolean sessionStatus = false;
    private Map<User, Boolean> userSessionStatus = new HashMap<>();
    private User currentUser;
    public void setSessionStatusActive(User user){
        userSessionStatus.put(user, true);
        currentUser=user;
    }
    public User getCurrentUser(){
        return currentUser;

    }
    public void setSessionStatusInactive(User user){
        userSessionStatus.put(user, false);
        currentUser=null;
    }

    public Boolean getSessionStatus(User user){
        return userSessionStatus.getOrDefault(user, false);
    }

    public Boolean isLoggedIn(User user){
        return getSessionStatus(user);
    }
}
