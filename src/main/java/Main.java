import org.apache.commons.codec.digest.DigestUtils;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        System.out.println(blockchain);
        System.out.println();

        blockchain.createGenesisBlock("Salut :)");
        System.out.println(blockchain);
        System.out.println();

        blockchain.createNextBlock("Next1");
        System.out.println(blockchain);
        System.out.println();

        blockchain.createNextBlock("Next2");
        System.out.println(blockchain);
        System.out.println();

        blockchain.createNextBlock("Next3");
        System.out.println(blockchain);
        System.out.println();
    }
}
