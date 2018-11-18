alter table operator
  add constraint fk_operator_role foreign key(roleid) references role(id);

alter table audit
  add constraint fk_audit_operator foreign key(operatorid) references operator(id);


alter table measurer
  add constraint fk_measurer_sap foreign key(sapid) references sap(id),
  add constraint fk_measurer_status foreign key(statusid) references status(id);

alter table beneficiary
  add constraint fk_beneficiary_village foreign key(villageid) references village(id);

alter table uptake
  add constraint fk_uptake_measurer foreign key(measurerid) references measurer(id);

alter table invoice
  add constraint fk_invoice_beneficiary foreign key(beneficiaryid) references beneficiary(id),
  add constraint fk_invoice_debtcollector foreign key(debtcollectorid) references operator(id);


alter table assigned
  add constraint fk_assigned_beneficiary foreign key(beneficiaryid) references beneficiary(id),
  add constraint fk_assigned_measurer foreign key(measurerid) references measurer(id),
  add constraint pk_assigned primary key(beneficiaryid,measurerid);


alter table sapdetail
  add constraint fk_sapdetail_invoice foreign key(invoiceid) references invoice(number),
  add constraint fk_sapdetail_uptake foreign key(uptakeid) references uptake(id),
  add constraint pk_sapdetail primary key(invoiceid,uptakeid);


alter table anotherservicedetail
  add constraint fk_anotherservicedetail_invoice foreign key(invoiceid) references invoice(number),
  add constraint fk_anotherservicedetail_service foreign key(anotherserviceid) references anotherservice(id),
  add constraint pk_anotherservicedetail primary key(invoiceid,anotherserviceid);
