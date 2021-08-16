package codefellowshipexample.codefellowship;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String body;
    private String dataTime;
    @ManyToOne
    private ApplicationUser addingPost;

    public Post(){

    }

    public Post(String body,String dataTime, ApplicationUser addingPost) {
        this.body = body;
        this.dataTime=dataTime;
        this.addingPost = addingPost;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ApplicationUser getAddingPost() {
        return addingPost;
    }

    public void setAddingPost(ApplicationUser addingPost) {
        this.addingPost = addingPost;
    }
}
