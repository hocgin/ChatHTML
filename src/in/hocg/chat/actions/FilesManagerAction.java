package in.hocg.chat.actions;

import in.hocg.chat.Custom;
import in.hocg.chat.pojo.FilesPojo;
import in.hocg.chat.service.FilesService;
import org.nutz.lang.Files;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.eclipse.jdt.internal.compiler.util.Util.UTF_8;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-9.
 */
@Ok("raw")
@At("/f")
public class FilesManagerAction extends BaseAction<FilesService> {

    /**
     * 文件上传
     * @param file
     * @param session
     * @return
     */
    @Ok("json:full") // file max size 10M
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp", "8192", "utf-8", "20000", "10485760"})
    @POST
    @At("/upload")
    public Custom.Data upload(@Param("file") TempFile file,
                              HttpSession session) {
        File target = null;
        if (file == null) {
            return Custom.Data.NEW(403);
        }
        String dirPath = String.format("%s/%s",
                Custom.DirPath.getDirPath(Custom.DirPath.Flag.File),
                file.getContentType());
        String rename = String.format("%s#%s",
                String.valueOf(System.currentTimeMillis()),
                file.getSubmittedFileName());
        FilesPojo filesPojo = new FilesPojo();
        try {
            target = new File(String.format("%s/%s", dirPath, rename));
            Files.move(file.getFile(), target);
            // Dao
            filesPojo.setCreator(((String) session.getAttribute(Custom.Session.USER)));
            filesPojo.setFormerly(file.getSubmittedFileName());
            filesPojo.setNow(rename);
            filesPojo.setKeepPath(dirPath);
            filesPojo.setType(file.getContentType());
            service().insert(filesPojo);
        } catch (IOException e) {
            target.deleteOnExit();
            e.printStackTrace();
        }
        return Custom.Data.SUCCESS().setData(String.valueOf(filesPojo.getId()));
    }

    /**
     * 文件下载 || 查看
     * @param uuid
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @Ok("raw")
    @At("/?")
    @GET
    public Object visitor(String uuid, HttpServletResponse response) throws UnsupportedEncodingException {
        FilesPojo filesPojo = service().fetch(uuid);
        if (filesPojo != null) {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filesPojo.getFormerly(), UTF_8));
            File file = new File(String.format("%s/%s", filesPojo.getKeepPath(), filesPojo.getNow()));
            if (file.exists()) {
                return file;
            }
        }
        return Custom.Data.NEW(404);
    }
}
