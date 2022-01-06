package org.hust.controller;

import org.hust.entity.invoice.Invoice;

import java.util.ArrayList;

public class HomeController extends BaseController {
    // TODO Get invoices from database
    public ArrayList<Invoice> getInvoices() {
        ArrayList<Invoice> invoices = new ArrayList<>();
        return invoices;
    }
}
