package com.example.dao;

import com.example.model.ChapterModel;

import java.util.List;

public interface IChapterDAO extends GenericDAO<ChapterModel>{

    List<ChapterModel> findAllChapter();

    ChapterModel findOneChapter(String slug);

    List<ChapterModel> findChapterByQuery(String query);

    Long addChapter (ChapterModel chapterModel);

    void updateChapter (ChapterModel chapterModel, String slug);

    void deleteChapter (String slug);



}
