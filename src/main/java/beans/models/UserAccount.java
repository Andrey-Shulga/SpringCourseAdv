package beans.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "MONEY")
    private double money;

    public UserAccount() {
    }

    public UserAccount(long id, double money) {

        this.id = id;
        this.money = money;
    }

    public UserAccount(double money) {
        this.money = money;
    }

    public UserAccount withId(long id) {
        return new UserAccount(id, money);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addMoney(double money) {
        this.money = this.getMoney() + money;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (id != that.id) return false;
        return Double.compare(that.money, money) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
