package com.example.waoquiz.game;

import java.util.List;

public class Game {

    public static class Answer {
        public String text;
        public Boolean rigth;
    }

    public static class Question {
        public String question;
        public List<Answer> answers;
    }

    public String theme;
    public List<Question> questions;

    public Game(String theme_, List<Question> questions_) {
        theme = theme_;
        questions = questions_;
    }
}
