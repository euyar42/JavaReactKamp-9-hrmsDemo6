package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToEmployer;

public interface SystemEmployeeConfirmToEmployerService {
	DataResult<List<SystemEmployeeConfirmToEmployer>> getAll();
	DataResult<SystemEmployeeConfirmToEmployer> getByEmployerId(int id);
	
	Result add(SystemEmployeeConfirmToEmployer systemEmployeeConfirmToEmployer);
	Result delete(int id);
	Result update(SystemEmployeeConfirmToEmployer systemEmployeeConfirmToEmployer);
}