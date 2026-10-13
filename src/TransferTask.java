public class TransferTask implements Runnable {
    private BankAccount source;
    private BankAccount destination;
    private double  amount;

    public TransferTask(BankAccount source, BankAccount destination, double amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public void run() {
        source.withdraw(amount);
        destination.deposit(amount);
    }
}
