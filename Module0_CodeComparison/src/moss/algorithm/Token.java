package moss.algorithm;

import java.util.Objects;

public class Token {
    private final int id;
    private final int lineNo;

    @SuppressWarnings("WeakerAccess")
    public static class TokenBuilder {
        private int hash;
        private int lineNo;

        public TokenBuilder setHash(int hash) {
            this.hash = hash;
            return this;
        }

        public TokenBuilder setLineNo(int lineNo) {
            this.lineNo = lineNo;
            return this;
        }

        public Token createToken() {
            return new Token(hash, lineNo);
        }
    }


    private Token(int hash, int lineNo) {
        this.id = hash;
        this.lineNo = lineNo;
    }

    public final int getLineNo() {
        return lineNo;
    }

    public final int getId() {
        return id;
    }

    @Override
    public final boolean equals(Object other){
        if (!(other instanceof Token)) return false;
        Token ref = (Token)other;
        return ref.id == this.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }




}
