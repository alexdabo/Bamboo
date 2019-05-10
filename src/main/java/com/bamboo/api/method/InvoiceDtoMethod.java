package com.bamboo.api.method;

import com.bamboo.api.dto.InvoiceDto;
import com.bamboo.model.entity.Invoice;
import com.bamboo.model.method.InvoiceImpl;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDtoMethod {
    public List<InvoiceDto> findByDate(String date) throws Exception{
        List<InvoiceDto> invoices = new ArrayList<>();
        for (Invoice invoice: new InvoiceImpl().findByDate(date)) {

            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setInvoiceId(invoice.getId());
            invoiceDto.setNumber(invoice.getNumber());
            invoiceDto.setDateOfIssue(invoice.getDateOfIssue());
            invoiceDto.setTotalToPay(invoice.getTotalToPay());
            invoiceDto.setPayed(invoice.isPayed());
            invoiceDto.setBeneficiary(new BeneficiaryDtoMethod().findById(invoice.getBeneficiary()));
            invoiceDto.setDebtcollector(new UserDtoMethod().findById(invoice.getDebtcollector()));
            invoices.add(invoiceDto);

        }

        return invoices;
    }
}
