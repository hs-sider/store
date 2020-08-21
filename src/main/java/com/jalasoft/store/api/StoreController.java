package com.jalasoft.store.api;

import com.jalasoft.store.model.Transaction;
import com.jalasoft.store.service.StoreService;
import com.jalasoft.store.service.exception.InvalidProductCodeException;
import com.jalasoft.store.service.exception.ReportParametersException;
import com.jalasoft.store.service.report.IReport;
import com.jalasoft.store.service.report.ReportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/purchase")
    public Transaction purchase(@RequestParam String productCode, @RequestParam int quantity) throws InvalidProductCodeException {
        return storeService.registerTransactionPurchase(productCode, quantity);
    }

    @PostMapping("/sale")
    public Transaction sale(@RequestParam String productCode, @RequestParam int quantity) throws InvalidProductCodeException {
        return storeService.registerTransactionSale(productCode, quantity);
    }

    @GetMapping("/report")
    public Object report(@RequestParam(name = "type") String type, @RequestParam(name = "subType") String subType) throws ReportParametersException {
        ReportFactory factory = new ReportFactory();
        IReport<?> report = factory.getReport(type, subType);
        return report.generateReport(storeService.getStore());
    }
}
