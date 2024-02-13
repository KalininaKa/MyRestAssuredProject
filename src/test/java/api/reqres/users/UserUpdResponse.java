package api.reqres.users;

import java.util.Date;

public class UserUpdResponse {
    private Date updatedAt;
    private String name;
    private String job;

    public UserUpdResponse(Date updatedAt, String name, String job) {
        this.updatedAt = updatedAt;
        this.name = name;
        this.job = job;
    }
    public UserUpdResponse (){
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}