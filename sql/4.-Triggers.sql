/*******************************************************************
*                    CALCULAR VALORES DE MEDIDAS                   *
********************************************************************/
CREATE OR REPLACE FUNCTION public.fun_calc_uptake() RETURNS TRIGGER AS $BODY$
declare
  meter_status  integer;
BEGIN

  meter_status = (select statusid from measurer where id = new.measurerid);

  new.lastvaluetaken = (select currentvaluetaken from uptake where measurerid=new.measurerid order by id  desc limit 1);
  if new.lastvaluetaken is null then
    new.lastvaluetaken = 0;
  end if;

	new.basevolume=(select s.basevolume from measurer m inner join sap s on s.id=m.sapid where m.id=new.measurerid);

	new.baseprice= (select s.baseprice  from measurer m inner join sap s on s.id=m.sapid where m.id=new.measurerid);

	new.extraprice=(select s.extraprice from measurer m inner join sap s on s.id=m.sapid where m.id=new.measurerid);



    --When the state is active
    if meter_status = 1 then
      if new.currentvaluetaken >= new.lastvaluetaken then
        new.volumeconsumed = (new.currentvaluetaken - new.lastvaluetaken);
        if new.volumeconsumed > new.basevolume then
          new.volumeexceeded = (new.volumeconsumed - new.basevolume);
          new.totalprice = (new.baseprice + (new.volumeexceeded * new.extraprice));
        else
          new.totalprice = new.baseprice;
          new.volumeexceeded = 0;
        end if;
      else
          RAISE EXCEPTION 'Medida actual menor a la anterior';
      end if;
    end if;

    --When the state is suspended
    if meter_status = 2 then
      RAISE EXCEPTION 'Servicio cortado en el medidor N° %',new.measurerid;
    end if;

    --When the state is damaged
    if meter_status =3 then
      new.currentvaluetaken = new.lastvaluetaken;
      new.volumeexceeded = 0;
      new.volumeconsumed = new.basevolume;
      new.totalprice = new.baseprice;
    end if;

    if meter_status > 3 then
      RAISE EXCEPTION 'No existe reglas para sap con id=%', meter_status;
    end if;

 	return new;

END
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER calc_uptake before insert  on uptake
FOR EACH ROW EXECUTE PROCEDURE fun_calc_uptake();
