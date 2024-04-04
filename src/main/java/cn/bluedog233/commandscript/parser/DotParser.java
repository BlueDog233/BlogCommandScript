package cn.bluedog233.commandscript.parser;

import java.util.Deque;

public class DotParser extends Parser {
    private Parser parentParser;
    public DotParser(Deque<Character> role, Parser parser) {
        super(',', role);
        this.parentParser = parser;
    }
    public String[] parse() {
        StringBuilder sb = new StringBuilder();
        if ( parentParser instanceof MediKParser) {
            String key = new EqulsParser(getDeque()).parse();

            while (getDeque().size() > 0) {
                char c = getDeque().pop();
                if(c=='"'){
                    String str=new QuotationParser(getDeque()).parse();
                    if(!str.equals(" ")){
                        return new String[]{key,str};
                    }
                }
                if (c == ',') {
                    break;
                }else if(c==']'){
                    getDeque().push(']');
                    break;
                }
                sb.append(c);
            }
            return new String[]{key, sb.toString()};
        } else {
            while (getDeque().size() > 0) {
                char c = getDeque().pop();
                if(c=='"') {
                    String str=new QuotationParser(getDeque()).parse();
                    if(!str.equals(" ")){
                        return new String[]{str};
                    }
                }
                else if (c == ',') {
                    break;
                }else if(c==')'){
                    getDeque().push(')');
                    break;
                }

                sb.append(c);
            }
            return new String[]{sb.toString()};
        }
    }
}
