package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.JobAdvertisementService;
import com.kodlamaio.hrmsDemo6.business.abstracts.SystemEmployeeConfirmToJobAdvertisementService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.JobAdvertisementDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.JobAdvertisement;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToJobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	private SystemEmployeeConfirmToJobAdvertisementService systemEmployeeConfirmToJobAdvertisementService;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,
			SystemEmployeeConfirmToJobAdvertisementService systemEmployeeConfirmToJobAdvertisementService) {
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.systemEmployeeConfirmToJobAdvertisementService = systemEmployeeConfirmToJobAdvertisementService;
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		this.jobAdvertisementDao.save(jobAdvertisement);
		this.systemEmployeeConfirmToJobAdvertisementService
				.add(new SystemEmployeeConfirmToJobAdvertisement(jobAdvertisement));
		return new SuccessResult("Job advertisement added successfully.");
	}

	@Override
	public Result delete(int id) {
		this.systemEmployeeConfirmToJobAdvertisementService.deleteByJobAdvertisementId(id);
		this.jobAdvertisementDao.deleteById(id);
		;
		return new SuccessResult("Job advertisement deleted successfully.");
	}

	@Override
	public Result update(JobAdvertisement jobAdvertisement) {
		this.jobAdvertisementDao.save(jobAdvertisement);
		this.systemEmployeeConfirmToJobAdvertisementService
				.add(new SystemEmployeeConfirmToJobAdvertisement(jobAdvertisement));
		return new SuccessResult("Job advertisement updated successfully.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>("Job advertisements listed successfully.",
				this.jobAdvertisementDao.findAll());
	}

	@Override
	public DataResult<JobAdvertisement> get(int id) {
		if (this.jobAdvertisementDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<JobAdvertisement>("The specified job advertisement was found successfully.",
					this.jobAdvertisementDao.findById(id).get());
		} else {
			return new ErrorDataResult<JobAdvertisement>("The specified job advertisement is not available.");
		}
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivated() {
		return new SuccessDataResult<List<JobAdvertisement>>("Active job advertisements listed successfully.",
				this.jobAdvertisementDao.findByActiveTrue());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivatedOrderByReleaseDateAsc() {
		return new SuccessDataResult<List<JobAdvertisement>>(
				"Active job advertisements listed and ordered by release date (Asc) successfully.",
				this.jobAdvertisementDao.findByActiveTrueOrderByReleaseDateAsc());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivatedOrderByApplicationDeadlineAsc() {
		return new SuccessDataResult<List<JobAdvertisement>>(
				"Active job advertisements listed and ordered by application deadline date (Asc) successfully.",
				this.jobAdvertisementDao.findByActiveTrueOrderByApplicationDeadlineAsc());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivatedAndEmployerId(int id) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				"The employer's active job advertisements listed successfully.",
				this.jobAdvertisementDao.findByActiveTrueAndEmployer_Id(id));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivatedAndWorkingTimeType(int workingTimeTypeId) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				"Active job advertisements listed by WorkingTimeType successfully.",
				this.jobAdvertisementDao.findByActiveTrueAndWorkingTimeType_Id(workingTimeTypeId));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivatedAndWorkingPlaceType(int workingPlaceTypeId) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				"Active job advertisements listed by WorkingPlaceType successfully.",
				this.jobAdvertisementDao.findByActiveTrueAndWorkingPlaceType_Id(workingPlaceTypeId));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivatedAndWorkingPlaceTypeAndWorkingTimeType(
			int workingPlaceTypeId, int workingTimeTypeId) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				"Active job advertisements listed by WorkingPlaceType or WorkingTimeType successfully.",
				this.jobAdvertisementDao.findByActiveTrueAndWorkingPlaceType_IdAndWorkingTimeType_Id(workingPlaceTypeId,
						workingTimeTypeId));

	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivatedWithPageable(Pageable pageable) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				"Active job advertisements listed with pageable successfully.",
				this.jobAdvertisementDao.findByActiveTrue(pageable));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActivatedAndWorkingPlaceTypeAndWorkingTimeTypeWithPageable(
			int workingPlaceTypeId, int workingTimeTypeId, Pageable pageable) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				"Active job advertisements listed by WorkingPlaceType or WorkingTimeType with pageable successfully.",
				this.jobAdvertisementDao.findByActiveTrueAndWorkingPlaceType_IdAndWorkingTimeType_Id(workingPlaceTypeId,
						workingTimeTypeId, pageable));
	}

	@Override
	public Result activateJobAdvertisement(int id) {
		JobAdvertisement j = this.jobAdvertisementDao.findById(id).get();
		j.setActive(true);
		this.jobAdvertisementDao.save(j);
		return new SuccessResult("Job advertisement activated successfully.");
	}

	@Override
	public Result deactivateJobAdvertisement(int id) {
		JobAdvertisement j = this.jobAdvertisementDao.findById(id).get();
		j.setActive(false);
		this.jobAdvertisementDao.save(j);
		return new SuccessResult("Job advertisement deactivated successfully.");
	}

}
