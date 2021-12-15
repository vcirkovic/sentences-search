# Sentence service API

Estimated time: 2 to 4 hours

Given a file containing one sentence per line implement a server that responds to queries like `curl localhost:8080/count?word=eggplant`. The response should be a json document that lists all of the sentences containing the word `eggplant` together with the number of times the word occurs in each sentence.

It should be possible to specify the file at the time the server is deployed by changing values in the configuration file.

In greater detail, the endpoint should be `/count` and the query parameter `word`. The returned json document should have two properties, `word`, the word provided in the `word` parameter, and `sentences`, an array of objects where each object has a `sentence` property and a `count` property.

For example, though we do not expect formatted/indented output.
```json
{
  "word": "eggplant",
  "sentences": [
    {"sentence": "Eggplant or not eggplant?", "count": 2}
    {"sentence": "Many people don't like eggplant.", "count": 1}
  ]
}
```

If there is more than one sentence containing the word, they should be ordered by `count` value decreasing with ties broken by sentence text. You can assume words are separated by whitespace. A file of example sentences is provided in `example.txt` and you can choose to read this as a file or from the classpath as a resource.

# A Spring Boot API for retrieving sentences info.

###Application Bootstrapping and Tech used 
###This application was created using Spring Boot initializer.

`
Java 11
gradle
lombok
`

##Running locally
With java
Jar file can be created using command: mvn package,
and the API can be run with: java -jar [jarFileInTargetFolder].jar

## Points of improvement
- Introduce caching mechanism in  sentence service to avoid loading from file
- add api docs 
- externalize sentencesFile variable to properties file
- sentence logic execution, its done multiple times can be optimized
