CREATE VIEW public.assignedmeaurer AS
 SELECT beneficiary.id AS beneficiary_id,
    (((beneficiary.lastname)::text || ' '::text) || (beneficiary.firstname)::text) AS beneficiary_name,
    measurer.number AS measurer_number,
    sap.name AS measurer_service,
    status.name AS measurer_status,
    measurer.installationdate AS measurer_instalation_date,
    assigned.assignmentdate,
    assigned.debt,
    assigned.status
   FROM ((((public.assigned
     JOIN public.measurer ON ((assigned.measurerid = measurer.id)))
     JOIN public.beneficiary ON ((assigned.beneficiaryid = beneficiary.id)))
     JOIN public.sap ON ((measurer.sapid = sap.id)))
     JOIN public.status ON ((measurer.statusid = status.id)));