package com.it.servive;

import com.it.dao.IssueDao;
import com.it.entity.Issue;
import com.it.utils.SmallUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyue on 2016/6/25.
 * IssueService
 */
public class IssueService {

    // 存储问题
    private IssueDao issueDao = new IssueDao();
    private Logger logger = LoggerFactory.getLogger(IssueService.class);
    // 存储问题
    private String quePath = "G:/issue/que";
    // 存储答案
    private String ansPath = "G:/issue/ans/";
    private List<String> ansList = new ArrayList<>();
    private List<String> queList = new ArrayList<>();

    /**
     * @param username 提问者
     * @param content  提问内容
     * @return 是否提交成功
     */
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
        queList.add(saveQue);
        try {
            FileUtils.writeLines(fileQue, queList, true);
            return issueDao.insertQue(username, question, time) == 1;
        } catch (IOException e) {
            throw new RuntimeException("写入文件失败", e);
        } finally {
            logger.debug(" dealQue ");
        }
    }

    /**
     * @param question 问题的MD5值
     * @param username 回答者
     * @param content  回答内容
     * @return 是否提交成功
     */
    public boolean dealAns(String question, String username, String content) {
        String time = SmallUtils.getTime();
        File fileAns = new File(ansPath + question);
        String ansSave = username + "+++" + time + "+++" + content;
        ansList.add(ansSave);
        try {
            FileUtils.writeLines(fileAns, ansList, true);
            return issueDao.updateAnswer(question) == 1;
        } catch (IOException e) {
            throw new RuntimeException("回答失败", e);
        }

    }

    public List<Issue> getAllIssue() {
        List<Issue> list = issueDao.getAllIssue();
        File fileQue = new File(quePath);
        try {
            queList = FileUtils.readLines(fileQue, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException("读取文档失败", e);
        }
        for (int i = 0; i < list.size(); i++) {
            String[] str = queList.get(i).split("\\+++");
            list.get(i).setContent(str[2]);
            list.get(i).setTime(str[3].replace("/", " "));
        }
        return list;
    }

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
                issue.setTime(str[3].replace("/", " "));
            }
        }
        return issue;
    }
}
