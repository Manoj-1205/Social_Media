import Repository.FeedRepository;
import Repository.SessionRepository;
import Repository.UserRepository;
import Repository.VotingTracker;
import generators.UserIdGenerator;
import models.Post;
import models.SocialNetwork;
import models.User;

import java.util.Scanner;

public class Main {
    private static final UserRepository userRepository=new UserRepository();
    private static final SessionRepository sessionRepository=new SessionRepository();
    private static final FeedRepository feedRepository=new FeedRepository();
    private static final VotingTracker votingTracker=new VotingTracker();
    public static void main(String[] args){

        SocialNetwork socialNetwork=new SocialNetwork(userRepository, sessionRepository, feedRepository, votingTracker);
        //Console input
        while (true) {

            if(sessionRepository.getCurrentUser()!=null){
                System.out.println("\nCURRENT USER : "+sessionRepository.getCurrentUser().getUserName());
            }
            System.out.println("\n");
            System.out.println("""
                    1.signup\s
                    2.login\s
                    3.post\s
                    4.newsfeed\s
                    5.follow\s
                    6.showUsers\s
                    7.votePost\s
                    8.comment\s
                    9.reply\s
                    10.voteComment""");
            System.out.println("\nEnter the commands you want to execute");
            Scanner sc = new Scanner(System.in);
            String request = sc.nextLine();
            try {


                switch (request) {
                    case "signup" -> {
                        System.out.println("Enter username:  ");
                        String username = sc.nextLine();
                        User user = User.builder().userName(username).build();
                        user.setUserId(UserIdGenerator.nextId());
                        socialNetwork.signup(user);
                    }
                    case "login" -> {
                        System.out.println("Enter username: ");
                        String name = sc.nextLine();
                        if (sessionRepository.getCurrentUser() != null) {
                            socialNetwork.logout(sessionRepository.getCurrentUser());
                        }
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
                    case "newsfeed" -> {
                        socialNetwork.showNewsFeed(sessionRepository.getCurrentUser());
                    }
                    case "follow" -> {
                        System.out.println("Enter the username whom you want to follow");
                        String target = sc.nextLine();
                        if (!userRepository.getUserMap().containsKey(target)) {
                            System.out.println("User Not found!");
                            break;
                        }
                        socialNetwork.follow(sessionRepository.getCurrentUser(), userRepository.getUserMap().get(target));

                    }
                    case "showUsers" -> {
                        userRepository.getUserMap().forEach((key, val) -> System.out.println(val.getUserId() + " " + val.getUserName()));
                    }

                    case "votePost" -> {
                        System.out.println("Enter the post id to be voted");
                        Long postId = sc.nextLong();
                        System.out.println("1 for upvote \n2 for downvote");
                        int option = sc.nextInt();
                        if (option == 1) socialNetwork.upvotePost(postId, sessionRepository.getCurrentUser());
                        else socialNetwork.downvotePost(postId, sessionRepository.getCurrentUser());
                    }
                    case "comment" -> {
                        System.out.println("Enter the post id to comment");
                        Long postId = sc.nextLong();
                        System.out.println("Enter the content ");
//                    String test = sc.nextLine();
                        String context = sc.next();
//                    System.out.println("CONTENT -> "+test);
                        socialNetwork.comment(sessionRepository.getCurrentUser(), postId, context);

                    }
                    case "reply" -> {
                        System.out.println("Enter the comment Id to reply");
                        Long commentId = sc.nextLong();
                        System.out.println("Enter the content");
                        String content = sc.nextLine();
                        String test = sc.nextLine();
                        socialNetwork.reply(sessionRepository.getCurrentUser(), commentId, test);
                    }

                    case "voteComment" -> {
                        System.out.println("Enter the comment id to be voted");
                        Long commentId = sc.nextLong();
                        System.out.println("1 for upvote \n2 for downvote");
                        int option = sc.nextInt();
                        if (option == 1) socialNetwork.upvoteComment(commentId, sessionRepository.getCurrentUser());
                        else socialNetwork.downvoteComment(commentId, sessionRepository.getCurrentUser());
                    }

                    default -> {
                        System.out.println("Invalid option. Please type complete command (Example : signup)");
                    }

                }
            }catch (Exception e){};

        }
    }
}
