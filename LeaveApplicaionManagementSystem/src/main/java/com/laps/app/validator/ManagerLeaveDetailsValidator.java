package com.laps.app.validator;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.laps.app.model.LeaveDetails;

@Component
public class ManagerLeaveDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		LeaveDetails ld = (LeaveDetails) target;
		if(ld.getDecision().equals("REJECTED")){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managercomment", "error.managercomment", "Comment is required.");
		}
	}
}
