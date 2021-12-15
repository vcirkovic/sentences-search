package com.getconvey.sentencesearch.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * Data information carrier which also offers word indexes to perform search against.
 */
@Getter
public class Sentence
{
	private List<String> words;
	private String content;

	public Sentence(String sentence)
	{
		this.content = sentence;
		this.words = Optional.ofNullable(sentence)
			.map(se -> se.split("[\\s,]+"))
			.map(Arrays::asList).stream()
			.flatMap(Collection::stream)
			.map(this::makeWordIndex)
			.collect(Collectors.toList());
	}

	public long countMatchingWord(String requestedWord)
	{
		String searchWord = makeWordIndex(requestedWord);
		return words
			.stream()
			.filter(word -> word.equals(searchWord))
			.count();
	}

	private String makeWordIndex(String word)
	{
		return word.trim().toLowerCase().replaceAll("\\.", "");
	}

	public boolean containsWord(String word)
	{
		return countMatchingWord(word) > 0;
	}
}
