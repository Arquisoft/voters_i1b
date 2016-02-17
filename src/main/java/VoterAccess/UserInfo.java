package VoterAccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfo {
	
	private static final Logger log = LoggerFactory.getLogger(UserInfo.class);

    private final String name;
    private final Integer age;

    private final String email;
    private final String password;

    private String votingStation;
    public UserInfo(String name, Integer age,String email, String password) {
    	log.info("Creating user " + name + ". age: " + age);
        this.name = name;
        this.age = age;
        this.email= email;
        this.password= password;
    }
    public String getVotingStation() {
    	return votingStation;
    }
    
    public void setVotingStation(String votingStation) {
    	this.votingStation = votingStation;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}