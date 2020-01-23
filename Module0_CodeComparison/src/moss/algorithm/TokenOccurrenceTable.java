package moss.algorithm;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
class TokenOccurrenceTable {
    private HashMap<Token, Integer> occurrences;
    private Collection<Token> blackList;

    TokenOccurrenceTable(){
        occurrences = new HashMap<>();
        blackList = new ArrayList<>();
    }

    void ignoreToken(Token tok){
        blackList.add(tok);
    }

    boolean isIgnored(Token tok){
        return blackList.contains(tok);
    }

    //adds the token to the occurrences table. Puts it in if the token has yet to occur
    private void addOccurredIfNotIgnored(Token tok){
        if (this.isIgnored(tok)) return;
        occurrences.putIfAbsent(tok, 0);
        occurrences.put(tok,
                occurrences.get(tok) + 1);
    }

    void tabulate(Reader reader) throws IOException {
        HashingTokenizer tokenizer = new HashingTokenizer(reader);
        Token currentToken;
        do {
            currentToken = tokenizer.getNextTokenInfo();
            this.addOccurredIfNotIgnored(currentToken);
        } while (!tokenizer.isAtEnd());
    }


    int collisionCount(){
        int count = 0;
        for (Integer singleCount : this.occurrences.values()){
            if (singleCount >= 2) count++;
        }
        return count;
    }

    //quick delegation
    int total(){
        return this.occurrences.size();
    }



}
