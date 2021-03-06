package com.kodlamaio.hrmsDemo6.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo6.business.abstracts.LanguageService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo6.dataAccess.abstracts.LanguageDao;
import com.kodlamaio.hrmsDemo6.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {
	
	private LanguageDao languageDao;
	
	@Autowired
	public LanguageManager(LanguageDao languageDao) {
		this.languageDao = languageDao;
	}

	@Override
	public DataResult<List<Language>> getAll() {
		return new SuccessDataResult<List<Language>>("Languages listed succesfully.", this.languageDao.findAll());
	}

	@Override
	public DataResult<Language> get(int id) {
		if (this.languageDao.findById(id).orElse(null) != null) {
			return new SuccessDataResult<Language>("The specified language was found successfully.",
					this.languageDao.findById(id).get());
		} else {
			return new ErrorDataResult<Language>("The specified language is not available.");
		}
	}

	@Override
	public Result add(Language language) {
//		List<Character> degrees = List.of('1', '2', '3', '4', '5');
//		if (languageDegreeIsValid(degrees)) {
//			this.languageDao.save(language);
//			return new SuccessDataResult<Language>("Language added succesfully.");
//		} else {
//			return new ErrorDataResult<Language>("Invalid language degree.");
//		}
		this.languageDao.save(language);
		return new SuccessResult("Language added succesfully.");
	}

	@Override
	public Result delete(int id) {
		this.languageDao.deleteById(id);
		return new SuccessResult("Language deleted succesfully.");
	}

	@Override
	public Result update(Language language) {
		this.languageDao.save(language);
		return new SuccessResult("Language updated succesfully.");
	}

}
