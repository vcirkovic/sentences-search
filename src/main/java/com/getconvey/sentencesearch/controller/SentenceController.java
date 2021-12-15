package com.getconvey.sentencesearch.controller;

import static com.getconvey.sentencesearch.controller.utils.ApiConst.API_V1_BASE_URL;

import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getconvey.sentencesearch.controller.response.SentenceResponse;
import com.getconvey.sentencesearch.service.SentenceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_V1_BASE_URL + "/sentences")
public class SentenceController
{

	private final SentenceService sentenceService;

	/***
	 * This api method offer ability to get sentence response
	 * @param word string (word) to check sentences against
	 * @return SentenceResponse model to make formatted response
	 */
	@GetMapping("count")
	public ResponseEntity<SentenceResponse> getWordCount(@RequestParam("word") String word)
	{
		return Optional.of(sentenceService.getSentencesByWord(word))
			.filter(Predicate.not(CollectionUtils::isEmpty))
			.map(sentences -> new SentenceResponse(sentences, word))
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
