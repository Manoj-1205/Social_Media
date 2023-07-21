package Repository;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class SessionRepository {
    private static Boolean sessionStatus = false;
    private Map<User, Boolean> userSessionStatus = new HashMap<>();

    public void setSessionStatusActive(User user){
        userSessionStatus.put(user, true);
    }
    public void setSessionStatusInactive(User user){
        userSessionStatus.put(user, false);
    }

    public Boolean getSessionStatus(User user){
        return userSessionStatus.getOrDefault(user, false);
    }
}
