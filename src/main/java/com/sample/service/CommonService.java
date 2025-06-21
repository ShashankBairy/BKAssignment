package com.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dto.CashPaymentDto;
import com.sample.dto.ConcessionDto;
import com.sample.dto.LoginDto;
import com.sample.dto.PaymentHistoryDto;
import com.sample.dto.RequestCancellationDto;
import com.sample.dto.StudentMajorInfo;
import com.sample.dto.StudentPerformance;
import com.sample.dto.StudentProfileDetails;
import com.sample.entity.AdditionalDetails;
import com.sample.entity.CampusDetails;
import com.sample.entity.FeeDetails;
import com.sample.entity.FeeHeads;
import com.sample.entity.OtherFeeHeads;
import com.sample.entity.Payments;
import com.sample.entity.PocketMoney;
import com.sample.entity.StudentDetails;
import com.sample.entity.StudentTransportDetails;
import com.sample.entity.UserTable;
import com.sample.repository.AdditionalDetailsRepository;
import com.sample.repository.CampusDetailsRepository;
import com.sample.repository.FeeDetailsRepository;
import com.sample.repository.FeeHeadsRepository;
import com.sample.repository.OtherFeeHeadsRepository;
import com.sample.repository.PaymentsRepository;
import com.sample.repository.PocketMoneyRepository;
import com.sample.repository.StudentDetailsRepository;
import com.sample.repository.StudentTransportRepository;
import com.sample.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonService {
	
	@Autowired
	private StudentDetailsRepository studentDetailsRepo;
	
	@Autowired
	private PaymentsRepository paymentsRepo;
	
	@Autowired
	private FeeHeadsRepository feeHeadsRepo;
	
	@Autowired
	private FeeDetailsRepository feeDetailsRepo;
	
	@Autowired
	private OtherFeeHeadsRepository otherFeeRepo;
	
	@Autowired
	private PocketMoneyRepository pocketMoneyRepo;
	
	@Autowired
	private CampusDetailsRepository campusRepo;
	
	@Autowired
	private StudentTransportRepository transportRepo;
	
	@Autowired
	private AdditionalDetailsRepository additionalRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public StudentPerformance getStudentPerformance(int studentId) {
		
		Optional<StudentDetails> student = studentDetailsRepo.findById(studentId);
		if(student.isEmpty()) {
			return null;
		}
		
		StudentPerformance studentP = new StudentPerformance();
		studentP.setIpeMarks(student.get().getIpe_marks());
		studentP.setRecentMarks(student.get().getRecent_marks());
		studentP.setEmcetMockTest(student.get().getEmcet_mock_test());
		studentP.setAttendence(student.get().getAttendance());
		return studentP;
	}
	
	public String giveConcession(int studentId, ConcessionDto concession) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		String classType = student.getType_class();
		List<Payments> payment = paymentsRepo.findByStudentDetailsAndClassType(student, classType);
		if(payment != null) {
			for(Payments a : payment) {
				a.setStudentDetails(student);
				a.setConcession_amount(concession.getConcessionAmount());
				paymentsRepo.save(a);
			}
			return "Concession amount transaction successfull";
		}
		return null;
	}
	
	public Optional<StudentDetails> getStudentInformation(int studentId) {
		Optional<StudentDetails> student = studentDetailsRepo.findById(studentId);
		if(student.isEmpty()) {
			return null;
		}
		return student;
	}

	public List<FeeHeads> getAllFeeHeads(){
		return feeHeadsRepo.findAll();
	}
	
	public FeeDetails getStudentFeeDetails(int studentId){
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		Optional<FeeDetails> feeDetails = feeDetailsRepo.findByStudentDetails(student);
		if(feeDetails.isEmpty()) {
			return null;
		}
		return feeDetails.get();
	}
	
	public OtherFeeHeads getStudentOtherFeeDetails(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		Optional<OtherFeeHeads> otherFeeDetails = otherFeeRepo.findByStudentDetails(student);
		if(otherFeeDetails.isEmpty()) {
			return null;
		}
		return otherFeeDetails.get();
	}
	
	
	public PocketMoney getStudentPocketMoney(int studentId, String class_type) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		List<Payments> payments = paymentsRepo.findByStudentDetails(student);
		if(payments.isEmpty()) {
			return null;
		}
		for (Payments payment : payments) {
	        if (payment.getClassType() != null && payment.getClassType().equals(class_type)) {
	            PocketMoney pocketMoney = payment.getPocketMoney();
	            if (pocketMoney != null) {
	                return pocketMoneyRepo.findById(pocketMoney.getPocket_money_id()).orElse(null);
	            }
	        }
	    }
		return null;
	}
	
	public List<StudentTransportDetails> getStudentTransportDetails(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		
		List<StudentTransportDetails> transportDetails = transportRepo.findByStudentDetails(student);
		
		if(transportDetails.isEmpty()) {
			return null;
		}
		
		return transportDetails;
		
	}
	
	public String studentPayment(int studentId, CashPaymentDto cash) {
//		logger.info("Received studentId: {}, feeHeadId: {}", studentId, cash.getFeeHeadId());
		
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		
		FeeHeads feeheads = feeHeadsRepo.findById(cash.getFeeHeadId()).orElse(null);
		
		CampusDetails campus = campusRepo.findByStudentDetails(student); 
		
		Payments payment = new Payments();
		
		payment.setStudentDetails(student);
		payment.setAmount(cash.getAmount());
		payment.setBank_details(cash.getBank_details());
		payment.setClassType(student.getType_class());
		payment.setFee_heads(feeheads.getFee_type());
		payment.setFeeHeadId(feeheads);
		payment.setFee_payment_year(cash.getFee_payment_year());
		payment.setMode_of_payment("Cash");
		payment.setPre_paid_reciept_no(cash.getPre_print_reciept_no());
		payment.setPayment_head(feeheads.getFee_type());
//		payment.setCampusDetails();
		payment.setInstallment_no(1);
		payment.setPayment_status(1);
		
		paymentsRepo.save(payment);
		
		return "Payment successfull";
		
	}
	
	public String requestCancellation(int studentId, String class_type, RequestCancellationDto cancel) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		
		List<Payments> payment = paymentsRepo.findByStudentDetailsAndClassType(student, class_type);
		
		if(payment != null) {
			for(Payments a : payment) {
				a.setStudentDetails(student);
				a.setClassType(class_type);
				a.setRequested_amount(cancel.getPaidAmount());
				paymentsRepo.save(a);
			}
			return "Requested Cancellation Amount";
		}
		
		return null;
	}
	
	public List<Payments> paymentHistory(int studentId, String classType){
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		
		List<Payments> payment = paymentsRepo.findByStudentDetailsAndClassType(student, classType);
		if(payment != null) {
			return payment;
		}
		
		return null;
	}
	
	public StudentMajorInfo getMajorStudentInfo(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		
		StudentMajorInfo studentInfo = new StudentMajorInfo();
		
		FeeDetails feedetails = getStudentFeeDetails(studentId);
		
		studentInfo.setCourseFee(feedetails.getCourseFee());
		studentInfo.setAddiAmount(feedetails.getAddiAmount());
		studentInfo.setNetFee(feedetails.getNetFee());
		studentInfo.setServiceTaxPaid(feedetails.getServiceTaxPaid());
		studentInfo.setFeePaid(feedetails.getFeePaid());
		studentInfo.setFeeDeducation(feedetails.getFeeDeucation());
		studentInfo.setFeeRefund(feedetails.getFeeRefund());
		studentInfo.setOverAlldue(feedetails.getOverAlldue());
		studentInfo.setServiceTaxToBePaid(feedetails.getServiceTaxToBePaid());
		
		String class_type = student.getType_class();
		
		PocketMoney pocketMoney = getStudentPocketMoney(studentId, class_type);
		if(pocketMoney != null) {
			studentInfo.setPocketRefund(pocketMoney.getPocketRefund());
			studentInfo.setDepositedAmount(pocketMoney.getDepositedAmount());
			studentInfo.setTakenAmount(pocketMoney.getTakenAmount());
		}
		else {
			studentInfo.setPocketRefund(0);
			studentInfo.setDepositedAmount(0);
			studentInfo.setTakenAmount(0);
		}
		
		List<StudentTransportDetails> transportDetails = getStudentTransportDetails(studentId);
		if(transportDetails.isEmpty()) {
			studentInfo.setAcademicYear(null);
			studentInfo.setTrStatus(null);
			studentInfo.setStop("");
		}
		else {
			StudentTransportDetails lastDetail = transportDetails.get(transportDetails.size() - 1);
		    studentInfo.setAcademicYear(lastDetail.getAcademicYear());
		    studentInfo.setTrStatus(lastDetail.getTrStatus());
		    studentInfo.setStop(lastDetail.getStop());
		}
		
		OtherFeeHeads others = getStudentOtherFeeDetails(studentId);
		studentInfo.setAkash_books(others.getAkash_books());
		studentInfo.setBus_pss(others.getBus_pss());
		studentInfo.setCaution_deposite(others.getCaution_deposite());
		studentInfo.setCrd_books(others.getCrd_books());
		studentInfo.setMiscellaneous(others.getMiscellaneous());
		studentInfo.setStd_welfare_fund(others.getStd_welfare_fund());
		studentInfo.setMaterial(others.getMaterial());
		studentInfo.setEamcet_app_fee(others.getEamcet_app_fee());
		
		return studentInfo;
		
	}
	
	public StudentProfileDetails getStudentProfile(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		
		StudentProfileDetails studentInfo = new StudentProfileDetails();
		studentInfo.setAadhar_no(student.getAadhar_no());
		studentInfo.setAddress(student.getAddress());
		studentInfo.setAdmission_status(student.getAdmission_type());
		studentInfo.setAdmission_type(student.getAdmission_type());
		studentInfo.setAttendance(student.getAttendance());
		studentInfo.setConcession(student.getConcession());
		studentInfo.setCourse_track(student.getCourse_track());
		studentInfo.setDob(student.getDob());
		studentInfo.setEmail(student.getEmail());
		studentInfo.setEmcet_mock_test(student.getEmcet_mock_test());
		studentInfo.setFather_name(student.getFather_name());
		studentInfo.setGender(student.getGender());
		studentInfo.setGroup_name(student.getGroup_name());
		studentInfo.setIpe_marks(student.getIpe_marks());
		studentInfo.setMobile_no(student.getMobile_no());
		studentInfo.setMother_name(student.getMother_name());
		studentInfo.setRecent_marks(student.getRecent_marks());
		studentInfo.setSection(student.getSection());
		studentInfo.setStudent_type(student.getStudent_type());
		studentInfo.setStudentName(student.getStudentName());
		studentInfo.setType_class(student.getType_class());
		
		AdditionalDetails addDetails = additionalRepo.findByStudentDetails(student);
		studentInfo.setLanguage1(addDetails.getLanguage1());
		studentInfo.setLanguage2(addDetails.getLanguage2());
		studentInfo.setLanguage3(addDetails.getLanguage3());
        
		CampusDetails campus = campusRepo.findByStudentDetails(student);
		if(campus != null) {
			studentInfo.setCity(campus.getCity());
			studentInfo.setCampusName(campus.getCampusName());
			
		}
		else {
			studentInfo.setCity("");
			studentInfo.setCampusName("");
			
		}
		return studentInfo;
	}
	
	public List<PaymentHistoryDto> paymentTable(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		if(student == null) {
			return new ArrayList<>();
		}
		String class_type = student.getType_class();
		
		List<Payments> payments = paymentsRepo.findByStudentDetailsAndClassType(student, class_type);
		
		List<PaymentHistoryDto> paymentHistoryDtos = new ArrayList<>();
		
		for(Payments payment : payments) {
			PaymentHistoryDto phdto = new PaymentHistoryDto();
			phdto.setClass_type(payment.getClassType());
			phdto.setAmount(payment.getAmount());
			phdto.setDate(payment.getCreated_on());
			phdto.setFee_head(payment.getFee_heads());
			phdto.setInstallment_no(payment.getInstallment_no());
			phdto.setMode_of_payment(phdto.getMode_of_payment());
			phdto.setPayment_campus("Campus CEVAEE");
			phdto.setPayment_head(payment.getPayment_head());
			paymentHistoryDtos.add(phdto);
		}
		
		return paymentHistoryDtos;
		
	}
	
	public String userLogin (LoginDto login) {
		UserTable user = userRepo.findById(login.getEmployee_id()).orElse(null);
		if(user != null) {
			String userpassword = user.getPassword();
			if(userpassword.matches(login.getPassword())) {
				return "Login Successfull";
			}
			else {
			    return "Password Incorrect";	
			}
		}
		
		return "User Not Found";
	}
	
}
