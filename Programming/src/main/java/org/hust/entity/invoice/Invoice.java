package org.hust.entity.invoice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hust.entity.payment.PaymentTransaction;

import java.util.List;

@Getter
@NoArgsConstructor
public class Invoice {
    private PaymentTransaction transaction;
    private int deposit;
    private int refund;
    private int fee;
    private int totalCharge;
    private List<String> bikeIds;

    public Invoice(PaymentTransaction transaction, int deposit, int refund, int fee, int totalCharge, List<String> bikeIds) {
        this.transaction = transaction;
        this.deposit = deposit;
        this.refund = refund;
        this.fee = fee;
        this.totalCharge = totalCharge;
        this.bikeIds = bikeIds;
    }

    // TODO: Save invoice to database
    public void save() {

    }

    public String toDetailedString() {
        return String.format("Deposit: +%s\nUsage fees: -%s\n", deposit, fee) + (refund == 0 ? "Total charge: -" + totalCharge : "Refund: +" + refund);
    }

    @Override
    public String toString() {
        return String.format("Deposit: +%s\nUsage fees: -%s\n", deposit, fee) + (refund == 0 ? "Total charge: -" + totalCharge : "Refund: +" + refund) + "\nBike: " + bikeIds.get(0);
    }
}
