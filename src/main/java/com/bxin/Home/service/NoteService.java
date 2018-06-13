package com.bxin.Home.service;

import com.bxin.Home.domain.Note;
import com.bxin.Home.repository.NoteRepository;
import com.bxin.Home.tools.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    /**
     * 获取全部留言信息
     *
     * @return
     */
    public List<Note> getAll() {
        return noteRepository.findAll(new Sort(Sort.Direction.DESC, "gmtCreate"));
    }

    /**
     * 新增留言
     *
     * @param note 轮播图
     * @return
     */
    public Result addNew(Note note) {

        note.setId(null);
        noteRepository.save(note);

        return Result.ok();
    }
}
