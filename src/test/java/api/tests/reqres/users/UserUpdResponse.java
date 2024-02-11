package api.tests.reqres.users;

public class UserUpdResponse {
    private String updatedAt;
    private String name;
    private String job;

    public UserUpdResponse(String updatedAt, String name, String job) {
        this.updatedAt = updatedAt;
        this.name = name;
        this.job = job;
    }
    public UserUpdResponse (){
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
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