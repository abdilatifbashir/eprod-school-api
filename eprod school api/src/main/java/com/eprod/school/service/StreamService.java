package com.eprod.school.service;


import com.eprod.school.model.FormStreamEntity;
import com.eprod.school.repository.FormStreamRepository;
import com.eprod.school.requestWrappers.StreamsWrapper;
import com.eprod.school.requestWrappers.UniversalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class StreamService {
    @Autowired
    FormStreamRepository formStreamRepository;

    /**
     *
     * @param streamsWrapper
     * @return
     */
    public ResponseEntity<UniversalResponse> createFormStream(StreamsWrapper streamsWrapper) {
        Optional<FormStreamEntity> form = formStreamRepository.findByForm(streamsWrapper.getStreamForm());
        if(form.isPresent()){
            return UniversalResponse.responseFormatter(400, 400, "form alreay exist", form);

        }
        FormStreamEntity formStream = new FormStreamEntity();
        formStream.setForm(streamsWrapper.getStreamForm());
        formStream = formStreamRepository.save(formStream);
        return UniversalResponse.responseFormatter(200, 200, "stream added", formStream);


    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    public ResponseEntity<UniversalResponse> getStreamList(int page,int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<FormStreamEntity> all = formStreamRepository.findAll(pageable);
        return UniversalResponse.responseFormatter(200, 200, "stream list", all);
    }

    /**
     *
     * @param id
     * @return
     */
    public ResponseEntity<UniversalResponse> getStream(Long id){
        FormStreamEntity formStream = formStreamRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("form stream with that id not found"));
        return UniversalResponse.responseFormatter(200, 200, "get single stream", formStream );

    }

    }
