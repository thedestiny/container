package com.it.servive;

import com.it.dao.IssueDao;
import com.it.dao.UserDao;
import com.it.utils.TimeUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieyue on 2016/6/25.
 * IssueService
 */
public class IssueService {
    private Logger logger = LoggerFactory.getLogger(IssueService.class);

    // 存储问题
    private Map<String, String> queMap = new HashMap<>();
    private IssueDao issueDao = new IssueDao();

    String quepath = "G:/issue/que/";
    String anspath = "G:/issue/ans/";

    /**
     * @param username 提问者
     * @param content  提问内容
     * @return 是否提交成功
     */
    public boolean dealQue(String username, String content) throws Exception {
        //截取前20位
        String quemd5 = DigestUtils.md5Hex(content);
        FileOutputStream outputStream = new FileOutputStream(new File(quepath, quemd5));
        File file = new File(anspath + quemd5);
        if(file.createNewFile()){

        }
        IOUtils.write(content, outputStream);
        outputStream.flush();
        outputStream.close();
        issueDao.insertQue(username,quemd5, TimeUtils.getTime());



        return false;
    }

    /**
     * @param username 回答者
     * @param content  回答内容
     * @return 是否提交成功
     */

    public boolean dealAns(String question, String username, String content) {



        return false;
    }

}
