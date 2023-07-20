package Repository;

public class SessionRepository {
    private static Boolean sessionStatus = false;

    public void setSessionStatusActive(){
        sessionStatus = true;
    }
    public void setSessionStatusInactive(){
        sessionStatus = false;
    }

    public Boolean getSessionStatus(){
        return sessionStatus;
    }
}
