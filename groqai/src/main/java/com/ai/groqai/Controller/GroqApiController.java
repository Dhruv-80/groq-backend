package com.ai.groqai.Controller;
import com.ai.groqai.Model.GroqRequest;
import com.ai.groqai.Service.GroqApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroqApiController {

    private final GroqApiService groqApiService;

    public GroqApiController(GroqApiService groqApiService) {
        this.groqApiService = groqApiService;
    }

    @PostMapping("/get-completion")
    public String getCompletion(@RequestBody GroqRequest request) {
        return groqApiService.getCompletion(request.getMessage(), request.getModel());
    }
}
