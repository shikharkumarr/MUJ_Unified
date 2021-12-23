package Model;

public class Users {
    private String username, password, email, id, search, course, gender, year, section;

    public Users(String username, String password, String email, String id, String search, String course, String gender, String year, String section) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
        this.search = search;
        this.course = course;
        this.gender = gender;
        this.year = year;
        this.section = section;

    }

    public Users() {

    }


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
