package model;

import org.springframework.data.annotation.Id;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by Carlos on 15/2/16.
 */
@Entity @Table(name="Voters")
public class Voter {

    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String email;
    private long pollingStationCode;


    public Voter(String name, String password, String email, long pollingStationCode) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.pollingStationCode = pollingStationCode;
    }

    public Voter(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPollingStationCode() {
        return pollingStationCode;
    }

    public void setPollingStationCode(long pollingStationCode) {
        this.pollingStationCode = pollingStationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean validate(Voter v)
    {
    	return equals(v) && password.equals(v.password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voter voter = (Voter) o;

        return email.equals(voter.getEmail());

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }


}
