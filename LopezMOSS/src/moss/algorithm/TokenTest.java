package moss.algorithm;

class TokenTest {
    public static void main(String[] args){
        Token token1 = new Token.TokenBuilder().setHash(123).setLineNo(1).createToken();
        Token token2 = new Token.TokenBuilder().setHash(1234).setLineNo(1).createToken();
        Token token3 = new Token.TokenBuilder().setHash(1234).setLineNo(1).createToken();
        System.out.println(token1.hashCode());
        System.out.println(token2.hashCode());
        System.out.println(token3.hashCode());
    }
}
