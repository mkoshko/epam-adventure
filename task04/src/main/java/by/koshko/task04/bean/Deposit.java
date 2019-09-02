package by.koshko.task04.bean;

import java.time.LocalDate;

/**
 * Class describes deposit entity.
 */
public class Deposit {

    /**
     * Type of the deposit.
     */
    private String type;

    /**
     * The international bank account number.
     */
    private String iban;

    /**
     * Depositor.
     */
    private Depositor depositor;
    /**
     * Deposit opens date.
     */
    private LocalDate depositDate;

    /**
     * Deposit amount.
     */
    private double amount;

    /**
     * Currency of deposit.
     */
    private Currency currency;

    /**
     * Profitability of the deposit.
     */
    private double profitability;
    /**
     * Term of the deposit.
     */
    private String term;
    /**
     * Indicates whether the deposit money from can be withdrawal.
     */
    private boolean withdrawal;

    /**
     * Indicates whether the balance can be refilled.
     */
    private boolean refill;

    /**
     * Indicates whether the deposit have capitalization.
     */
    private boolean capitalization;

    /**
     * Unique identifier of the deposit.
     */
    private String id;

    public String getType() {
        return type;
    }

    public void setType(final String newType) {
        type = newType;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(final String newIban) {
        iban = newIban;
    }

    public Depositor getDepositor() {
        return depositor;
    }

    public void setDepositor(final Depositor newDepositor) {
        depositor = newDepositor;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(final LocalDate newDepositDate) {
        depositDate = newDepositDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double newAmount) {
        amount = newAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency newCurrency) {
        currency = newCurrency;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(final double newProfitability) {
        profitability = newProfitability;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(final String newTerm) {
        term = newTerm;
    }

    public boolean isWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(final boolean newWithdrawal) {
        withdrawal = newWithdrawal;
    }

    public boolean isRefill() {
        return refill;
    }

    public void setRefill(final boolean newRefill) {
        refill = newRefill;
    }

    public boolean isCapitalization() {
        return capitalization;
    }

    public void setCapitalization(final boolean newCapitalization) {
        capitalization = newCapitalization;
    }

    public String getId() {
        return id;
    }

    public void setId(final String newId) {
        id = newId;
    }


    @Override
    public String toString() {
        return "Deposit{"
                + "type='" + type + '\''
                + ", iban='" + iban + '\''
                + ", depositor=" + depositor
                + ", depositDate=" + depositDate
                + ", amount=" + amount
                + ", currency=" + currency
                + ", profitability=" + profitability
                + ", term='" + term + '\''
                + ", withdrawal=" + withdrawal
                + ", refill=" + refill
                + ", capitalization=" + capitalization
                + ", id='" + id + '\''
                + '}';
    }
}
