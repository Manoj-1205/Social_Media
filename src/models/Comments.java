package models;

import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter

public class Comments {
    private Long commentId;
    private Long postId;
    private User author;
    private String content;

    @Builder.Default
    private LocalDateTime timeStamp = LocalDateTime.now();
    @Builder.Default
    List<Comments> replyList = new ArrayList<>();
    @Builder.Default
    private Integer upvote=0;
    @Builder.Default
    private Integer downvote=0;

    public String commentCreatedTime(LocalDateTime currentTime, LocalDateTime createdTime){
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
