package com.framework.upload.web;

import com.framework.properties.Properties;
import com.framework.response.AjaxResult;
import com.framework.upload.FileStatusEnum;
import com.framework.upload.entity.UploadFile;
import com.framework.upload.service.UploadFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private Properties properties;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 文件上传
     *
     * @param file
     * @param entryCode
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public AjaxResult upload(@RequestPart("file") MultipartFile file, String entryCode) {
        String dir = properties.getValue("upload.dir");
        String date_dir = new SimpleDateFormat("yyyyMM").format(new Date());
        try {
            File file_dir = new File(dir + date_dir);
            if (!file_dir.exists()) {
                file_dir.mkdirs();
            }
            String realFileName = file.getOriginalFilename();
            String saveFileName = String.valueOf(System.nanoTime());

            String file_path = dir + date_dir + File.separator + saveFileName;
            //将文件保存到指定目录
            file.transferTo(new File(file_path));

            if (StringUtils.isEmpty(entryCode)) {
                entryCode = saveFileName;
            }

            //保存数据库记录
            UploadFile uploadFile = new UploadFile();
            uploadFile.setCreateTime(new Date());
            uploadFile.setRealFileName(realFileName);
            uploadFile.setSaveFilePath(date_dir);
            uploadFile.setSaveFileName(saveFileName);
            uploadFile.setEntryCode(entryCode);
            uploadFile.setState(FileStatusEnum.NOSAVE.getValue());
            uploadFileService.save(uploadFile);

            return AjaxResult.successObject(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("文件上传出错.");
        }
    }

    /**
     * 查询entityCode下对应附件列表
     *
     * @param entityCode
     * @return
     */
    @RequestMapping("/list/{entityCode}")
    @ResponseBody
    public AjaxResult list(@PathVariable String entityCode) {
        if (!StringUtils.isEmpty(entityCode)) {
            List<UploadFile> list = uploadFileService.getListByEntryCode(entityCode);
            return AjaxResult.successObject(list);
        }
        return AjaxResult.success();
    }

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable Long id) {
        UploadFile file = uploadFileService.findOne(id);
        if (file != null) {
            uploadFileService.delete(id);
        }
        return AjaxResult.success();
    }

    /**
     * 文件下载
     *
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> download(@PathVariable Long id) throws IOException {
        UploadFile file = uploadFileService.findOne(id);
        if (file != null) {
            String dir = properties.getValue("upload.dir");
            String file_path = dir + file.getSaveFilePath() + File.separator + file.getSaveFileName();
            File download_file = new File(file_path);
            if (download_file.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getRealFileName(), "UTF-8"));
                return new ResponseEntity<>(FileUtils.readFileToByteArray(download_file), headers, HttpStatus.CREATED);
            }
        }
        return null;
    }
}
