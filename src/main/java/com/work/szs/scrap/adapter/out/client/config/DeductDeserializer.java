package com.work.szs.scrap.adapter.out.client.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.work.szs.scrap.adapter.out.client.model.ScrapResponse;
import com.work.szs.refund.domain.enums.DeductType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeductDeserializer extends JsonDeserializer<List<ScrapResponse.Deduction>> {
    private int year;

    @Override
    public List<ScrapResponse.Deduction> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode rootNode = jp.getCodec().readTree(jp);
        List<ScrapResponse.Deduction> deductions = new ArrayList<>();

        processNationalPension(rootNode, deductions);

        processCreditCardDeductions(rootNode, deductions);

        processTaxCredit(rootNode, deductions);

        return deductions;
    }

    private void processTaxCredit(JsonNode rootNode, List<ScrapResponse.Deduction> deductions) {
        if (rootNode.has("세액공제")) {
            JsonNode taxCreditNode = rootNode.get("세액공제");
            double amount = parseAmount(taxCreditNode.asText());

            deductions.add(new ScrapResponse.Deduction(year, DeductType.TAX_CREDIT, amount));
        }
    }


    private void processNationalPension(JsonNode rootNode, List<ScrapResponse.Deduction> deductions) {
        if (rootNode.has("국민연금")) {
            ArrayNode nationalPensionArray = (ArrayNode) rootNode.get("국민연금");
            for (JsonNode deductionNode : nationalPensionArray) {
                String monthStr = deductionNode.get("월").asText().replaceAll("\\s+", "");
                int year = Integer.parseInt(monthStr.split("-")[0]);
                int month = Integer.parseInt(monthStr.split("-")[1]);
                double amount = parseAmount(deductionNode.get("공제액").asText());

                deductions.add(new ScrapResponse.Deduction(year, month, DeductType.NATIONAL_PENSION, amount));
            }
        }
    }


    private void processCreditCardDeductions(JsonNode rootNode, List<ScrapResponse.Deduction> deductions) {
        if (rootNode.has("신용카드소득공제")) {
            JsonNode creditCardDeductionNode = rootNode.get("신용카드소득공제");
            year = creditCardDeductionNode.get("year").asInt();

            if (creditCardDeductionNode.has("month")) {
                ArrayNode monthsArray = (ArrayNode) creditCardDeductionNode.get("month");
                for (JsonNode monthNode : monthsArray) {
                    monthNode.fields().forEachRemaining(entry -> {
                        String monthStr = entry.getKey().replaceAll("\\s+", "");
                        int month = Integer.parseInt(monthStr);
                        double amount = parseAmount(entry.getValue().asText());

                        deductions.add(new ScrapResponse.Deduction(year, month, DeductType.CREDIT_CARD, amount));
                    });
                }
            }
        }
    }


    private double parseAmount(String amountStr) {
        amountStr = amountStr.replaceAll(",", "").replaceAll("\\s+", "");
        return Double.parseDouble(amountStr);
    }

}
