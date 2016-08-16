package in.hocg.chat.service;

import in.hocg.chat.pojo.FilesPojo;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-8-9.
 */
public class FilesService extends BaseService<FilesPojo> {

    public void insert(FilesPojo filesPojo) {
        filesPojo.setCreateTime(now());
        dao().insert(filesPojo);
    }
}
