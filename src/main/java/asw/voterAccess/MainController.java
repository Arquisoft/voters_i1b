package asw.voterAccess;

import java.io.IOException;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import asw.dbManagement.VoterRepository;
import asw.model.Voter;


@RestController
public class MainController {


    @Autowired
    private VoterRepository voterRep;
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    
    @RequestMapping(value = "/user",consumes = { MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Voter> getVoterInfo(@RequestBody String json) {
    	
    	return authAndOperate((dbVoter)->{
            						System.out.println("POST SERVED");
            						return new ResponseEntity<Voter>(dbVoter, HttpStatus.OK);
            						},json);
    }
    
    @RequestMapping(value = "/changePass",consumes = { MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Voter> changePass(@RequestBody String json) {
    	return authAndOperate((dbVoter)->{
            						System.out.println("POST SERVED");
            						ObjectNode node = null;
            						try {
            							node = new ObjectMapper().readValue(json, ObjectNode.class);
            							} catch (Exception e) {e.printStackTrace(); 
            			            	return new ResponseEntity<Voter>(HttpStatus.INTERNAL_SERVER_ERROR);}
            						dbVoter.setPassword(node.get("newPassword").asText());
            						voterRep.save(dbVoter);
            						return new ResponseEntity<Voter>(HttpStatus.OK);
         	  					    },json); 
    }
    
    private ResponseEntity<Voter> authAndOperate(Function<Voter,ResponseEntity<Voter>> f, String json)
    {
    	Voter voter;
    	try {
            voter = JSON_MAPPER.readValue(json, Voter.class);
            Voter dbVoter = voterRep.findByEmail(voter.getEmail());
            if(voter.validate(dbVoter))
            {
            	return f.apply(dbVoter);
            }
          } catch (Exception e) {
            	System.out.println("POST Failed");
            	e.printStackTrace();        	
            	return new ResponseEntity<Voter>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
                return new ResponseEntity<Voter>(HttpStatus.NOT_FOUND);
    	
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