package asw.model;

// import org.springframework.data.annotation.Id;
// import javax.persistence.Id;

import javax.persistence.*;


@Entity 
@Table(name="Voters")
public class Voter {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;    
	
    private String name;    
    private String password;
    
    @Column(unique=true)
    private String email;
    @Column(unique=true)
    private String nif;
    
    private String pollingStationCode;


    public Voter(String name, String password, String email, String pollingStationCode, String nif) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.pollingStationCode = pollingStationCode;
        this.setNif(nif);
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

    public String getPollingStationCode() {
        return pollingStationCode;
    }

    public void setPollingStationCode(String pollingStationCode) {
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
        return nif.hashCode();
    }

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}


}
