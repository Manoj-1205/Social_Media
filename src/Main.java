import Repository.FeedRepository;
import Repository.SessionRepository;
import Repository.UserRepository;
import Repository.VotingTracker;
import generators.UserIdGenerator;
import models.Comments;
import models.Post;
import models.SocialNetwork;
import models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static UserRepository userRepository=new UserRepository();
    private static SessionRepository sessionRepository=new SessionRepository();
    private static FeedRepository feedRepository=new FeedRepository();
    private static VotingTracker votingTracker=new VotingTracker();
    public static void main(String[] args){
        User user1=new User(1L, "Manoj", new ArrayList<>(), new ArrayList<>());
        User user2=new User(2L, "Sam", new ArrayList<>(), new ArrayList<>());
        User user3=new User(3L, "Praveen", new ArrayList<>(), new ArrayList<>());
        User user4=new User(4L, "Anto", new ArrayList<>(), new ArrayList<>());
//        Post post=new Post(user1, "Getting 1% Better every day", 0,0,new ArrayList<>());

        Post post1 = Post.builder()
                .user(user1)
                .content("Getting 1% Better every day")
                .build();

        System.out.println("Post created time "+post1.getCreatedTime());

        Post post2 = Post.builder()
                .user(user1)
                .content("Chase your dreams")
                .build();
        Post post3 = Post.builder()
                .user(user2)
                .content("All I want is cake")
                .build();

        Post post4 = Post.builder()
                .user(user3)
                .content("Friends Electrical")
                .build();



        SocialNetwork socialNetwork=new SocialNetwork(userRepository, sessionRepository, feedRepository, votingTracker);

        socialNetwork.postFeed(post1);
        socialNetwork.postFeed(post2);
        socialNetwork.postFeed(post3);
        socialNetwork.postFeed(post4);
//        try {
//            // Wait for 5 seconds
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        socialNetwork.upvotePost(1L, user1);
        socialNetwork.upvotePost(2L, user1);
        socialNetwork.downvotePost(2L, user2);
        socialNetwork.downvotePost(2L, user1);


        //user 1 follows user2
        socialNetwork.follow(user1, user2);
//        socialNetwork.follow(user1, user3);

        //AddComment
        socialNetwork.comment(user1, 1L, "Well Said!");
        socialNetwork.comment(user2, 1L, "We can do it");
        socialNetwork.comment(user2, 3L, "Share the cake");

        socialNetwork.upvotePost(4L, user2);
        socialNetwork.upvotePost(4L, user1);
        socialNetwork.upvotePost(4L, user2);
        socialNetwork.upvotePost(4L, user3);
        //Reply comment

        socialNetwork.reply(user1, 2L, "Great man");

        socialNetwork.showNewsFeed(user1);

        //Console input
        while (true) {


            System.out.println("Enter the commands you want to execute");
            System.out.println("1.signup \n2.login \n3.post \n4.showNewsFeed \n5.follow " +
                                "\n6.showUsers \n7.votePost");
            Scanner sc = new Scanner(System.in);
            String request = sc.nextLine();
            switch (request) {
                case "signup" -> {
                    System.out.println("Enter username: ");
                    String username = sc.nextLine();
                    User user = User.builder().userName(username).build();
                    user.setUserId(UserIdGenerator.nextId());
                    socialNetwork.signup(user);
                }
                case "login" -> {
                    System.out.println("Enter username: ");
                    String name = sc.nextLine();
                    if (!userRepository.getUserMap().containsKey(name)) {
                        System.out.println("Account not found. Please sign up first");
                        break;
                    }
                    socialNetwork.login(userRepository.getUserMap().get(name));
                }
                case "post" -> {
                    System.out.println("Enter the content.");
                    String content = sc.nextLine();
                    Post post = Post.builder()
                            .user(sessionRepository.getCurrentUser())
                            .content(content)
                            .build();
                    socialNetwork.postFeed(post);
                }
                case "showNewsFeed" -> {
                    socialNetwork.showNewsFeed(sessionRepository.getCurrentUser());
                }
                case "follow" -> {
                    System.out.println("Enter the username whom you want to follow");
                    String target = sc.nextLine();
                    if(!userRepository.getUserMap().containsKey(target)){
                        System.out.println("User Not found!");
                        break;
                    }
                    socialNetwork.follow(sessionRepository.getCurrentUser(), userRepository.getUserMap().get(target));

                }
                case "showUsers" -> {
                    userRepository.getUserMap().forEach((key, val) -> System.out.println(val.getUserId()+" "+val.getUserName()));
                }

                case "votePost" -> {
                    System.out.println("Enter the post id to be voted");
                    Long postId = sc.nextLong();
                    System.out.println("1 for upvote \n2 for downvote");
                    int option = sc.nextInt();
                    if(option==1) socialNetwork.upvotePost(postId, sessionRepository.getCurrentUser());
                    else socialNetwork.downvotePost(postId, sessionRepository.getCurrentUser());
                }
                case "comment" -> {
                    System.out.println("Enter the post id to comment");
                    Long postId = sc.nextLong();
                    System.out.println("Enter the content");
                    String content = sc.nextLine();
                    socialNetwork.comment(sessionRepository.getCurrentUser(), postId, content);

                }

            }

        }
    }
}



//        socialNetwork.login(user1);
//        socialNetwork.logout(user1);
//        socialNetwork.login(user1);
//        socialNetwork.login(user1);
//        socialNetwork.login(user2);