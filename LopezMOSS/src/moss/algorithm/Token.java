package moss.algorithm;

import org.junit.Assert;
import org.omg.CORBA.Any;

import java.util.Objects;
import java.util.Observable;

public class Token {
    public enum TYPE{
        EOL, NUMBER, WORD, STRING_LITERAL, IGNORE, OTHER, NONE
    }


    private final int id;
    private final int lineNo;
    @SuppressWarnings("FieldCanBeLocal")
    private final Object value;

    @SuppressWarnings("WeakerAccess")
    public static class TokenBuilder {
        private int id;
        private int lineNo;
        private Object value;
        private TYPE type;

        @SuppressWarnings({"SameParameterValue", "UnusedReturnValue"})
        final TokenBuilder setType(TYPE type){
            return setTypeValue(type, null);
        }

        final <T> TokenBuilder setTypeValue(TYPE type, T value){
            this.type = type;
            this.value = value;
            //NOTE: this null check is necessary because if there is no SPECIFIC value for a particular token,
            //it is unnecessary to separate its identity from other already existing tokens of precisely the same type
            if (this.value == null)
                this.id = this.type.hashCode();
            else
                this.id = this.value.hashCode();
            return this;
        }


        TokenBuilder setLineNo(int lineNo) {
            this.lineNo = lineNo;
            return this;
        }


        public Token createToken() {
            return new Token(id, lineNo, value);
        }
    }


    private Token(int hash, int lineNo, Object value) {
        this.id = hash;
        this.lineNo = lineNo;
        this.value = value;
    }

    public final int getLineNo() {
        return lineNo;
    }

    public final int getId() {
        return id;
    }

    @Override
    public final boolean equals(Object other){
        //TODO: Write motivation for using only id as the identifier of the class
        if (!(other instanceof Token)) return false;
        Token ref = (Token)other;
        return ref.id == this.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }





}
