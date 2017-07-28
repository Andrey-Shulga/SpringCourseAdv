package beans.models;

import util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {"id", "email", "name", "birthday", "password", "role", "userAccount"})
public class User implements Serializable{

    private long id;
    private String email;
    private String name;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate birthday;
    private String password;
    private String role;
    private UserAccount userAccount;

    public User() {
    }

    public User(long id, String email, String name, LocalDate birthday, String password, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.password=password;
        this.role=role;

    }

    public User(String email, String name, LocalDate birthday, String password, String role) {
        this(-1, email, name, birthday, password, role);
    }

    public User withId(long id) {
        return new User(id, email, name, birthday, password, role);
    }

    public User(long id) {
        this.id = id;
    }

    public User(beans.soap.com.epam.User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthday = LocalDate.parse(user.getBirthday(), formatter);
        this.password = user.getPassword();
        this.role = user.getRole();
        this.userAccount = new UserAccount(user.getUserAccount());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return userAccount != null ? userAccount.equals(user.userAccount) : user.userAccount == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userAccount=" + userAccount +
                '}';
    }
}
