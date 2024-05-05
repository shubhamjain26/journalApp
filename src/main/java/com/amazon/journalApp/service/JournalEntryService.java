package com.amazon.journalApp.service;

import com.amazon.journalApp.entity.JournalEntry;
import com.amazon.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry createEntry(JournalEntry journalEntry){
        return journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public JournalEntry updateEntry(ObjectId id, JournalEntry journalEntry){
        Optional<JournalEntry> journalEntryInDBOpt = getJournalEntryById(id);
        if (journalEntryInDBOpt.isPresent()){
            JournalEntry journalEntryInDB = journalEntryInDBOpt.get();
            if (!Objects.equals(journalEntryInDB.getTitle(), journalEntry.getTitle())){
                journalEntryInDB.setTitle(journalEntry.getTitle());
            }
            if (!Objects.equals(journalEntryInDB.getContent(), journalEntry.getContent())){
                journalEntryInDB.setContent(journalEntry.getContent());
            }
            return journalEntryRepository.save(journalEntryInDB);
        }
        return journalEntry;
    }
}
