package api.tests.reqres.users;

import java.util.Date;

public class SuccessUserCreated extends Create {
    private String name;
    private String job;
    private String id;
    private Date createdAt;

    public SuccessUserCreated() {
        super();
    }

    public SuccessUserCreated(String name, String job, String id, Date createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getJob() {
        return job;
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}