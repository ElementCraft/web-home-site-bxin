package com.bxin.Home.web.rest;

import com.bxin.Home.domain.Note;
import com.bxin.Home.service.NoteService;
import com.bxin.Home.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小王子
 */
@RestController
@RequestMapping("/api/note")
public class NoteResource {

    @Autowired
    private NoteService noteService;

    @GetMapping("/all")
    public ResponseEntity<List<Note>> all() {

        return ResponseEntity.ok(noteService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.addNew(note));
    }

}
