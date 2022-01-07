package org.hust.entity.invoice;

import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hust.entity.db.Database;
import org.hust.entity.payment.CreditCard;
import org.hust.entity.payment.PaymentTransaction;
import org.hust.utils.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

    public void save() {
        MongoDatabase db = Database.getConnection();
        Document invoiceDoc = new Document("_id", new ObjectId());
        invoiceDoc.append("deposit", this.deposit);
        invoiceDoc.append("refund", this.refund);
        invoiceDoc.append("fee", this.fee);
        invoiceDoc.append("totalCharge", this.totalCharge);
        invoiceDoc.append("bikes", this.bikeIds);
        db.getCollection("invoices").insertOne(invoiceDoc);
    }

    public String toDetailedString() {
        return String.format("Deposit: +%s\nUsage fees: -%s\n", deposit, fee) + (refund == 0 ? "Total charge: -" + totalCharge : "Refund: +" + refund);
    }

    @Override
    public String toString() {
        return String.format("Deposit: +%s\nUsage fees: -%s\n", deposit, fee) + (refund == 0 ? "Total charge: -" + totalCharge : "Refund: +" + refund) + "\nBike: " + bikeIds.get(0);
    }

    public void setBikeIds(List<String> bikes) {
        this.bikeIds = bikes;
    }
}
