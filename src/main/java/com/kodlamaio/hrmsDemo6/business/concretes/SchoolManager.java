package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.SchoolService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.SchoolDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.School;

@Service
public class SchoolManager implements SchoolService {

	private SchoolDao schoolDao;
	
	@Autowired
	public SchoolManager(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	@Override
	public DataResult<List<School>> getAll() {
		return new SuccessDataResult<List<School>>("Schools listed succesfully.", this.schoolDao.findAll());
	}

	@Override
	public DataResult<School> get(int id) {
		if (this.schoolDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<School>("The specified school was found successfully.",
					this.schoolDao.findById(id).get());
		} else {
			return new ErrorDataResult<School>("The specified school is not available.");
		}
	}

	@Override
	public Result add(School school) {
		this.schoolDao.save(school);
		return new SuccessResult("School added succesfully.");
	}

	@Override
	public Result delete(int id) {
		this.schoolDao.deleteById(id);
		return new SuccessResult("School deleted succesfully.");
	}

	@Override
	public Result update(School school) {
		this.schoolDao.save(school);
		return new SuccessResult("School updated succesfully.");
	}

	@Override
	public DataResult<List<School>> getAllOrderByEndDateDesc() {
		return new SuccessDataResult<List<School>>("Schools listed and ordered by end date (Desc) succesfully.", this.schoolDao.findAllByOrderByEndDateDesc());
	}

	@Override
	public DataResult<List<School>> getAllByEndDateIsNull() {
		return new SuccessDataResult<List<School>>("Schools in progress listed succesfully.", this.schoolDao.findByEndDateIsNull());
	}

	@Override
	public DataResult<List<School>> getAllByEndDateIsNotNullOrderByEndDateDesc() {
		return new SuccessDataResult<List<School>>("Graduated schools listed and ordered by end date (Desc) succesfully.", this.schoolDao.findByEndDateIsNotNullOrderByEndDateDesc());
	}

}
