package com.example.dao;

import com.example.model.BookModel;
import com.example.model.ChapterModel;

import java.util.List;

public interface IChapterDAO extends GenericDAO<ChapterModel>{

    List<ChapterModel> findAllChapter();

    ChapterModel findOneChapter(String slug);

    Long addChapter (ChapterModel chapterModel);

    void updateChapter (ChapterModel chapterModel, String slug);

    void deleteChapter (String slug);



}
