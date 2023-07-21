import Repository.FeedRepository;
import Repository.SessionRepository;
import Repository.UserRepository;
import Repository.VotingTracker;
import models.Post;
import models.SocialNetwork;
import models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    private static UserRepository userRepository=new UserRepository();
    private static SessionRepository sessionRepository=new SessionRepository();
    private static FeedRepository feedRepository=new FeedRepository();
    private static VotingTracker votingTracker=new VotingTracker();
    public static void main(String[] args){
        User user1=new User(1L, "Manoj", new ArrayList<>(), new ArrayList<>());
        User user2=new User(2L, "Sam", new ArrayList<>(), new ArrayList<>());
//        Post post=new Post(user1, "Getting 1% Better every day", 0,0,new ArrayList<>());

        Post post1 = Post.builder()
                .user(user1)
                .content("Getting 1% Better every day")
                .build();

        System.out.println("Post created time "+post1.getCreatedTime());
        System.out.println("Duration "+post1.postCreatedTime(post1.getCreatedTime(), LocalDateTime.now()));
        Post post2 = Post.builder()
                .user(user1)
                .content("Chase your dreams")
                .build();
        Post post3 = Post.builder()
                .user(user2)
                .content("All I want is cake")
                .build();

        SocialNetwork socialNetwork=new SocialNetwork(userRepository, sessionRepository, feedRepository, votingTracker);

        socialNetwork.postFeed(post1);
        socialNetwork.postFeed(post2);
        socialNetwork.postFeed(post3);
//        try {
//            // Wait for 5 seconds
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        socialNetwork.showNewsFeed();
        socialNetwork.upvotePost(1L, user1);
        socialNetwork.upvotePost(2L, user1);
        socialNetwork.downvotePost(2L, user2);
        socialNetwork.downvotePost(2L, user1);
        socialNetwork.showNewsFeed();

        socialNetwork.follow(user1, user1);

    }
}



//        socialNetwork.login(user1);
//        socialNetwork.logout(user1);
//        socialNetwork.login(user1);
//        socialNetwork.login(user1);
//        socialNetwork.login(user2);