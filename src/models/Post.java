package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder

public class Post {
    private Long postId;
    private User user;
    private String content;

    private List<Comments> commentList;

    @Builder.Default
    private LocalDateTime createdTime = LocalDateTime.now();
    @Builder.Default
    private Integer upvotes=0;
    @Builder.Default
    private Integer downvotes=0;



    public String postCreatedTime(LocalDateTime currentTime, LocalDateTime createdTime){
        Duration duration = Duration.between(createdTime, currentTime);
        long days = duration.toDays();
        if(days>0) return days + "day ago";
        long hours = duration.toHours();
        if(hours>0) return hours + "hr ago";
        long minutes = duration.toMinutes();
        if(minutes>0) return minutes + "m ago";
        long seconds = duration.getSeconds();
        return seconds + "sec ago";

    }
}
