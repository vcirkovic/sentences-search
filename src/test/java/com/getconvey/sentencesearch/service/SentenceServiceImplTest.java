package com.getconvey.sentencesearch.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.getconvey.sentencesearch.model.Sentence;

@SpringBootTest
class SentenceServiceImplTest
{

	@Autowired
	private SentenceService sentenceService;

	@Test
	public void matchingWordInSentence(){

 		//when
		List<Sentence> result = sentenceService.getSentencesByWord("test");

		//then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(1);
		assertThat(result.get(0).getContent()).isEqualTo("He put his hand on the coffeepot to test it and he took down a cup and poured it and walked out and up the hallway.");
		assertThat(result.get(0).getWords()).containsExactlyInAnyOrder("he", "put", "his", "hand", "on", "the", "coffeepot", "to", "test",
			"it", "and", "he", "took", "down", "a", "cup", "and", "poured", "it", "and", "walked", "out", "and", "up", "the", "hallway");
	}

	@Test
	public void matchingWordMultipleSentence(){

		//when
		List<Sentence> result = sentenceService.getSentencesByWord("that");

		//then
		assertThat(result).isNotEmpty();
		assertThat(result).hasSize(28);
		assertThat(result.stream().filter(sentence -> sentence.countMatchingWord("that") > 1).collect(Collectors.toList())).hasSize(4);
	}


	@Test
	public void noMatchingWord(){

		//when
		List<Sentence> result = sentenceService.getSentencesByWord("test-that");

		//then
		assertThat(result).isEmpty();
	}
}