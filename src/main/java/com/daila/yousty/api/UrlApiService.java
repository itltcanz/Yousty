package com.daila.yousty.api;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
@Component
public class UrlApiService {

    private final String API_URL = "https://api.imgbb.com/1/upload";
    private final String API_KEY = "9d4866589f46edb537b1fb87127290a2";

    public List<String> getUrls(List<String> clothingImages) {
        List<String> urls = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        for (String clothingImage : clothingImages) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("key", API_KEY);
            body.add("image", clothingImage);
            body.add("expiration", 120);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    JSONObject data = jsonObject.getJSONObject("data");
                    String imageUrl = data.getString("url");
                    urls.add(imageUrl);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to parse JSON response", e);
                }
            } else {
                throw new RuntimeException("Failed to upload image: " + response.getStatusCode());
            }
        }
        return urls;
    }
}
