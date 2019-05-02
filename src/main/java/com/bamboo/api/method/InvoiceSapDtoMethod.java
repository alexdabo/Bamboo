package com.bamboo.api.method;

import com.bamboo.api.dto.BeneficiaryDto;
import com.bamboo.api.dto.InvoiceSapDto;
import com.bamboo.api.dto.UserDto;
import com.bamboo.model.entity.Invoice;
import com.bamboo.model.entity.SapDetail;
import com.bamboo.model.method.InvoiceImpl;
import com.bamboo.model.method.SapDetailImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class InvoiceSapDtoMethod {

    public List<InvoiceSapDto> findByBeneficiary(int beneficiaryId) throws Exception {
        List<InvoiceSapDto> invoices = new ArrayList<>();
        for (SapDetail sapDetail : new SapDetailImpl().findByBeneficiary(beneficiaryId)) {
            /*
              private int id;
    private int invoiceId;
    private String number;
    private Date dateOfIssue;
    private Double totalToPay;
    private boolean payed;
    private BeneficiaryDto beneficiary;
    private UserDto debtcollector;
    private List<UptakeDto> detail;
            */

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

}
