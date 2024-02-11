package api.tests.reqres.registration;

import api.tests.reqres.registration.Register;

public class UnsuccessUserReg extends Register {
    private String error;
    public UnsuccessUserReg() {
    }

    public UnsuccessUserReg(String email, String password, String error) {
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

