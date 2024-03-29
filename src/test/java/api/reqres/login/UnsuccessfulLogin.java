package api.reqres.login;

import api.reqres.registration.Register;

public class UnsuccessfulLogin extends Register {
    private String error;
    public UnsuccessfulLogin() {
    }

    public UnsuccessfulLogin(String email, String password, String error) {
        super(email, password);
        this.error = error;
    }
    public String getError() {
        return error;
    }

    public void setError(String error){
        this.error = error;

    }

    public String getEmail(){
        return super.getEmail();
    }

    public void setEmail(String email){
        super.setEmail(email);
    }

    public String getPassword(){
        return super.getPassword();
    }

    public void setPassword(String password){
        super.setPassword(password);
    }
}

