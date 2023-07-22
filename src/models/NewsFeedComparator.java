package models;

import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

@AllArgsConstructor

public class NewsFeedComparator implements Comparator<Post> {
    private User currentUser;


    @Override
    public int compare(Post post1, Post post2) {
        // Check if the posts are from followed users
        boolean isPost1Followed = isFollowedUser(currentUser, post1.getUser());
        boolean isPost2Followed = isFollowedUser(currentUser, post2.getUser());

        // Followed users appear first
        if (isPost1Followed && !isPost2Followed) {
            return -1;
        } else if (!isPost1Followed && isPost2Followed) {
            return 1;
        }

        // Higher score (upvotes - downvotes) appears first
        int score1 = post1.getUpvotes() - post1.getDownvotes();
        int score2 = post2.getUpvotes() - post2.getDownvotes();
        if (score1 > score2) {
            return -1;
        } else if (score1 < score2) {
            return 1;
        }

        //Higher number of comments appears first
        if (post1.getCommentList().size() > post2.getCommentList().size()) {
            return -1;
        } else if (post1.getCommentList().size() < post2.getCommentList().size()) {
            return 1;
        }


        Duration timeDifference1 = Duration.between(post1.getCreatedTime(), LocalDateTime.now());
        Duration timeDifference2 = Duration.between(post2.getCreatedTime(), LocalDateTime.now());
        if (timeDifference1.compareTo(timeDifference2) < 0) {
            return -1;
        } else if (timeDifference1.compareTo(timeDifference2) > 0) {
            return 1;
        }


        return Long.compare(post1.getPostId(), post2.getPostId());
    }

    private boolean isFollowedUser(User currentUser, User user) {
        return currentUser.getFollowing().contains(user);
    }
}