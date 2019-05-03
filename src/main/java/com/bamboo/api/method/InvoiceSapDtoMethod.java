package com.bamboo.api.method;

import com.bamboo.api.dto.InvoiceSapDto;
import com.bamboo.model.entity.Invoice;
import com.bamboo.model.entity.SapDetail;
import com.bamboo.model.method.InvoiceImpl;
import com.bamboo.model.method.SapDetailImpl;

import java.util.ArrayList;
import java.util.List;

public class InvoiceSapDtoMethod {

    public List<InvoiceSapDto> findByBeneficiary(int beneficiaryId) throws Exception {
        List<InvoiceSapDto> invoices = new ArrayList<>();
        for (SapDetail sapDetail : new SapDetailImpl().findByBeneficiary(beneficiaryId)) {
            Invoice invoice = new InvoiceImpl().findById(sapDetail.getInvoice());

            InvoiceSapDto invoiceSapDto = new InvoiceSapDto();
            invoiceSapDto.setId(sapDetail.getId());
            invoiceSapDto.setInvoiceId(invoice.getId());
            invoiceSapDto.setNumber(invoice.getNumber());
            invoiceSapDto.setDateOfIssue(invoice.getDateOfIssue());
            invoiceSapDto.setTotalToPay(invoice.getTotalToPay());
            invoiceSapDto.setPayed(invoice.isPayed());
            invoiceSapDto.setBeneficiary(new BeneficiaryDtoMethod().findById(invoice.getBeneficiary()));
            invoiceSapDto.setDebtcollector(new UserDtoMethod().findById(invoice.getDebtcollector()));
            invoiceSapDto.setDetail(new UptakeDtoMethod().findByInvoice(invoice.getId()));
            invoices.add(invoiceSapDto);

        }
        return invoices;
    }

    public InvoiceSapDto findById(int invoiceId) throws Exception {
        InvoiceSapDto invoiceSapDto = null;

        for (SapDetail sapDetail : new SapDetailImpl().findByInvoice(invoiceId)) {
            Invoice invoice = new InvoiceImpl().findById(sapDetail.getInvoice());

            invoiceSapDto = new InvoiceSapDto();
            invoiceSapDto.setId(sapDetail.getId());
            invoiceSapDto.setInvoiceId(invoice.getId());
            invoiceSapDto.setNumber(invoice.getNumber());
            invoiceSapDto.setDateOfIssue(invoice.getDateOfIssue());
            invoiceSapDto.setTotalToPay(invoice.getTotalToPay());
            invoiceSapDto.setPayed(invoice.isPayed());
            invoiceSapDto.setBeneficiary(new BeneficiaryDtoMethod().findById(invoice.getBeneficiary()));
            invoiceSapDto.setDebtcollector(new UserDtoMethod().findById(invoice.getDebtcollector()));
            invoiceSapDto.setDetail(new UptakeDtoMethod().findByInvoice(invoice.getId()));
        }
        return invoiceSapDto;
    }

}
