package domain;

public class LibraryUser {
    private int user_id;
    private String user_name;
    private String user_email;
    private String user_password;
    private boolean staff_member;

    public LibraryUser(){

    }

    public LibraryUser(int user_id, String user_name, String user_email, String user_password, boolean staff_member){
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        this.staff_member = staff_member;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public boolean isStaff_member() {
        return staff_member;
    }

    public void setStaff_member(boolean staff_member) {
        this.staff_member = staff_member;
    }
}
