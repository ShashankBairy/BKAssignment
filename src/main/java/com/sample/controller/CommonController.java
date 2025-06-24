package com.sample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.CashPaymentDto;
import com.sample.dto.ConcessionDto;
import com.sample.dto.EmployeeDetails;
import com.sample.dto.LoginDto;
import com.sample.dto.PaymentHistoryDto;
import com.sample.dto.RequestCancellationDto;
import com.sample.dto.StudentMajorInfo;
import com.sample.dto.StudentPerformance;
import com.sample.dto.StudentProfileDetails;
import com.sample.entity.Campus;
import com.sample.entity.CampusDetails;
import com.sample.entity.FeeDetails;
import com.sample.entity.FeeHeads;
import com.sample.entity.OtherFeeHeads;
import com.sample.entity.Payments;
import com.sample.entity.PocketMoney;
import com.sample.entity.StudentDetails;
import com.sample.entity.StudentTransportDetails;
import com.sample.service.CommonService;

@RestController
@RequestMapping
@CrossOrigin

public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping("/getStudentPerformance")
	public StudentPerformance getStudentPerformance(@RequestParam int studentId) {
		return commonService.getStudentPerformance(studentId);
	}
	
	@PutMapping("/concessionAmount")
	public String giveConcession(@RequestParam int studentId, @RequestBody ConcessionDto concession) {
		return commonService.giveConcession(studentId, concession);
	}

	@GetMapping("/getStudentDetails/{studentId}")
	public Optional<StudentDetails> getStudentInfo(@PathVariable int studentId){
		return commonService.getStudentInformation(studentId);
	}
	
	@GetMapping("/getAllFeeHeads")
	public List<FeeHeads> getAllFeeHeads(){
		return commonService.getAllFeeHeads();
	}
	
	@GetMapping("/getStudentFeeDetails")
	public FeeDetails getStudentFeeDetails(@RequestParam int studentId) {
		return commonService.getStudentFeeDetails(studentId);
	}
	
	@GetMapping("/getStudentOtherFeeDetails")
	public OtherFeeHeads getStudentOtherDetails(@RequestParam int studentId) {
		return commonService.getStudentOtherFeeDetails(studentId);
	}
	
	@GetMapping("/getPM")
	public PocketMoney getStudentPM(int studentId, String class_type) {
		return commonService.getStudentPocketMoney(studentId, class_type);
	}
	
	@PostMapping("/payment")
	public String studentPayment (@RequestParam int studentId, @RequestBody CashPaymentDto cash) {
		System.out.println(studentId);
		System.out.println(cash);
		return commonService.studentPayment(studentId, cash);
	}
	
	@PutMapping("/requestCancellation")
	public String requestCancellation(@RequestParam int studentId,
			@RequestParam String class_type, @RequestBody RequestCancellationDto cancel ) {
		return commonService.requestCancellation(studentId, class_type, cancel);
	}
	
	@GetMapping("/payments")
	public List<Payments> getPaymentsHistory(@RequestParam int studentId, @RequestParam String classType){
		return commonService.paymentHistory(studentId, classType);
	}
	
	@GetMapping("/transportDetails")
	public List<StudentTransportDetails> getStudentTransportInfo(@RequestParam int studentId) {
		return commonService.getStudentTransportDetails(studentId);
	}
	
	@GetMapping("/getStudentInfo")
	public StudentMajorInfo getStudentMajorInfo(@RequestParam int studentId) {
		return commonService.getMajorStudentInfo(studentId);
	}
	
	@GetMapping("/getStudentProfile")
	public StudentProfileDetails getStudentProfileDetails(@RequestParam int studentId) {
		return commonService.getStudentProfile(studentId);
	}
	
	@GetMapping("/paymentsHistory")
	public List<PaymentHistoryDto> getPaymenthistoryinfo(@RequestParam int studentId){
		return commonService.paymentTable(studentId);
	}
	
	@PostMapping("/login")
	public EmployeeDetails userLogin(@RequestBody LoginDto login) {
		return commonService.userLogin(login);
	}
	
	@GetMapping("/campus")
	public Campus getCampus(@RequestParam int campus_id) {
		return commonService.getCampus(campus_id);
	}
	
	@GetMapping("/getStudentCampus")
	public List<CampusDetails> getStudentCampus (@RequestParam int studentId) {
		return commonService.getStudentCampusDetails(studentId);
	}
	
	@GetMapping("/makevalueszero")
	public String makeZeros(@RequestParam int studentId) {
		return commonService.setZero(studentId);
	}
}

