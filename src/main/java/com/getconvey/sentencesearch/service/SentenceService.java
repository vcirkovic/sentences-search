package com.getconvey.sentencesearch.service;

import java.util.List;

import com.getconvey.sentencesearch.model.Sentence;

public interface SentenceService
{

	List<Sentence> getAllSentences();

	List<Sentence> getSentencesByWord(String word);
}
