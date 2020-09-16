public class BlockBuilder {
    // Block header
    private int version = 1;
    private String previousHash = null;
    private int profWorkDifficulty = 2;
    private int nonce = 0;
    // End block header

    private int dataCounter = 0;
    private String data = "";


    //------------------------
    //SETTER
    public BlockBuilder setVersion(int version) {
        this.version = version;
        return this;
    }

    public BlockBuilder setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
        return this;
    }

    public BlockBuilder setProfWorkDifficulty(int profWorkDifficulty) {
        this.profWorkDifficulty = profWorkDifficulty;
        return this;
    }

    public BlockBuilder setNonce(int nonce) {
        this.nonce = nonce;
        return this;
    }

    public BlockBuilder setDataCounter(int dataCounter) {
        this.dataCounter = dataCounter;
        return this;
    }

    public BlockBuilder setData(String data) {
        this.data = data;
        return this;
    }


    //------------------------
    //BUILD
    public Block build() {
        return new Block(version, previousHash, profWorkDifficulty, nonce, dataCounter, data);
    }
}
