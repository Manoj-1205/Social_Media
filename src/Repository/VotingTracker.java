package Repository;

import lombok.Builder;
import models.User;

@Builder
public class VotingTracker {

    private Boolean upvoted=false;
    private Boolean downvoted=false;
    private static User user;

    public void upvote(){
        if(upvoted) return;
        if(downvoted){
            downvoted=false;
        }
        upvoted=true;
    }
    public void downvote(){
        if(downvoted) return;
        if(upvoted){
            upvoted=false;
        }
        downvoted=true;
    }
}
