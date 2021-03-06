package com.kodlamaio.hrmsDemo6.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToJobAdvertisement;

public interface SystemEmployeeConfirmToJobAdvertisementDao extends JpaRepository<SystemEmployeeConfirmToJobAdvertisement, Integer> {
	SystemEmployeeConfirmToJobAdvertisement findByJobAdvertisement_Id(int id);
	//SystemEmployeeConfirmToJobAdvertisement findFirstByJobAdvertisement_IdOrderByDateOfConfirmDesc(int id);
	long deleteByJobAdvertisement_Id(int id);
}
