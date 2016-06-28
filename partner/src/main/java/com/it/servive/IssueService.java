package com.it.servive;

import com.it.dao.IssueDao;
<<<<<<< HEAD
import com.it.dao.UserDao;
import com.it.utils.TimeUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
=======
import com.it.entity.Answer;
import com.it.entity.Issue;
import com.it.utils.CacheUtils;
import com.it.utils.SmallUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
>>>>>>> open
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
<<<<<<< HEAD
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
=======
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> open

/**
 * Created by xieyue on 2016/6/25.
 * IssueService
 */
public class IssueService {
    private Logger logger = LoggerFactory.getLogger(IssueService.class);

    // 存储问题
<<<<<<< HEAD
    private Map<String, String> queMap = new HashMap<>();
    private IssueDao issueDao = new IssueDao();

    String quepath = "G:/issue/que/";
    String anspath = "G:/issue/ans/";

=======
    private static IssueDao issueDao = new IssueDao();
    private static Logger logger = LoggerFactory.getLogger(IssueService.class);
    // 存储问题
    private static String quePath = "G:/issue/que";
    // 存储答案
    private static String ansPath = "G:/issue/ans/";
    List<String> ansList = new ArrayList<>();
    private static List<String> queList = new ArrayList<>();
    private static List<String> temple = new ArrayList<>();

>>>>>>> open
    /**
     * @param username 提问者
     * @param content  提问内容
     * @return 是否提交成功
     */
<<<<<<< HEAD
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
=======
    public boolean dealQue(String username, String content) throws IOException {
        // 计算问题内容的md5值 所有的问题装在一个que文件中,每个问题提出时就创建一个以
        // 问题内容md5值为名称的文件存储答案，并且放在anspath文件夹下。
        String question = DigestUtils.md5Hex(content);
        String time = SmallUtils.getTime();
        File fileQue = new File(quePath);
        File fileAns = new File(ansPath, question);
        if (!fileAns.exists() && fileAns.createNewFile()) {
            logger.debug("  create " + question + " file !");
        }
        // question+++username+++content+++time
        String saveQue = question + "+++" + username + "+++" + content + "+++" + time;
        logger.debug("  :" + saveQue);
        // 清除问题列表的缓存
        CacheUtils.remove("queList");
        logger.debug("queList is {}",CacheUtils.get("queList"));
        temple.add(saveQue);
        try {
            FileUtils.writeLines(fileQue, temple, true);
            temple.clear();
            return issueDao.insertQue(username, question, time) == 1;
        } catch (IOException e) {
            throw new RuntimeException("写入文件失败", e);
        } finally {
            logger.debug(" dealQue ");
        }
    }

    /**
     * @param question 问题的MD5值
>>>>>>> open
     * @param username 回答者
     * @param content  回答内容
     * @return 是否提交成功
     */
    public boolean dealAns(String question, String username, String content) throws IOException {
        String time = SmallUtils.getTime();
        // 清空 question 对应的值,question 为问题的md5值,即清除缓存。
        CacheUtils.remove(question);
        File fileAns = new File(ansPath + question);
        if (!fileAns.exists()) {
            fileAns.createNewFile();
            logger.debug(" execute createFile ");
        }
        logger.debug(" after fileAns is :" + fileAns.getPath());
        Answer answer = new Answer(username, time, content);
        String ansSave = username + "+++" + time + "+++" + content;
        temple.add(ansSave);
        try {
            FileUtils.writeLines(fileAns, temple, true);
            temple.clear();
            return issueDao.updateAnswer(question) == 1;
        } catch (IOException e) {
            throw new RuntimeException("回答失败", e);
        }
    }

<<<<<<< HEAD
    public boolean dealAns(String question, String username, String content) {

=======
    public List<Issue> getAllIssue() {
//        List<Issue> list = issueDao.getAllIssue();
//        list = readQue(list);
//        return list;
        return readQue(issueDao.getAllIssue());
    }
>>>>>>> open

    public Issue findIssue(String question) {

        Issue issue = issueDao.findIssue(question);
        File fileQue = new File(quePath);
        try {
            queList = FileUtils.readLines(fileQue, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException("读取文档失败", e);
        }
        for (int i = 0; i < queList.size(); i++) {
            if (queList.get(i).contains(question)) {
                String[] str = queList.get(i).split("\\+++");
                issue.setContent(str[2]);
            }
        }
        return issue;
    }
    @SuppressWarnings("unchecked")
    public List<Answer> findAllAnswer(String question) throws IOException {
        // readAns 中上是否有question的缓存，没有创建，有的话什么也不做。
        readAns(question);
        List<String> list = (List<String>) CacheUtils.get(question);
        List<Answer> answerList = new ArrayList<>();
        if(list == null){
            return null;
        }
        for (String str : list) {
            String[] array = str.split("\\+++");
            answerList.add(new Answer(array[0], array[1], array[2]));
        }
        return answerList;
    }
    @SuppressWarnings("unchecked")
    private List<Issue> readQue(List<Issue> list) {

        Object object = CacheUtils.get("queList");
        logger.debug("object is :{}" , object == null);
        if (object == null) {
            File fileQue = new File(quePath);
            try {
                queList.clear();
                queList = FileUtils.readLines(fileQue, "utf-8");
                CacheUtils.set("queList", queList);
                logger.debug(" read queList from Disk");
            } catch (IOException e) {
                throw new RuntimeException("读取文档失败", e);
            }
        } else {
            queList = (List<String>) object;
            logger.debug(" read queList from cache");
        }
        for (int i = 0, n = list.size(); i < n; i++) {
            String[] str = queList.get(n - 1 - i).split("\\+++");
            list.get(i).setContent(str[2]);
            System.out.println(list.get(i).toString());
            System.out.println(queList.get(n - 1 - i));
        }
        return list;
    }

    private void readAns(String question) throws IOException {

        Object object = CacheUtils.get(question);
        System.out.println("object is :" + object);
        if (object == null) {
            File fileAns = new File(ansPath + question);
            if (!fileAns.exists()) {
                fileAns.createNewFile();
                logger.debug(" server is busy!");
                return;
            }
            try {
                ansList = FileUtils.readLines(fileAns, "utf-8");
                CacheUtils.set(question, ansList);
                logger.debug(" read queList from Disk");
            } catch (IOException e) {
                throw new RuntimeException("读取文档失败", e);
            }
        }
    }


}
