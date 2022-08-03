package batchEmail.dbTable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.Setter;

@Entity
@Table(name="BATCH_JOB_STATUS")
@Setter
@Data
public class Batch_Job_Status {

	public Batch_Job_Status() {
		// TODO Auto-generated constructor stub
	}
    @Column(name = "BATCH_CATEGORY")
    private String BATCH_CATEGORY;
    @Id
    @Column(name = "BATCH_NAME")
    private String BATCH_NAME;
    
    @Column(name = "SOURCE_FILE_NAME")
    private String SOURCE_FILE_NAME;

    @Column(name = "SOURCE_COUNT")
    private Integer  SOURCE_COUNT;

    @Column(name = "TARGET_COUNT")
    private Integer  TARGET_COUNT;
    @Column(name = "MAX_DATE")
    private String MAX_DATE;
    @Column(name = "START_TM")
    private Date START_TM;
    @Column(name = "END_TM")
    private Date END_TM;
    @Column(name = "BATCH_STATUS")
    private String BATCH_STATUS;
    @Column(name = "SEND_MAIL_OBJECT")
    private String SEND_MAIL_OBJECT;
    @Column(name = "SEND_MAIL_SUBJECT")
    private String SEND_MAIL_SUBJECT;
    @Column(name = "SEND_MAIL_CONTENT")
    private String SEND_MAIL_CONTENT;
    @Column(name = "SEND_MAIL_FLAG")
    private String SEND_MAIL_FLAG;
    @Column(name = "UPDATE_TM")
    private Date UPDATE_TM;
	public String getBATCH_CATEGORY() {
		return BATCH_CATEGORY;
	}
	public void setBATCH_CATEGORY(String bATCH_CATEGORY) {
		BATCH_CATEGORY = bATCH_CATEGORY;
	}
	public String getBATCH_NAME() {
		return BATCH_NAME;
	}
	public void setBATCH_NAME(String bATCH_NAME) {
		BATCH_NAME = bATCH_NAME;
	}
	public String getSOURCE_FILE_NAME() {
		return SOURCE_FILE_NAME;
	}
	public void setSOURCE_FILE_NAME(String sOURCE_FILE_NAME) {
		SOURCE_FILE_NAME = sOURCE_FILE_NAME;
	}
	public int getSOURCE_COUNT() {
		return SOURCE_COUNT;
	}
	public void setSOURCE_COUNT(int sOURCE_COUNT) {
		SOURCE_COUNT = sOURCE_COUNT;
	}
	public int getTARGET_COUNT() {
		return TARGET_COUNT;
	}
	public void setTARGET_COUNT(int tARGET_COUNT) {
		TARGET_COUNT = tARGET_COUNT;
	}
	public String getMAX_DATE() {
		return MAX_DATE;
	}
	public void setMAX_DATE(String mAX_DATE) {
		MAX_DATE = mAX_DATE;
	}
	public Date getSTART_TM() {
		return START_TM;
	}
	public void setSTART_TM(Date sTART_TM) {
		START_TM = sTART_TM;
	}
	public Date getEND_TM() {
		return END_TM;
	}
	public void setEND_TM(Date eND_TM) {
		END_TM = eND_TM;
	}
	public String getBATCH_STATUS() {
		return BATCH_STATUS;
	}
	public void setBATCH_STATUS(String bATCH_STATUS) {
		BATCH_STATUS = bATCH_STATUS;
	}
	public String getSEND_MAIL_OBJECT() {
		return SEND_MAIL_OBJECT;
	}
	public void setSEND_MAIL_OBJECT(String sEND_MAIL_OBJECT) {
		SEND_MAIL_OBJECT = sEND_MAIL_OBJECT;
	}
	public String getSEND_MAIL_SUBJECT() {
		return SEND_MAIL_SUBJECT;
	}
	public void setSEND_MAIL_SUBJECT(String sEND_MAIL_SUBJECT) {
		SEND_MAIL_SUBJECT = sEND_MAIL_SUBJECT;
	}
	public String getSEND_MAIL_CONTENT() {
		return SEND_MAIL_CONTENT;
	}
	public void setSEND_MAIL_CONTENT(String sEND_MAIL_CONTENT) {
		SEND_MAIL_CONTENT = sEND_MAIL_CONTENT;
	}
	public String getSEND_MAIL_FLAG() {
		return SEND_MAIL_FLAG;
	}
	public void setSEND_MAIL_FLAG(String sEND_MAIL_FLAG) {
		SEND_MAIL_FLAG = sEND_MAIL_FLAG;
	}
	public Date getUPDATE_TM() {
		return UPDATE_TM;
	}
	public void setUPDATE_TM(Date uPDATE_TM) {
		UPDATE_TM = uPDATE_TM;
	}


}





