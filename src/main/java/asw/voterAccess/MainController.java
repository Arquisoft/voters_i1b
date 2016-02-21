package asw.voterAccess;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import asw.dbManagement.VoterRepository;
import asw.model.Voter;


@RestController
public class MainController {


    @Autowired
    private VoterRepository voterRep;
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    
    @RequestMapping(value = "/user",consumes = { MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getVoterInfo(@RequestBody String json) {
            //If the user on the input is the same as the one in the db and credentials are ok
            //return user info and HTTP response
    	Voter voter;
    	try {
            voter = JSON_MAPPER.readValue(json, Voter.class);
            Voter dbVoter = voterRep.findByEmail(voter.getEmail());
            if(voter.validate(dbVoter)){
            	System.out.println("POST SERVED");
         	  	return new ResponseEntity<String>("{\"pollingStationCode\":\""+dbVoter.getPollingStationCode()+"\"}", HttpStatus.OK);}       
     	
        } catch (Exception e) {
        	System.out.println("POST Failed");
        	e.printStackTrace();        	
        	return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
       // return this.vService.findByEmailAndPassword(userInfo.getEmail(),userInfo.getPassword());
    }

    @RequestMapping("/")
    public String landing() {
        return "<!DOCTYPE html>"+
"<html lang=\"en\">"+
"	<head>"+
"		<meta charset=\"UTF-8\">"+
"		<title>Voting System</title>"+
"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>		"+
"	</head>"+
"	<body>"+
"		<form id=form>"+
"			<label for=\"_user_email\">Email:</label>"+
"			<input id= \"_user_email\" type=\"text\" name=\"email\"><br>"+
"			<label for=\"_user_pass\">Password:</label>"+
"			<input id=\"_user_pass\" type=\"password\" name=\"password\"><br> "+
"			<input type=\"button\" value=\"Submit\" onclick=\"submitform()\" />"+
"		</form>"+
"		<div id=code></div>"+
"		"+
"		"+
"		<script type=\"text/javascript\">	"+
"		"+
"		$.fn.serializeObject = function()"+
"		{"+
"		   var o = {};"+
"		   var a = this.serializeArray();"+
"		   $.each(a, function() {"+
"		       if (o[this.name]) {"+
"		           if (!o[this.name].push) {"+
"		               o[this.name] = [o[this.name]];"+
"		           }"+
"		           o[this.name].push(this.value || '');"+
"		       } else {"+
"		           o[this.name] = this.value || '';"+
"		       }"+
"		   });"+
"		   return o;"+
"		};"+
"					"+
"    		function submitform(){"+
"    			var frm = $(\"form\");"+
"    			var data = JSON.stringify(frm.serializeObject());"+
"        		var xhttp = new XMLHttpRequest();"+
"        		xhttp.onreadystatechange = function() {"+
"        		    if (xhttp.readyState == 4 && xhttp.status == 200) {"+
"        		      document.getElementById(\"code\").innerHTML = xhttp.responseText;"+
"        		    }"+
"        		  };"+
"        		xhttp.open(\"POST\", \"user\", true);"+
"        		xhttp.setRequestHeader(\"Content-Type\", \"application/json\");"+
"        		xhttp.send(data);"+
"        		}"+
"        </script>"+
"	</body>"+
"</html>";
	

    }
    
    @RequestMapping("/probarDB")
    public String probardb() {
        
    	try{
    		Voter v = new Voter("Daniel Cuesta Suarez","1234","dani@example.com","123456","71776420K");
    		System.out.println(voterRep.save(v));
    	}catch(Exception e){e.printStackTrace();}    	
    	
    	return "Done";
    }
}