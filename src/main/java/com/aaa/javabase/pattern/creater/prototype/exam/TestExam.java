package com.aaa.javabase.pattern.creater.prototype.exam;

/**
 * @author liuzhen.tian
 * @version 1.0 TestExam.java  2022/3/27 20:28
 */
public class TestExam {
    public static void main(String[] args) {
        QuestionPaper questionPaper = new QuestionPaper();
        questionPaper.createPaper("唐三", "202203270");
        questionPaper.createPaper("李白", "202203271");

    }
}
