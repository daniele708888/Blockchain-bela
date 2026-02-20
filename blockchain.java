import java.util.ArrayList;

public class Blockchain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 4;

    public static void main(String[] args) {

        // Blocco Genesi
        System.out.println("Creazione Blocco Genesi...");
        Block genesisBlock = new Block("Blocco Genesi", "0");
        genesisBlock.mineBlock(difficulty);
        blockchain.add(genesisBlock);

        // Secondo Blocco
        System.out.println("\nCreazione Secondo Blocco...");
        Block block1 = new Block("Secondo blocco", genesisBlock.hash);
        block1.mineBlock(difficulty);
        blockchain.add(block1);

        // Terzo Blocco
        System.out.println("\nCreazione Terzo Blocco...");
        Block block2 = new Block("Terzo blocco", block1.hash);
        block2.mineBlock(difficulty);
        blockchain.add(block2);

        // Verifica Blockchain
        System.out.println("\nLa blockchain è valida? " + isChainValid());
    }

    // Verifica integrità
    public static boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            // Controllo hash corrente
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Hash non valido al blocco " + i);
                return false;
            }

            // Controllo collegamento
            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("Collegamento non valido al blocco " + i);
                return false;
            }
        }

        return true;
    }
}