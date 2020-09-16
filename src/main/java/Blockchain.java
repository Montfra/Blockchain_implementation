import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> blockchain;


    //------------------------
    //CONSTRUCTOR
    public Blockchain() {
        blockchain = new ArrayList<>();
    }


    //------------------------
    //GETTER
    public Block get(int index) {
        return blockchain.get(index);
    }


    //------------------------
    //METHODS
    public void createGenesisBlock(String data) {
        blockchain.add(new BlockBuilder().setData(data).build());
    }

    public void createNextBlock(String data) {
        Block lastBlock = blockchain.get(blockchain.size() -1);
        blockchain.add(new BlockBuilder().setData(data).setPreviousHash(lastBlock.getActualHash()).setDataCounter(lastBlock.getDataCounter() + 1).build());
    }

    public boolean verifyBlock(int index) {
        if (index == 0) { return verifyGenesis(); }

        //get useful block and catch error
        Block actualBlock = null;
        Block previousBlock = null;
        try{
            actualBlock = blockchain.get(index);
            previousBlock = blockchain.get(index-1);
        }catch (Exception e){
            System.out.println("ERROR INDEX MUST BE VALID");
            System.exit(1);
        }

        //Verify needed
        //Block must have a datacount equals to datacount +1 of the previous block
        if (actualBlock.getDataCounter() != previousBlock.getDataCounter() +1) { return false;}

        //Hash of previous block have to correspond
        if (!actualBlock.getPreviousHash().equals(previousBlock.getActualHash())) { return false;}

        //Hash must be valid
        if (!actualBlock.computeActualHash().equals(actualBlock.getActualHash())) { return false;}

        return true;
    }

    private boolean verifyGenesis() {
        if (blockchain.size() <= 0) { return false; }
        Block actualBlock = blockchain.get(0);

        //Block must have a datacount equals to datacount +1 of the previous block
        if (actualBlock.getDataCounter() != 0) { return false;}

        //Hash of previous block have to correspond
        if (actualBlock.getPreviousHash() != null) { return false;}

        //Hash must be valid
        if (!actualBlock.computeActualHash().equals(actualBlock.getActualHash())) { return false;}

        return true;
    }

    public boolean verifyBlockchain() {
        for (int i = 0; i < blockchain.size(); i++) {
            if (!verifyBlock(i)){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "blockchain=" + blockchain;
    }
}
