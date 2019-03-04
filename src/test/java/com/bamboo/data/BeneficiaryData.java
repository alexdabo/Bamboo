/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.data;

import com.bamboo.model.entity.Beneficiary;
import com.bamboo.model.method.BeneficiaryImpl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alexander
 */
public final class BeneficiaryData {

    private final BeneficiaryImpl beneficiaryImpl = new BeneficiaryImpl();
    private final VillageData villageData = new VillageData();
    private final Beneficiary beneficiary;

    public BeneficiaryData() {
        villageData.save();
        this.beneficiary = new Beneficiary(1000000, "2895612450", "Porraz Vázcones", "Joaquin Porras", "Av los alamos", "0926754312", villageData.findById(), "Junto a la pista de hielo");
    }

    public boolean save() {
        boolean saved = false;
        try {
            saved = beneficiaryImpl.save(beneficiary);
            if (saved) {
                System.out.println("Saved:   " + beneficiary);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return saved;
    }

    public List<Beneficiary> find() {
        List<Beneficiary> list = new ArrayList<>();
        try {
            list = beneficiaryImpl.find();
            if (list.size() > 0) {
                System.out.println("Found: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Beneficiary findById() {
        Beneficiary beneficiary1 = null;
        try {
            beneficiary1 = beneficiaryImpl.findById(beneficiary.getId());
            if (beneficiary1 != null) {
                System.out.println("By Id:   " + beneficiary1);
            }
        } catch (Exception e) {
        }
        return beneficiary1;
    }

    public boolean update() {
        boolean updated = false;
        beneficiary.setPlaceReference("Frente a las canchas sinteticas");
        try {
            updated = beneficiaryImpl.update(beneficiary);
            if (updated) {
                System.out.println("Updated: " + beneficiary);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return updated;
    }

    public boolean delete() {
        boolean deleted = false;
        try {
            deleted = beneficiaryImpl.delete(beneficiary);
            if (deleted) {
                villageData.delete();
                System.out.println("Deleted: " + beneficiary);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return deleted;

    }

    public List<Beneficiary> findByData() {
        List<Beneficiary> list = new ArrayList<>();
        try {
            list = beneficiaryImpl.findByData("joa", 0);
            if (list.size() > 0) {
                System.out.println("Data:  ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + list.get(i));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Map<String, Object>> peopleFromVillages() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = beneficiaryImpl.peopleFromVillages();
            if (list.size() > 0) {
                System.out.println("From:  " + new Gson().toJson(list));

            }
        } catch (Exception e) {
        }
        return list;
    }
}
