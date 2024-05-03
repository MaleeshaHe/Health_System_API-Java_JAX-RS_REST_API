package net.codejava.ws.dao;

import net.codejava.ws.models.Billing;

import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    private static BillingDAO instance;
    private static List<Billing> data = new ArrayList<>();

    static {
        data.add(new Billing(1,"INV-001","05/02/2024",5000.00,"Paid",0.00));
        data.add(new Billing(2,"INV-002","05/02/2024",5000.00,"Paid",0.00));
    }

    private BillingDAO() {

    }

    public static BillingDAO getInstance() {
        if (instance == null) {
            instance = new BillingDAO();
        }
        return instance;
    }

    public List<Billing> listAll() {
        return new ArrayList<Billing>(data);
    }

    public int add(Billing billing) {
        int newId = data.size() + 1;
        billing.setId(newId);
        data.add(billing);

        return newId;
    }

    public Billing get(int id) {
        Billing billingToFind = new Billing(id);
        int index = data.indexOf(billingToFind);
        if (index >= 0) {
            return data.get(index);
        }
        return null;
    }

    public boolean delete(int id) {
        Billing billingToFind = new Billing(id);
        int index = data.indexOf(billingToFind);
        if(index >= 0) {
            data.remove(index);
            return true;
        }
        return false;
    }

    public boolean update(Billing billing) {
        int index = data.indexOf(billing);
        if (index >= 0) {
            data.set(index, billing);
            return true;
        }
        return false;
    }
}
