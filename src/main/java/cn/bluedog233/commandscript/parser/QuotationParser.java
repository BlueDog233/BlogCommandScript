package cn.bluedog233.commandscript.parser;

import java.util.Deque;

public class QuotationParser extends Parser{
    public QuotationParser( Deque<Character> deque) {
        super('"', deque);
    }
    public String parse() {
        StringBuilder sb = new StringBuilder();
        while (getDeque().size() > 0) {
            char c = getDeque().pop();
            if (c == '"') {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
