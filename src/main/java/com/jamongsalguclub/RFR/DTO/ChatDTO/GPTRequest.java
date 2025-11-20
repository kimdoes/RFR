package com.jamongsalguclub.RFR.DTO.ChatDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GPTRequest {

    private String model;
    private List<MessageDTO> messages;

    public GPTRequest(String model, String content, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();

        this.messages.add(new MessageDTO("system", prompt));
        this.messages.add(new MessageDTO("user", content));
    }
}
