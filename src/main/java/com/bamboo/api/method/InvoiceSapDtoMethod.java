package com.bamboo.api.method;

import com.bamboo.api.dto.InvoiceSapDto;
import com.bamboo.api.dto.UptakeDto;
import com.bamboo.model.entity.Invoice;
import com.bamboo.model.entity.Sap;
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

    public InvoiceSapDto save(InvoiceSapDto invoiceSap) throws Exception {
        InvoiceSapDto newInvoiceSap = null;
        boolean saved = false;
        Invoice invoice = new InvoiceImpl().save(getInvoice(invoiceSap));
        if (invoice != null && invoice.getId() != 0) {
            for (UptakeDto uptakeDto : invoiceSap.getDetail()) {
                SapDetail sapDetail = new SapDetail();
                sapDetail.setInvoice(invoice.getId());
                sapDetail.setUptake(uptakeDto.getId());
                sapDetail = new SapDetailImpl().save(sapDetail);
                if (sapDetail != null) {
                    saved = true;
                }
            }
            if (saved) {
                newInvoiceSap.setDetail(new UptakeDtoMethod().findByInvoice(invoice.getId()));
            }
        } else {
            throw new Exception("La factura no se pudo crear.");
        }


        return newInvoiceSap;
    }

    private Invoice getInvoice(InvoiceSapDto invoiceSap) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceSap.getId());
        invoice.setNumber(invoiceSap.getNumber());
        invoice.setDateOfIssue(invoiceSap.getDateOfIssue());
        invoice.setTotalToPay(invoice.getTotalToPay());
        invoice.setIsPayed(invoiceSap.isPayed());
        invoice.setBeneficiary(invoiceSap.getBeneficiary().getId());
        invoice.setDebtcollector(invoiceSap.getDebtcollector().getId());
        return invoice;
    }

    private InvoiceSapDto getInvoiceSap(Invoice invoice) throws Exception {
        InvoiceSapDto invoiceSap = new InvoiceSapDto();
        invoiceSap.setId(invoiceSap.getId());
        invoiceSap.setNumber(invoiceSap.getNumber());
        invoiceSap.setDateOfIssue(invoiceSap.getDateOfIssue());
        invoiceSap.setTotalToPay(invoice.getTotalToPay());
        invoiceSap.setPayed(invoice.isPayed());
        invoiceSap.setBeneficiary(new BeneficiaryDtoMethod().findById(invoice.getBeneficiary()));
        invoiceSap.setDebtcollector(new UserDtoMethod().findById(invoice.getDebtcollector()));
        return invoiceSap;
    }

}
