package com.douzone.handler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.douzone.entity.regist.EarnerUpdateVO;
import java.util.regex.Pattern;

public class RequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EarnerUpdateVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EarnerUpdateVO request = (EarnerUpdateVO) target;
        
        Set<String> allowedParamNames = new HashSet<>(Arrays.asList(
                "earner_name",
                "residence_status",
                "is_native",
                "personal_no",
                "zipcode",
                "address",
                "address_detail",
                "email1",
                "email2",
                "tel1",
                "tel2",
                "tel3",
                "phone1",
                "phone2",
                "phone3",
                "etc",
                "is_tuition",
                "deduction_amount",
                "is_artist",
                "artist_type",
                "ins_reduce"
        ));

        // param_name 값이 allowedParamNames에 포함되지 않으면 에러 발생
        if (!allowedParamNames.contains(request.getParam_name())) {
            errors.rejectValue("param_name", "param_name.invalid", "Invalid param_name.");
        }

        String param_value = request.getParam_value();

        switch (request.getParam_name()) {
            case "is_tuition":
            case "is_artist":
                if (!("Y".equals(param_value) || "N".equals(param_value))) {
                    errors.rejectValue("param_value", "param_value.invalid", "ParamValue should be 'Y' or 'N'.");
                }
                break;

            case "is_native":
                if (!("내".equals(param_value) || "외".equals(param_value))) {
                    errors.rejectValue("param_value", "param_value.invalid", "ParamValue should be '내' or '외'.");
                }
                break;
            case "artist_type":
            	if (!("단기".equals(param_value) || "일반".equals(param_value))) {
                    errors.rejectValue("param_value", "param_value.invalid", "ParamValue should be '단기' or '일반'.");
                }
                break;
            case "tel1":
            case "tel2":
            case "tel3":
            case "phone1":
            case "phone2":
            case "phone3":
                if (!(Pattern.matches("^\\d{3,4}$", param_value))) {
                    errors.rejectValue("param_value", "param_value.invalid", "ParamValue should be a 3-4 digit number.");
                }
                break;
            case "deduction_amount":
                try {
                    int deductionAmount = Integer.parseInt(param_value);
                    if (deductionAmount <= 0) {
                        errors.rejectValue("param_value", "param_value.invalid", "ParamValue should be a positive integer.");
                    }
                } catch (NumberFormatException e) {
                    errors.rejectValue("param_value", "param_value.invalid", "ParamValue should be an integer.");
                }
                break;
            case "email1":
            case "email2":
                if (!param_value.matches("^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*$")) {
                    errors.rejectValue("param_value", "param_value.invalid", "ParamValue should be a valid email address.");
                }
                break;
            case "ins_reduce":
            case "earner_name":
            case "zipcode":
            case "address":
            case "address_detail":
            case "etc":
            case "personal_no":
                break;
        }
        if (param_value.isEmpty()) {
            errors.rejectValue("param_value", "param_value.empty", "ParamValue should not be empty.");
        }
    }
}

