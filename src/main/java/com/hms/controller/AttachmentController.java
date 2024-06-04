package com.hms.controller;
import com.hms.entity.HomeworkAttachment;
import com.hms.service.HomeworkAttachmentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AttachmentController {
    @Resource
    private HomeworkAttachmentService homeworkAttachmentService;
    @RequestMapping(
            value = "/attachment/download-attachment",
            method = RequestMethod.GET
    )
    public void downloadAttachment(
            @RequestParam(value = "id") Integer id,
            HttpServletResponse resp
    ) {
        HomeworkAttachment homeworkAttachment = homeworkAttachmentService.selectHomeworkAttachmentByHomeworkId(id);
        String title = homeworkAttachment.getTitle();
        String attachment = homeworkAttachment.getAttachment();
        if (attachment != null && attachment.length() > 0) {
            File file = new File(attachment);
            if (file.exists() == true) {
                resp.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(title, StandardCharsets.UTF_8));
                resp.setContentType("application/force-download");
                byte[] buffer = new byte[8192];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = resp.getOutputStream();
                    int size = bis.read(buffer);
                    while (size != -1) {
                        os.write(buffer, 0, size);
                        size = bis.read(buffer);
                    }
                }
                catch (Exception exc) {
                    exc.printStackTrace();
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        }
                        catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        }
                        catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}