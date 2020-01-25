package moss.algorithm;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Used to hold a sequential group of tokens
 */
class TokenCluster {
    private final Collection<Token> tokens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenCluster that = (TokenCluster) o;
        return tokens.equals(that.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokens);
    }

    /**
     * The TokenCluster class is meant to be immutable upon construction,
     * so adding of tokens will be done through this builder. You can add Token(s) sequentially in this Builder.
     */
    static class TokenClusterBuilder{
        private Collection<Token> tokens;
        TokenClusterBuilder(){ this.tokens = new ArrayList<>(); }
        void addNext(Token tok){ tokens.add(tok); }
        TokenCluster cluster(){ return new TokenCluster(tokens); }
    }

    /**
     * @param tokens Container for tokens to be placed in the cluster. Holds the tokens in the proper sequence
     */
    private TokenCluster(Collection<Token> tokens){
        this.tokens = tokens;
    }
}
