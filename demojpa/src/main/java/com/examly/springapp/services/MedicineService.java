package com.examly.springapp.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Medicine;
import com.examly.springapp.repository.MedicineRepo;

@Service
public class MedicineService {
    private MedicineRepo medicineRepo;

    public MedicineService(MedicineRepo medicineRepo) {
        this.medicineRepo = medicineRepo;
    }

    public boolean saveMedicine(Medicine medicine) {

        try{
            medicineRepo.save(medicine);
        }
        catch(Exception e)
        {
            System.out.println("\nException");
            System.out.println("e.getMessage()");
            return false;
        
        }
        return true;
    }
    
    public List<Medicine>listofMedicines()
    {
        return medicineRepo.findAll();
    }

    public Medicine getMedById(int id)
    {
        return medicineRepo.findById(id).orElse(new Medicine());
    }
    public boolean updateall(int medicineId,Medicine medicine)
    {
        if(this.getMedById(medicineId) ==null)
        {
            return false;
        }
        try{
            medicineRepo.save(medicine);
        }
        catch(Exception e)
        {
            System.out.println("Exception:");
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean deleteAll(int medicineId)
    {
        if(this.getMedById(medicineId) ==null)
        {
            return false;
        }
        try{
            medicineRepo.deleteById(medicineId);
        }
        catch(Exception e)
        {
            System.out.println("Exception:");
            System.out.println(e);
            return false;
        }
        return true;
    }

    
}
