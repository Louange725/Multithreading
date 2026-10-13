import java.security.spec.EllipticCurve;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bankAccounts.add(new BankAccount(10000));
        }
//        System.out.println("Bank Accounts:" + bankAccounts);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int source = random.nextInt(10);
            int destination = random.nextInt(10);
            while(source == destination) {
                destination = random.nextInt(10);
            }
            int amount = random.nextInt(100000) + 1;
            executor.execute(new TransferTask(
                    bankAccounts.get(source),
                    bankAccounts.get(destination),
                    500
            ));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
            int total = 0;
            for (BankAccount bankAccount : bankAccounts) {
                total += bankAccount.getBalance();
            }
            System.out.println("Total balance: " + total);

        }
    }

