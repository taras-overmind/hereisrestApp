package com.project.tyrell.hereisrest.shared;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Hidden
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/getTestEntities")
    public List<TestModel> getAllTestEntities() throws ExecutionException, InterruptedException {
        return testService.getAllTestEntities();
    }

    @GetMapping("/getTestById")
    public TestModel getTestEntityById(@RequestParam String docId) throws ExecutionException, InterruptedException {
        return testService.getEntityById(docId);
    }

    @PostMapping(value = "/createTestEntity", consumes = "application/json", produces = "application/json")
    public String createTestEntity(@RequestBody TestModel testModel) throws ExecutionException, InterruptedException {
        return testService.createEntity(testModel);
    }

    @DeleteMapping("/deleteEntityById")
    public String deleteTestEntityById(@RequestParam String docId) throws ExecutionException, InterruptedException {
        return testService.deleteEntityById(docId);
    }

}
