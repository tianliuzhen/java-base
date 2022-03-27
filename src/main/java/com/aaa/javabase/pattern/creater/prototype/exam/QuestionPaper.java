package com.aaa.javabase.pattern.creater.prototype.exam;

import com.aaa.javabase.pattern.creater.prototype.exam.questions.AnswerQuestions;
import com.aaa.javabase.pattern.creater.prototype.exam.questions.ChoiceQuestions;
import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建试卷（这种方法没有使用原型模式）
 *
 * @author liuzhen.tian
 * @version 1.0 QuestionMain.java  2022/3/27 20:06
 */
public class QuestionPaper {

    /**
     * 创建试卷
     *
     * @param userName 姓名
     * @param number   学号
     * @return List<ChoiceQuestions>
     */
    public List<ChoiceQuestions> createPaper(String userName, String number) {
        // 1、选择考试题集合
        List<ChoiceQuestions> choiceQuestions = Lists.newArrayList();

        choiceQuestions.add(new ChoiceQuestions(
                "java所定义的版本中不包括：",
                new HashMap<String, String>() {{
                    put("A", "JAVA2 EE");
                    put("B", "JAVA2 card");
                    put("C", "JAVA2 HE");
                    put("D", "JAVA2 SE");
                }}, "B"));

        choiceQuestions.add(new ChoiceQuestions(
                "下列说法正确的是：",
                new HashMap<String, String>() {{
                    put("A", "JAVA程序的main方法必须写在类里面");
                    put("B", "JAVA程序中可以多个main方法");
                    put("C", "JAVA程序中类名必须与文件名一样");
                    put("D", "JAVA程序的main方法如果只有一条语句，可以不用{}(大括号)括起来");
                }}, "B"));

        // 2、选择问题题集合
        List<AnswerQuestions> answerQuestions = Lists.newArrayList();
        answerQuestions.add(new AnswerQuestions("开心他妈给恶心开门？", "开心到家了"));
        answerQuestions.add(new AnswerQuestions("纱布搽屁股？", "露一手"));


        // 3、打印考试题
        StringBuilder paper = new StringBuilder();
        paper.append("考生：" + userName + "\r\n");
        paper.append("考号：" + number + "\r\n");
        paper.append("----------------------------------------------------------" + "\r\n");
        paper.append("一、选择题" + "\r\n");

        for (int i = 0; i < choiceQuestions.size(); i++) {
            paper.append("第").append(i + 1).append("题：").append(choiceQuestions.get(i).getName()).append("\r\n");
            Map<String, String> option = choiceQuestions.get(i).getOption();
            option.keySet().forEach(k -> {
                paper.append(k).append("：").append(option.get(k)).append("\r\n");
            });
            paper.append("答案：").append(choiceQuestions.get(i).getKey()).append("\r\n\n");
        }

        paper.append("二、选择题" + "\r\n");
        for (int i = 0; i < answerQuestions.size(); i++) {
            paper.append("第").append(i + 1).append("题：").append(answerQuestions.get(i).getProblem());
            paper.append("答案：").append(answerQuestions.get(i).getAnswer()).append("\r\n\n");
        }
        System.out.println(paper.toString());

        return choiceQuestions;
    }
}
