package moss.algorithm;

import java.io.IOException;
import java.io.Reader;

public class TokenHashingStrategy implements ComparisonStrategy {

    private TokenOccurrenceTable table;
    public TokenHashingStrategy(){
        table = new TokenOccurrenceTable();
    }



    //returns a score between 0.0 and 1.0
    @Override
    public Double compare(Reader str1, Reader str2) throws IOException {
        table.tabulate(str1);
        table.tabulate(str2);
        return (double) table.collisionCount() / (double) table.total();
    }


}
