package cn.bluedog233.goodservice.parser;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;


public abstract class Parser {
    private char endChar;

    public char getEndChar() {
        return endChar;
    }

    public void setEndChar(char endChar) {
        this.endChar = endChar;
    }

    public Deque<Character> getDeque() {
        return deque;
    }

    public void setDeque(Deque<Character> deque) {
        this.deque = deque;
    }

    private Deque<Character> deque;
    public Parser(char endChar, Deque<Character> deque) {
        this.endChar= this.endChar;
        this.deque=deque;
    }
}
