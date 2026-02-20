import java.security.MessageDigest;

public class Block {

    public String hash;
    public String previousHash;
    public String data;
    public long timestamp;
    public int nonce;

    // Costruttore
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    // Calcolo hash SHA-256
    public String calculateHash() {
        try {
            String input = previousHash + Long.toString(timestamp) 
                         + Integer.toString(nonce) + data;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Mining (Proof of Work)
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');

        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }

        System.out.println("Blocco minato! Hash: " + hash);
    }
}