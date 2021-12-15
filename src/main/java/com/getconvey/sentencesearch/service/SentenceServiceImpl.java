package com.getconvey.sentencesearch.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.getconvey.sentencesearch.model.Sentence;

@Service
public class SentenceServiceImpl implements SentenceService
{

	@Value("classpath:data/sentences.txt")
	private Resource sentencesFile;

	@Override
	public List<Sentence> getAllSentences()
	{
		try (Stream<String> stream = Files.lines(sentencesFile.getFile().toPath()))
		{
			return stream.map(Sentence::new).collect(Collectors.toList());
		}
		catch (IOException e)
		{
			throw new RuntimeException(String.format("File {} not found", sentencesFile.getFilename()));
		}
	}

	@Override
	public List<Sentence> getSentencesByWord(String word)
	{
		return getAllSentences().stream().filter(sentence -> sentence.containsWord(word)).collect(Collectors.toList());
	}
}
