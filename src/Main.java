import Repository.SessionRepository;
import Repository.UserRepository;
import models.SocialNetwork;
import models.User;

public class Main {
    private static UserRepository userRepository=new UserRepository();
    private static SessionRepository sessionRepository=new SessionRepository();

    public static void main(String[] args){
        User user=new User(1L, "Manoj");
        SocialNetwork socialNetwork=new SocialNetwork(userRepository, sessionRepository);
        socialNetwork.login(user);
        socialNetwork.logout(user);
        socialNetwork.login(user);
        socialNetwork.login(user);
    }
}