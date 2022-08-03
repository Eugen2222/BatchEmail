package batchEmail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import batchEmail.dbTable.Batch_Job_Status;



@SpringBootApplication

public class BatchEmailApplication implements CommandLineRunner {
	@Autowired
	private Environment env;
	@Autowired
    private JavaMailSender mailSender;
	@Autowired
	private Batch_Job_Status_Repository batch_Job_Status_Repository;
   	
	final Logger logger= LoggerFactory.getLogger(BatchEmailApplication.class);
	private enum  SEND_MAIL_FLAG {
	    Success, Failure
	}
	

    @Override
    public void run(String... args) throws Exception {

		try {
			
			if(env.getProperty("test_mod").equals("true")) {
				SimpleMailMessage message = new SimpleMailMessage();
		        message.setFrom(env.getProperty("email_sender"));
		        logger.info("Try to send test email.");
		        message.setTo(env.getProperty("test_email_receiver").replace(" ", "").split(","));
		        message.setSubject("CUB MAIL SERVER TEST");
		        message.setText("CUB MAIL SERVER TEST");
		        mailSender.send(message);
		        logger.info("Test email has been sent to "+env.getProperty("test_email_receiver").replace(" ", ""));
				System.out.println("Test Finish");
		        logger.info("Try to test check email flag.");
		    	test_update();
		    	
			}

			sendSimpleMail();
			logger.info("All tasks have been completed.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    }
	
	public static void main(String[] args) {
//		SpringApplication.run(BatchEmailApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(BatchEmailApplication.class, args);
		

        System.exit(SpringApplication.exit(context, () -> 0));
	        // do something
		
	}
	

	

	public void test_update() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.setProperty("log4jFileName", dateFormat.format(new Date()));
    
        logger.info("Try to set FTP_BRANCH SEND_MAIL_FLAG to Failure.");
		batch_Job_Status_Repository.setSEND_MAIL_FLAG(SEND_MAIL_FLAG.Failure.name(), "FTP_BRANCH");	

		System.out.println("updated");
	}

	public void sendSimpleMail(){
    	String [] flags= {"Failure","Waiting"};
		List<Batch_Job_Status> jobList = batch_Job_Status_Repository.selectUnsent(flags);

		for(Batch_Job_Status j : jobList) {
			String pk=j.getBATCH_NAME();
//			if(!pk.equals("FTP_BRANCH")) continue;

			logger.info("Try to send email for db table:\"" +pk+"\".");
			try {
//	        SimpleMailMessage message = new SimpleMailMessage();
	        MimeMessage message = mailSender.createMimeMessage();
	        message.setFrom(env.getProperty("email_sender"));
	        String [] addresses=j.getSEND_MAIL_OBJECT().replace(" ", "").split(",");
	      
	        for(String to:addresses) {
	            message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
	        }
	   
	        message.setSubject(j.getSEND_MAIL_SUBJECT());
	        String content="";
	        if (j.getSEND_MAIL_CONTENT()==null) {
	        	content="No email content on status table.";
	        }else {
	        	content=j.getSEND_MAIL_CONTENT();
	        }
	        message.setText(content, "UTF-8");
//	        message.setContent(content, "text/html");
	        mailSender.send(message);
			batch_Job_Status_Repository.setSEND_MAIL_FLAG(SEND_MAIL_FLAG.Success.name(), pk);				
			batch_Job_Status_Repository.setUPDATE_TM(pk);
//			break;
			logger.info("Completed.");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
	            logger.error(e.getMessage(),e);
				
			}
		}
        

    
	}
}
