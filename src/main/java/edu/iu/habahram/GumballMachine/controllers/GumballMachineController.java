package edu.iu.habahram.GumballMachine.controllers;

import edu.iu.habahram.GumballMachine.model.GumballMachineRecord;
import edu.iu.habahram.GumballMachine.model.TransitionRequest;
import edu.iu.habahram.GumballMachine.model.TransitionResult;
import edu.iu.habahram.GumballMachine.repository.IGumballRepository;
import edu.iu.habahram.GumballMachine.service.IGumballService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/gumballs")
public class GumballMachineController {

    IGumballService gumballService;

    public GumballMachineController(IGumballService gumballService) {
        this.gumballService = gumballService;
    }

    @GetMapping
    public List<GumballMachineRecord> findAll() {
        try {
            return gumballService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping
    public String addOrUpdate(@RequestBody GumballMachineRecord record) {
        try {
            return gumballService.save(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/insert-quarter")
    public TransitionResult insertQuarter(@RequestBody TransitionRequest transitionRequest) {
        try {
            return gumballService.insertQuarter(transitionRequest.id());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/eject-quarter")
    public TransitionResult ejectQuarter(@RequestBody TransitionRequest request) {
        try {
            return gumballService.ejectQuarter(request.id());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/turn-crank")
    public TransitionResult turnCrank(@RequestBody TransitionRequest request) {
        try {
            return gumballService.turnCrank(request.id());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/refill")
    public void refillMachine(@RequestBody TransitionRequest request) throws IOException {
        System.out.println(request.id() + "ct: " + request.count());
        gumballService.refill(request.id(), request.count());
    }
}
