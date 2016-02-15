package model;

import org.springframework.data.annotation.Id;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by Carlos on 15/2/16.
 */
@Entity
public class Voter {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nif;
    @Column(nullable = false)
    private String email;
    private long code;


    public Voter(long id, String password, String name, String email, String nif, long code) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.nif = nif;
        this.code = code;
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

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voter voter = (Voter) o;

        return nif != null ? nif.equals(voter.nif) : voter.nif == null;

    }

    @Override
    public int hashCode() {
        return nif != null ? nif.hashCode() : 0;
    }


}
