package moss.algorithm;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Collection;

class HashingTokenizer {
    private StreamTokenizer tokenizer;
    static final int EOF = 0;
    static final int EOL = 1;
    static final int NUMBER = 5;
    static final int IGNORE = 3;
    static final int OTHER = 2;
    static final int QUOTE = '"';

    public void addCharacterToken(int ch){
        tokenizer.ordinaryChar(ch);
    }


    //isAtEnd simply checks if the next if the next token is the end-of-file character
    //a significant design decision here is the fact that an IOException will crash the system unwarranted after printing the stack trace
    //this function will not tamper with the order of the tokens
    @SuppressWarnings("WeakerAccess")
    public boolean isAtEnd(){
        tokenizer.pushBack();
        try {
            return tokenizer.nextToken() == StreamTokenizer.TT_EOF;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return true;
    }


    Token getNextTokenInfo() throws IOException {
        int tokenNumber = tokenizer.nextToken();
        int id;
        switch(tokenNumber){
            case StreamTokenizer.TT_EOF:
                id = EOF;
                break;
            case StreamTokenizer.TT_EOL:
                id = EOL;
                break;
            case StreamTokenizer.TT_NUMBER:
                //CHANGE: recently changed id for number to the NUMBER constant instead of the integer's hash value
                id = NUMBER;
                break;

            case StreamTokenizer.TT_WORD:
                id = tokenizer.sval.hashCode();
                break;

            default:
                //extra checks
                //a quote check here
                if (tokenNumber == QUOTE) id = tokenizer.sval.hashCode();
                else id = Integer.hashCode(tokenNumber);
                break;
        }
        return new Token.TokenBuilder().setHash(id).setLineNo(tokenizer.lineno()).createToken();
    }



    HashingTokenizer(Reader reader){
        tokenizer = new StreamTokenizer(reader);
        tokenizer.quoteChar(QUOTE);


    }
}
