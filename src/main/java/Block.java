import org.apache.commons.codec.digest.DigestUtils;
import java.util.Date;

public class Block {
    private int blockSize;

    // Block header
    private int version;
    private String previousHash;
    private String actualHash;
    private Date timestamp;
    private int profWorkDifficulty;
    private int nonce;
    // End block header

    private int dataCounter;
    private String data;


    //------------------------
    //CONSTRUCTOR
    public Block(int version, String previousHash, int profWorkDifficulty, int nonce, int transactionCounter, String transaction) {
        this.blockSize = computeBlockSize();
        this.version = version;
        this.previousHash = previousHash;
        this.timestamp = computeTimestamp();
        this.profWorkDifficulty = profWorkDifficulty;
        this.nonce = nonce;
        this.dataCounter = transactionCounter;
        this.data = transaction;
        this.actualHash = computeActualHash();
        this.profOfWork();
    }

    public String computeActualHash() {
        String hash = DigestUtils.sha256Hex(data + dataCounter + blockSize + version + previousHash + timestamp + profWorkDifficulty + nonce);
        hash = DigestUtils.sha256Hex(hash);
        return  hash;
    }

    private int computeBlockSize() {
        return 128;
    }

    private Date computeTimestamp() {
        return  new Date();
    }


    //------------------------
    //GETTER
    public String getActualHash() {
        return actualHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getNonce() {
        return nonce;
    }

    public int getDataCounter() {
        return dataCounter;
    }

    public String getData() {
        return data;
    }


    //------------------------
    //METHODS
    public boolean profOfWork() {
        this.nonce = 0;
        StringBuilder nbZero = new StringBuilder();
        for (int i = 0; i < profWorkDifficulty; i++) {nbZero.append('0');}
        String zero = nbZero.toString();

        while (true) {
            this.actualHash = this.computeActualHash();
            String subHash = this.actualHash.substring(0, profWorkDifficulty);

            if (subHash.equals(zero)) {
                return true;
            }
            this.nonce++;
        }
    }

    @Override
    public String toString() {
        return "Block{" +
                "previousHash='" + previousHash + '\'' +
                ", actualHash='" + actualHash + '\'' +
                ", nonce=" + nonce +
                ", dataCounter=" + dataCounter +
                ", data='" + data + '\'' +
                '}';
    }
}

