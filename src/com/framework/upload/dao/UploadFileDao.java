package com.framework.upload.dao;

import com.framework.dao.BaseDao;
import com.framework.upload.entity.UploadFile;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
public interface UploadFileDao extends BaseDao<UploadFile, Long> {

    @Query("from UploadFile where entryCode = ?1")
    List<UploadFile> getListByEntryCode(String enrtyCode);
}
