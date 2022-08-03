package batchEmail;
import batchEmail.dbTable.Batch_Job_Status;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface Batch_Job_Status_Repository extends JpaRepository<Batch_Job_Status, Long> {
    // 自定義SQL查詢
    @Query(value = "select * from Batch_Job_Status ", nativeQuery = true)
    public List<Batch_Job_Status> selectAll();
    


    @Query(value = "select * from Batch_Job_Status where SEND_MAIL_FLAG in (:flags)", nativeQuery = true)
    public List<Batch_Job_Status> selectUnsent(@Param("flags") String[]  flags);
    @Transactional
    @Modifying
    @Query("update Batch_Job_Status set SOURCE_COUNT = ?1 where BATCH_NAME = ?2")
    int setSOURCE_COUNT(Integer SOURCE_COUNT, String BATCH_NAME);
    
    @Transactional
    @Modifying   
    @Query("update Batch_Job_Status set SEND_MAIL_OBJECT = ?1 where BATCH_NAME = ?2")
    int setReceiver(String SEND_MAIL_OBJECT, String BATCH_NAME);
    
    @Transactional
    @Modifying   
    @Query("update Batch_Job_Status set SEND_MAIL_FLAG = ?1 where BATCH_NAME = ?2")
    int setSEND_MAIL_FLAG(String SEND_MAIL_FLAG, String BATCH_NAME);
    
    @Transactional
    @Modifying   
    @Query("update Batch_Job_Status set UPDATE_TM = CURRENT_TIMESTAMP where BATCH_NAME = ?1")
    int setUPDATE_TM( String BATCH_NAME);
}

