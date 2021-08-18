package codefellowshipexample.codefellowship;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String myName;
    @Column(unique = true)
    private String userName;

    @ManyToOne
    private ApplicationUser addingUser;

    public Feed(){

    }
    public Feed(String myName,String userName,ApplicationUser addingUser) {
        this.myName=myName;
        this.userName =  userName;
        this.addingUser=addingUser;

    }
    public int getId() {

        return id;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ApplicationUser getAddingUser() {
        return addingUser;
    }

    public void setAddingUser(ApplicationUser addingUser) {
        this.addingUser = addingUser;
    }
    //    @ManyToOne
//    private Post feedUser;
//    public Feed(){
//
//    }
//
//    public Feed(List postShow, Post feedUser) {
//        this.postShow = postShow;
//        this.feedUser = feedUser;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public List getPostShow() {
//        return postShow;
//    }
//
//    public void setPostShow(List postShow) {
//        this.postShow = postShow;
//    }
//
//    public Post getFeedUser() {
//        return feedUser;
//    }
//
//    public void setFeedUser(Post feedUser) {
//        this.feedUser = feedUser;
//    }
}
