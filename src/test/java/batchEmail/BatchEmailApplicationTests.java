package batchEmail;


import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import batchEmail.Batch_Job_Status_Repository;
import batchEmail.dbTable.Batch_Job_Status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.InternetAddress;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
 
import org.apache.logging.log4j.*;




//import org.apache.log4j.PropertyConfigurator;
@SpringBootTest
class BatchEmailApplicationTests {
	@Autowired
	private Batch_Job_Status_Repository batch_Job_Status_Repository;

	@Test
	void contextLoads() {
	}
	
	//log4j
	public enum SEND_MAIL_FLAG {
	    Success, Failure
	}
	@Test
	public void test() {
		System.out.println("he");
//		List<Batch_Job_Status> jobList = batch_Job_Status_Repository.selectAll();
	      	String content ="";
          	
	      	 Properties config = null;

	
    

	}
	
	
	@Test
	public void update() {

	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        System.setProperty("log4jFileName", dateFormat.format(new Date()));
	    
			Logger logger= LogManager.getLogger(BatchEmailApplicationTests.class);
			batch_Job_Status_Repository.setSEND_MAIL_FLAG(SEND_MAIL_FLAG.Failure.name(), "FTP_BRANCH");	
	
			System.out.println("updated");
	}
		@Autowired
		private Environment env;
	   @Autowired
	    private JavaMailSender mailSender;
	    @Test
	    
	    public void sendSimpleMail() throws Exception {
	    	String [] flags= {"Failure","Waiting"};
			List<Batch_Job_Status> jobList = batch_Job_Status_Repository.selectUnsent(flags);
	
			for(Batch_Job_Status j : jobList) {
				String pk=j.getBATCH_NAME();
				if(!pk.equals("FTP_BRANCH")) continue;
				System.out.println(pk);	
		        SimpleMailMessage message = new SimpleMailMessage();
		        message.setFrom(env.getProperty("email_sender"));
		        message.setTo(j.getSEND_MAIL_OBJECT().split(","));
		        message.setSubject(j.getSEND_MAIL_SUBJECT());
		        message.setText((j.getSEND_MAIL_CONTENT()==null)?"testing":j.getSEND_MAIL_CONTENT());
		        mailSender.send(message);
				batch_Job_Status_Repository.setSEND_MAIL_FLAG(SEND_MAIL_FLAG.Success.name(), pk);				
				batch_Job_Status_Repository.setUPDATE_TM(pk);
				break;
			}


			System.out.println("finish");
	    }
}
