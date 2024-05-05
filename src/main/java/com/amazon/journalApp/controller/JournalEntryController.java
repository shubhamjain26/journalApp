package com.amazon.journalApp.controller;

import com.amazon.journalApp.entity.JournalEntry;
import com.amazon.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry j = journalEntryService.createEntry(journalEntry);
        return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(id);

        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        JournalEntry journalEntryUpdated = journalEntryService.updateEntry(id, journalEntry);
        return new ResponseEntity<>(journalEntryUpdated, HttpStatus.OK);
    }
}
