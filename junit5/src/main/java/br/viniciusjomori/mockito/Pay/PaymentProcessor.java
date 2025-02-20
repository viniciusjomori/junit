package br.viniciusjomori.mockito.Pay;

import java.math.BigDecimal;

public class PaymentProcessor {

    private boolean allowForeignCurrencies;
    private String fallbackOption;
    private BigDecimal taxRate;

    public PaymentProcessor() {
        this(false, "DEBIT", new BigDecimal("19.00"));
    }

    public PaymentProcessor(String fallbackOption, BigDecimal taxRate) {
        this(false, fallbackOption, taxRate);
    }

    public PaymentProcessor(boolean allowForeignCurrencies, String fallbackOption, BigDecimal taxRate) {
        this.allowForeignCurrencies = allowForeignCurrencies;
        this.fallbackOption = fallbackOption;
        this.taxRate = taxRate;
    }

    public BigDecimal chargeCustomer(String customerId, BigDecimal netPrice) {
        // Any arbitrary implementation
        System.out.println("About to charge customer: " + customerId);
        return BigDecimal.ZERO;
    }

    public boolean isAllowForeignCurrencies() {
        return this.allowForeignCurrencies;
    }

    public boolean getAllowForeignCurrencies() {
        return this.allowForeignCurrencies;
    }

    public void setAllowForeignCurrencies(boolean allowForeignCurrencies) {
        this.allowForeignCurrencies = allowForeignCurrencies;
    }

    public String getFallbackOption() {
        return this.fallbackOption;
    }

    public void setFallbackOption(String fallbackOption) {
        this.fallbackOption = fallbackOption;
    }

    public BigDecimal getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}
