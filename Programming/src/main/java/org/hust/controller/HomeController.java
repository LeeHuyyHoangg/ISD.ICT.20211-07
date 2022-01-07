package org.hust.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hust.entity.db.Database;
import org.hust.entity.invoice.Invoice;
import org.hust.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class HomeController extends BaseController {

    public ArrayList<Invoice> getInvoices() {
        ArrayList<Invoice> invoices = new ArrayList<>();
        MongoDatabase db = Database.getConnection();
        MongoCollection<Document> invoiceCollection = db.getCollection("invoices");
        FindIterable<Document> iterDoc = invoiceCollection.find();
        for (Document doc: iterDoc) {
            Invoice invoice = Utils.documentToObject(doc, Invoice.class);
            invoice.setBikeIds(doc.get("bikes", new ArrayList<String>().getClass()));
            invoices.add(invoice);
        }
        return invoices;
    }

    public static void main(String args[]) {
        ArrayList<Invoice> invoiceLst = new HomeController().getInvoices();
        for (Invoice invoice: invoiceLst) {
            System.out.println(invoice);
        }
    }
}
