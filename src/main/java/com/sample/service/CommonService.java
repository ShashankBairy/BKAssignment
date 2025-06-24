package com.sample.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sample.dto.CashPaymentDto;
import com.sample.dto.ConcessionDto;
import com.sample.dto.EmployeeDetails;
import com.sample.dto.LoginDto;
import com.sample.dto.PaymentHistoryDto;
import com.sample.dto.RequestCancellationDto;
import com.sample.dto.StudentMajorInfo;
import com.sample.dto.StudentPerformance;
import com.sample.dto.StudentProfileDetails;
import com.sample.dto.StudentRedisDto;
import com.sample.entity.AdditionalDetails;
import com.sample.entity.Campus;
import com.sample.entity.CampusDetails;
import com.sample.entity.FeeDetails;
import com.sample.entity.FeeHeads;
import com.sample.entity.OtherFeeHeads;
import com.sample.entity.Payments;
import com.sample.entity.PocketMoney;
import com.sample.entity.StudentDetails;
import com.sample.entity.StudentTransportDetails;
import com.sample.entity.UserTable;
import com.sample.repository.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonService {

    private final BusAssianRepository busAssianRepository;

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

	@Autowired
	private CampusRepository campusrepo;

    CommonService(BusAssianRepository busAssianRepository) {
        this.busAssianRepository = busAssianRepository;
    }

	public StudentPerformance getStudentPerformance(int studentId) {

		Optional<StudentDetails> student = studentDetailsRepo.findById(studentId);
		if (student.isEmpty()) {
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
		if (payment != null) {
			for (Payments a : payment) {
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
		if (student.isEmpty()) {
			return null;
		}
		return student;
	}

	public List<FeeHeads> getAllFeeHeads() {
		return feeHeadsRepo.findAll();
	}

	public FeeDetails getStudentFeeDetails(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		Optional<FeeDetails> feeDetails = feeDetailsRepo.findByStudentDetails(student);
		if (feeDetails.isEmpty()) {
			return null;
		}
		return feeDetails.get();
	}

	public OtherFeeHeads getStudentOtherFeeDetails(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		Optional<OtherFeeHeads> otherFeeDetails = otherFeeRepo.findByStudentDetails(student);
		if (otherFeeDetails.isEmpty()) {
			return null;
		}
		return otherFeeDetails.get();
	}

	public PocketMoney getStudentPocketMoney(int studentId, String class_type) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		List<Payments> payments = paymentsRepo.findByStudentDetails(student);
		if (payments.isEmpty()) {
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

		if (transportDetails.isEmpty()) {
			return null;
		}

		return transportDetails;

	}

	@Transactional
	public String studentPayment(int studentId, CashPaymentDto cash) {
		// Fetch student details
		StudentDetails student = studentDetailsRepo.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));

		// Fetch fee heads
		FeeHeads feeheads = feeHeadsRepo.findById(cash.getFeeHeadId())
				.orElseThrow(() -> new RuntimeException("Fee head not found"));

		// Fetch campus details
//		List<CampusDetails> campus = campusRepo.findByStudentDetails(student);

		// Create new payment entry
		Payments payment = new Payments();
		payment.setStudentDetails(student);
		payment.setAmount(cash.getAmount());
		payment.setBank_details(cash.getBank_details());
		payment.setClassType(student.getType_class());
		payment.setFee_heads(feeheads.getFee_type());
		payment.setFeeHeadId(feeheads);
		payment.setFee_payment_year(cash.getFee_payment_year());
		payment.setMode_of_payment(cash.getModeOfPayment());
		payment.setPre_paid_reciept_no(cash.getPre_print_reciept_no());
		payment.setPayment_head(feeheads.getFee_type());
		payment.setPayment_status(1);
		payment.setCheck_date(cash.getCheck_date());
		payment.setCheck_no(cash.getCheck_no());

		// Set campus
//        CampusDetails studentCampus = campus.get(0);
//		Campus originalCampus = campusrepo.findById(studentCampus.getId()).orElse(null);
//		payment.setCampus(originalCampus);

		// Handle installment payments for feeHeadId == 6
		int totalDue = 0;
		if (cash.getFeeHeadId() == 6) {
			totalDue = studentTotalDue(studentId);
			totalDue -=cash.getAmount();
			payment.setTotal_due(totalDue);
			int remainingInstallment1 = 40000 - student.getInstallment_1();
			int remainingInstallment2 = 40000 - student.getInstallment_2();
			int remainingInstallment3 = 20000 - student.getInstallment_3();
			int amountToPay = cash.getAmount();

			while (amountToPay > 0) {
				if (remainingInstallment1 > 0 && amountToPay > 0) {
					int amountForInstallment1 = Math.min(remainingInstallment1, amountToPay);
					student.setInstallment_1(student.getInstallment_1() + amountForInstallment1);
					amountToPay -= amountForInstallment1;
					remainingInstallment1 -= amountForInstallment1;
					payment.setAmount(amountToPay);
				} else if (remainingInstallment2 > 0 && amountToPay > 0) {
					int amountForInstallment2 = Math.min(remainingInstallment2, amountToPay);
					student.setInstallment_2(student.getInstallment_2() + amountForInstallment2);
					amountToPay -= amountForInstallment2;
					remainingInstallment2 -= amountForInstallment2;
					payment.setAmount(amountToPay);
				} else if (remainingInstallment3 > 0 && amountToPay > 0) {
					int amountForInstallment3 = Math.min(remainingInstallment3, amountToPay);
					student.setInstallment_3(student.getInstallment_3() + amountForInstallment3);
					amountToPay -= amountForInstallment3;
					remainingInstallment3 -= amountForInstallment3;
					payment.setAmount(amountToPay);
				} else {
					return "Payment exceeds maximum installment limits";
				}
			}
		}
		// Handle akash_books for feeHeadId == 1
		else if (cash.getFeeHeadId() == 1 || cash.getAkashBooks() != null) {
			payment.setAkash_books(cash.getAkashBooks());
		}
		// Handle pocket money for feeHeadId == 5
		else if (cash.getFeeHeadId() == 5 || cash.getPocket_money_amount() != 0) {
			// Fetch payments for the student and class type
			List<Payments> studentPayments = paymentsRepo.findByStudentDetailsAndClassType(student,
					student.getType_class());
			if (studentPayments == null) {
				studentPayments = Collections.emptyList();
			}

			// Search for existing PocketMoney in payments
			Optional<Payments> existingPocketMoneyPayment = studentPayments.stream()
					.filter(p -> p.getPocketMoney() != null).findFirst();

			if (existingPocketMoneyPayment.isPresent()) {
				// Update existing PocketMoney
				PocketMoney pocketMoney = existingPocketMoneyPayment.get().getPocketMoney();
				pocketMoney.setDepositedAmount(pocketMoney.getDepositedAmount() + cash.getAmount());
				pocketMoneyRepo.save(pocketMoney); // Assuming you have a pocketMoneyRepo
				payment.setPocketMoney(pocketMoney); // Link to existing PocketMoney
			} else {
				// Create new PocketMoney
				PocketMoney newPocketMoney = new PocketMoney();
				newPocketMoney.setPocket_money_id(1); // Implement ID generation logic
				newPocketMoney.setDepositedAmount(cash.getPocket_money_amount());
				newPocketMoney.setPocketRefund(0);
				newPocketMoney.setTakenAmount(0);
				newPocketMoney.setFeeHeads(feeheads);
				pocketMoneyRepo.save(newPocketMoney); // Save new PocketMoney
				payment.setPocketMoney(newPocketMoney); // Link to new PocketMoney
			}
		} else {
			payment.setAmount(cash.getAmount());
		}

		// Save payment and student details
		paymentsRepo.save(payment);
		studentDetailsRepo.save(student);

		return "Payment successful";
	}

	public String requestCancellation(int studentId, String class_type, RequestCancellationDto cancel) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);

		List<Payments> payment = paymentsRepo.findByStudentDetailsAndClassType(student, class_type);

		if (payment != null) {
			for (Payments a : payment) {
				a.setStudentDetails(student);
				a.setClassType(class_type);
				a.setRequested_amount(cancel.getPaidAmount());
				paymentsRepo.save(a);
			}
			return "Requested Cancellation Amount";
		}

		return null;
	}

	public List<Payments> paymentHistory(int studentId, String classType) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);

		List<Payments> payment = paymentsRepo.findByStudentDetailsAndClassType(student, student.getType_class());
		if (payment != null) {
			return payment;
		}

		return null;
	}

	public StudentMajorInfo getMajorStudentInfo(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);

		StudentMajorInfo studentInfo = new StudentMajorInfo();

		FeeDetails feedetails = getStudentFeeDetails(studentId);

		studentInfo.setCourseFee(feedetails.getCourse_fee());
		studentInfo.setAddiAmount(feedetails.getAddiAmount());
		studentInfo.setNetFee(feedetails.getNet_fee());
		studentInfo.setServiceTaxPaid(feedetails.getService_tax_paid());
		studentInfo.setFeePaid(feedetails.getFee_paid());
		studentInfo.setFeeDeducation(feedetails.getFeeDeucation());
		studentInfo.setFeeRefund(feedetails.getFee_refund());
		studentInfo.setOverAlldue(feedetails.getOverAlldue());
		studentInfo.setServiceTaxToBePaid(feedetails.getService_tax_to_be_paid());

		String class_type = student.getType_class();

		PocketMoney pocketMoney = getStudentPocketMoney(studentId, class_type);
		if (pocketMoney != null) {
			studentInfo.setPocketRefund(pocketMoney.getPocketRefund());
			studentInfo.setDepositedAmount(pocketMoney.getDepositedAmount());
			studentInfo.setTakenAmount(pocketMoney.getTakenAmount());
		} else {
			studentInfo.setPocketRefund(0);
			studentInfo.setDepositedAmount(0);
			studentInfo.setTakenAmount(0);
		}

		List<StudentTransportDetails> transportDetails = getStudentTransportDetails(studentId);
		if (transportDetails == null || transportDetails.isEmpty()) {
			studentInfo.setAcademicYear(null);
			studentInfo.setTrStatus(null);
			studentInfo.setStop("");
		} else {
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

		List<CampusDetails> campus = campusRepo.findByStudentDetails(student);
		if (campus.isEmpty()) {
			studentInfo.setCampusName(null);
			studentInfo.setCity(null);
		} else {
			CampusDetails studentCampus = campus.get(campus.size() - 1);
			studentInfo.setCampusName(studentCampus.getCampus_name());
			studentInfo.setCity(studentCampus.getCity());
		}

		return studentInfo;
	}

	public List<PaymentHistoryDto> paymentTable(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		if (student == null) {
			return new ArrayList<>();
		}
		String class_type = student.getType_class();

		List<Payments> payments = paymentsRepo.findByStudentDetailsAndClassType(student, class_type);

		List<PaymentHistoryDto> paymentHistoryDtos = new ArrayList<>();

		for (Payments payment : payments) {
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

	public EmployeeDetails userLogin(LoginDto login) {
		UserTable user = userRepo.findById(login.getEmployee_id()).orElse(null);
		if (user != null) {
			String userpassword = user.getPassword();
			if (userpassword.matches(login.getPassword())) {
				EmployeeDetails employee = new EmployeeDetails();
				employee.setEmployee_id(user.getEmployee_id());
				employee.setDesignation(user.getDesignation());
				employee.setCampus_id(user.getCampus().getCampusid());
				return employee;
			} else {
				return null;
			}
		}

		return null;
	}

	public Campus getCampus(int campus_id) {
		Campus campus = campusrepo.findById(campus_id).orElse(null);
		if (campus != null) {
			return campus;
		}

		return null;
	}

	public List<CampusDetails> getStudentCampusDetails(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		if (student != null) {
			List<CampusDetails> campus = campusRepo.findByStudentDetails(student);
			if (campus != null) {
				return campus;
			} else {
				return null;
			}
		}
		return null;
	}

	public String setZero(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		student.setInstallment_1(0);
		student.setInstallment_2(0);
		student.setInstallment_3(0);

		studentDetailsRepo.save(student);

		return "Values changed";
	}

	public String setTotalDue(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		List<Payments> payments = paymentsRepo.findByStudentDetailsAndClassType(student, student.getType_class());

		for (Payments a : payments) {
			a.setTotal_due(120000);
		}
		paymentsRepo.saveAll(payments);
		return "Total value changed";
	}

	public List<StudentDetails> getAllStudents(String admission_status) {
		return studentDetailsRepo.findAll().stream().filter(
				student -> admission_status == null || admission_status.equalsIgnoreCase(student.getAdmission_status()))
				.collect(Collectors.toList());
	}

	@Cacheable(value = "studentProfileCache", key = "#studentId")
	public StudentRedisDto getStudentProfileFromCache(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		if (student == null) {
			return null;
		}

		return new StudentRedisDto(student.getStudentId(), student.getStudentName(), student.getGender(),
				student.getSection(), student.getAdmission_status(), student.getFather_name());

	}
	
	public int studentTotalDue(int studentId) {
		StudentDetails student = studentDetailsRepo.findById(studentId).orElse(null);
		
		List<Payments> payments = paymentsRepo.findByStudentDetailsAndClassType(student, student.getType_class());
		if(payments.isEmpty()) {
			return 100000;
		}
		Payments duePayment = payments.get(payments.size()-1);
		return duePayment.getTotal_due();
		
	}
}
