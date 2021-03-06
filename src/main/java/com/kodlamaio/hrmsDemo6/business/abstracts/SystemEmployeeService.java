package com.kodlamaio.hrmsDemo6.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployee;

public interface SystemEmployeeService {
	DataResult<List<SystemEmployee>> getAll();
	DataResult<SystemEmployee> get(int id);
	
	Result add(SystemEmployee systemEmployee);
	Result delete(int id);
	Result update(SystemEmployee systemEmployee);
	
	DataResult<Boolean> existsSystemEmployeeByNationalityId(String nationalityId);
	DataResult<Boolean> existsSystemEmployeeByEmail(String email);
}
