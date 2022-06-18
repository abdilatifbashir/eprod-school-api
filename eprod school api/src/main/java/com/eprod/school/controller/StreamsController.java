package com.eprod.school.controller;

import com.eprod.school.requestWrappers.UniversalResponse;
import com.eprod.school.service.StreamService;
import com.eprod.school.requestWrappers.StreamsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class StreamsController {
    @Autowired
    StreamService streamService;

    @PostMapping("/streams")
    /**
     *auhor:abdilatif
     * create stream
     */
    public ResponseEntity<UniversalResponse> createFormStream(@RequestBody StreamsWrapper streamsWrapper){
        return streamService.createFormStream(streamsWrapper);

    }

    /**
     * author :abdilatif
     * view streams
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/streams/list")
    public ResponseEntity<UniversalResponse> getStreamList(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        if(!page.isPresent() || !size.isPresent())
            return UniversalResponse.responseFormatter(200,400,"please include page and size",null);
        return streamService.getStreamList(page.get(),size.get());
    }


    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/stream/{id}")
    public  ResponseEntity<UniversalResponse> getStream(@PathVariable Long id){
        return streamService.getStream(id);
    }

}
