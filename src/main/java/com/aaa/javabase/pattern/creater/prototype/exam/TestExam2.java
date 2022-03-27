package com.aaa.javabase.pattern.creater.prototype.exam;

/**
 * @author liuzhen.tian
 * @version 1.0 TestExam.java  2022/3/27 20:28
 */
public class TestExam2 {
    public static void main(String[] args) throws CloneNotSupportedException {

        QuestionPaper2 questionPaper2 = new QuestionPaper2();
        questionPaper2.initData();

        questionPaper2.createPaper("唐三", "202203270");
        questionPaper2.createPaper("李白", "202203271");
    }
}
