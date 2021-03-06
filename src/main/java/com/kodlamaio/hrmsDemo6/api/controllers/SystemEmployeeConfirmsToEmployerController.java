package com.kodlamaio.hrmsDemo6.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmsDemo6.business.abstracts.SystemEmployeeConfirmToEmployerService;
import com.kodlamaio.hrmsDemo6.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo6.entities.concretes.SystemEmployeeConfirmToEmployer;

@RestController
@RequestMapping("/api/systememployeeconfirmstoemployer")
@CrossOrigin
public class SystemEmployeeConfirmsToEmployerController {

	private SystemEmployeeConfirmToEmployerService systemEmployeeConfirmToEmployerService;

	@Autowired
	public SystemEmployeeConfirmsToEmployerController(
			SystemEmployeeConfirmToEmployerService systemEmployeeConfirmToEmployerService) {
		this.systemEmployeeConfirmToEmployerService = systemEmployeeConfirmToEmployerService;
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.systemEmployeeConfirmToEmployerService.getAll());
	}

	@GetMapping("/get")
	public ResponseEntity<?> get(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToEmployerService.get(id));
	}

	@GetMapping("/getbyemployerid")
	public ResponseEntity<?> getByEmployerId(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToEmployerService.getByEmployerId(id));
	}
	
//	@GetMapping("/getfirstbyemployeridorderbydateofconfirmdesc")
//	public ResponseEntity<?> getFirstByEmployerIdOrderByDateOfConfirmDesc(@RequestParam(name = "id") int id) {
//		return ResponseEntity
//				.ok(this.systemEmployeeConfirmToEmployerService.getFirstByEmployerIdOrderByDateOfConfirmDesc(id));
//	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(
			@Valid @RequestBody SystemEmployeeConfirmToEmployer systemEmployeeConfirmToToEmployer) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToEmployerService.add(systemEmployeeConfirmToToEmployer));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam(name = "id") int id) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToEmployerService.delete(id));
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(
			@Valid @RequestBody SystemEmployeeConfirmToEmployer systemEmployeeConfirmToToEmployer) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToEmployerService.update(systemEmployeeConfirmToToEmployer));
	}

	@PostMapping("/confirmemployer)")
	public ResponseEntity<?> confirmEmployer(@RequestParam(name = "systemEmployeeId") int systemEmployeeId, @RequestParam(name = "employerId") int employerId) {
		return ResponseEntity.ok(this.systemEmployeeConfirmToEmployerService.confirmEmployer(systemEmployeeId, employerId));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>("Validation Errors", validationErrors);
	}

}
