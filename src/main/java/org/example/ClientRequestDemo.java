package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.example.core.domain.Bicycle;
import org.example.core.domain.Policy;
import org.example.core.domain.RiskType;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class ClientRequestDemo {

    public static void main(String[] args) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        Policy policy = new Policy(List.of(bicycle1, bicycle2, bicycle3));
        String url = "http://localhost:8080/calculator/";
        Double premium = restTemplate.postForObject(url, policy, Double.class);
        System.out.println("PREMIUM: " + premium);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(policy);
        System.out.println(json);

    }

    private static final Bicycle bicycle1 = new Bicycle(
            "Pearl", "Gravel SL EVO", 2015, new BigDecimal(1000),
            List.of(RiskType.THEFT, RiskType.DAMAGE, RiskType.THIRD_PARTY_DAMAGE)
    );

    private static final Bicycle bicycle2 = new Bicycle(
            "Sensa", "V2", 2020, new BigDecimal(225),
            List.of(RiskType.DAMAGE)
    );

    private static final Bicycle bicycle3 = new Bicycle(
            "OTHER", "OTHER", 2019, new BigDecimal(100),
            List.of(RiskType.DAMAGE, RiskType.THIRD_PARTY_DAMAGE)
    );

}