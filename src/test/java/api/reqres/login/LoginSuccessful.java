package api.reqres.login;

public class LoginSuccessful {
    private String token;

    public LoginSuccessful(String token) {
        this.token = token;
    }

    public LoginSuccessful(){}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
