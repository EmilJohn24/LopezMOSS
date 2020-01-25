package moss.algorithm;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
class TokenClusterOccurrenceTable {
    private HashMap<TokenCluster, Integer> occurrences;
    static int DEFAULT_CLUSTER_SIZE = 2;

    TokenClusterOccurrenceTable(){
        occurrences = new HashMap<>();
    }



    //adds the token to the occurrences table. Puts it in if the token has yet to occur
    private void addOccurred(TokenCluster tokenCluster){
        occurrences.putIfAbsent(tokenCluster, 0);
        occurrences.put(tokenCluster,
                occurrences.get(tokenCluster) + 1);
    }

    void tabulate(Reader reader) throws IOException {
        HashingTokenizer tokenizer = new HashingTokenizer(reader);
        for (TokenCluster cluster : tokenizer.remainingTokenClusters(DEFAULT_CLUSTER_SIZE)){
            this.addOccurred(cluster);
        }
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
