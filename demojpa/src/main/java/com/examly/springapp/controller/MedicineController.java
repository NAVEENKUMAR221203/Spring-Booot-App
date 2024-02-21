package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Medicine;
import com.examly.springapp.services.MedicineService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class MedicineController {

    private MedicineService medicineService;
    
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping("/medicine")
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        if(medicineService.saveMedicine(medicine)==true)
        {
            return new ResponseEntity<>(medicine,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/medicines")
    public ResponseEntity<List<Medicine>> getlistofMedicines()
    {
        List<Medicine>l=medicineService.listofMedicines();
        if(l.size()==0)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(l,HttpStatus.OK);
        
    }
   
    @GetMapping("/medicines/{medicineId}")
    public ResponseEntity<Medicine> getMethodName(@PathVariable("medicineId") int medicineId) {
        Medicine m=medicineService.getMedById(medicineId);
        if(m==null)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(m,HttpStatus.OK);
        
    }
    
    @PutMapping("api/producted/{medicineId}")
    public ResponseEntity<Medicine> putMethodName(@PathVariable("medicineId") int medicineId, @RequestBody Medicine medicine)
    {
        if(medicineService.updateall(medicineId,medicine)==true)
        {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/prod/{medicineId}")
    public ResponseEntity<Medicine> delete(@PathVariable int medicineId)
    {
        if(medicineService.deleteAll(medicineId)==true)
        {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

}
