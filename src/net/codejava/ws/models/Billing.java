package net.codejava.ws.models;

public class Billing {
    private int id;
    private String invoiceNumber;
    private String transactionDate;
    private double billedAmount;
    private String paymentStatus;
    private double outstandingBalance;

    public Billing(int id) {
        this.id = id;
    }

    public Billing() {
    }

    public Billing(int id, String invoiceNumber, String transactionDate, double billedAmount, String paymentStatus, double outstandingBalance) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.transactionDate = transactionDate;
        this.billedAmount = billedAmount;
        this.paymentStatus = paymentStatus;
        this.outstandingBalance = outstandingBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(double billedAmount) {
        this.billedAmount = billedAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
}