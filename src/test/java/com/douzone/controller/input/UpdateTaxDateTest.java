package com.douzone.controller.input;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.douzone.entity.input.UpdateTaxDateVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateTaxDateTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUpdateTaxDateNormal() throws Exception {
        UpdateTaxDateVO updateTaxDateVO = UpdateTaxDateVO.builder()
                .payment_date(15)
                .accrual_ym(202201)
                .tax_id(1)
                .build();

        mockMvc.perform(patch("/input/update_taxdate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateTaxDateVO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTaxDateInvalidPaymentDate() throws Exception {
        UpdateTaxDateVO updateTaxDateVO = UpdateTaxDateVO.builder()
                .payment_date(32)
                .accrual_ym(202201)
                .tax_id(1)
                .build();

        mockMvc.perform(patch("/input/update_taxdate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateTaxDateVO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateTaxDateInvalidAccrualYm() throws Exception {
        UpdateTaxDateVO updateTaxDateVO = UpdateTaxDateVO.builder()
                .payment_date(15)
                .accrual_ym(199999)
                .tax_id(1)
                .build();

        mockMvc.perform(patch("/input/update_taxdate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateTaxDateVO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateTaxDateMissingTaxId() throws Exception {
        UpdateTaxDateVO updateTaxDateVO = UpdateTaxDateVO.builder()
                .payment_date(15)
                .accrual_ym(202201)
                .build();

        mockMvc.perform(patch("/input/update_taxdate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateTaxDateVO)))
                .andExpect(status().isBadRequest());
    }
}
