package com.example.service;

import com.example.model.ChapterModel;

import java.util.List;

public interface IChapterService {

    ChapterModel findOneChapterBySlug(String slug);

    List<ChapterModel> findAllChapter();

    ChapterModel save(ChapterModel chapterModel);

    void delete(String slug);

    ChapterModel update(ChapterModel chapterModel, String slug);
}
