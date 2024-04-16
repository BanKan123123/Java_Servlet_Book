package com.example.paramMapper;

import com.example.model.LoanSlipModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

public class LoanSlipParamMapper {
    public static String mapperParam (HttpServletRequest req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String code = req.getParameter("code");
        int idAccount = Integer.parseInt(req.getParameter("account"));
        int idBook = Integer.parseInt(req.getParameter("book"));
        LoanSlipModel loanSlipModel = new LoanSlipModel(idAccount, idBook, code);
        return mapper.writeValueAsString(loanSlipModel);
    }
}
