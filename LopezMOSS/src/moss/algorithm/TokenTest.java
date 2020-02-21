package moss.algorithm;

import org.eclipse.osgi.framework.internal.core.Tokenizer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

class TokenTest {
    private static String TEST_TOKEN_STRING_1 = "if";
    private static String TEST_TOKEN_STRING_2 = "else";
    private static String TEST_TOKEN_STRING_3 = "if";
    private static String TEST_TOKENS = "if else then";

    private static StringReader TEST_TOKEN_READER = new StringReader(TEST_TOKENS);

    private static Token token1 = new Token.TokenBuilder().setTypeWithValue(Token.TYPE.WORD, TEST_TOKEN_STRING_1).createToken();
    private static Token token2 = new Token.TokenBuilder().setTypeWithValue(Token.TYPE.WORD, TEST_TOKEN_STRING_2).createToken();
    private static Token token3 = new Token.TokenBuilder().setTypeWithValue(Token.TYPE.WORD, TEST_TOKEN_STRING_3).createToken();

    /**
     * Check if tokens that have the same string content are equal
     */
    @Test
    void tokenEqualityTest(){
        assert(token1.equals(token3));
        assert(!token1.equals(token2));
    }


    /**
     * Checks if the hash value of equal tokens are equal. See the equals and hashCode equivalence contract for more details
     */
    @Test
    void tokenConsistentHashTest(){
        assert(token1.hashCode() == token3.hashCode());
        assert(token1.hashCode() != token2.hashCode());
    }

    /**
     * Checks if the tokenization yields the same value as the value in the reader
     */
    @Test
    void tokenizationTest() throws IOException, HashingTokenizer.TokenizerEndException {
        HashingTokenizer tokenizer = new HashingTokenizer(TEST_TOKEN_READER);
        assert tokenizer.getNextTokenInfo().getValue().equals("else");
    }
}
