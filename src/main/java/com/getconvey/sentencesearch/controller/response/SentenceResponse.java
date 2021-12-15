package com.getconvey.sentencesearch.controller.response;

import java.util.List;
import java.util.stream.Collectors;

import com.getconvey.sentencesearch.model.Sentence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SentenceResponse
{
	private final String word;
	private final List<SentenceWithCount> sentences;

	public SentenceResponse(List<Sentence> sentences, String word)
	{
		this.word = word;
		this.sentences = sentences
			.stream()
			.map(sentence -> new SentenceWithCount(sentence.getContent(), sentence.countMatchingWord(word)))
			.collect(Collectors.toList());
	}

	@Getter
	@AllArgsConstructor
	private static class SentenceWithCount
	{
		private final String sentence;
		private final long count;
	}
}
