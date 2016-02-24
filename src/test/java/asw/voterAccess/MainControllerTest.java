package asw.voterAccess;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import asw.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class MainControllerTest {

    @Value("${local.server.port}")
    private int port;

    private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	@Test
	public void getLanding() throws Exception {
		String userURI = base.toString();  
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), equalTo("<!DOCTYPE html>"+
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
				"</html>"));
	}
	
	@Test
	public void getUser() throws Exception {
		String userURI = base.toString() + "/user"; 
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
	}

}