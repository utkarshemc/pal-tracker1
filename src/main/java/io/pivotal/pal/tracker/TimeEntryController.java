package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create (@RequestBody TimeEntry timeEntry)
    {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(createdTimeEntry,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read (@PathVariable long id)
    {
        TimeEntry readTimeEntry = timeEntryRepository.find(id);
        if(readTimeEntry == null)
        {
            return new ResponseEntity<>(readTimeEntry,HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(readTimeEntry, HttpStatus.OK);
        }
    }

    @GetMapping
    public  ResponseEntity<List<TimeEntry>> list ()
    {

        return new ResponseEntity<>(timeEntryRepository.list(),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update (@PathVariable long id,@RequestBody TimeEntry timeEntry)
    {
        TimeEntry updateTimeEntry = timeEntryRepository.update(id,timeEntry);
        if(updateTimeEntry == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(updateTimeEntry, HttpStatus.OK);
        }
    }
   @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete (@PathVariable long id)
    {
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
