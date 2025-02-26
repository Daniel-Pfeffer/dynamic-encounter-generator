package at.jku.deq.service.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
internal class KeywordExtractionService {
    companion object {
        private val LOG = KotlinLogging.logger {}
        private val stopWords = setOf(
            "a",
            "an",
            "the",
            "and",
            "or",
            "but",
            "if",
            "then",
            "else",
            "when",
            "at",
            "by",
            "for",
            "with",
            "about",
            "against",
            "between",
            "into",
            "through",
            "during",
            "before",
            "after",
            "above",
            "below",
            "to",
            "from",
            "up",
            "down",
            "in",
            "out",
            "on",
            "off",
            "over",
            "under",
            "again",
            "further",
            "then",
            "once",
            "here",
            "there",
            "all",
            "any",
            "both",
            "each",
            "few",
            "more",
            "most",
            "other",
            "some",
            "such",
            "no",
            "nor",
            "not",
            "only",
            "own",
            "same",
            "so",
            "than",
            "too",
            "very",
            "s",
            "t",
            "can",
            "will",
            "just",
            "don",
            "should",
            "now"
        )
    }

    fun extractKeywords(description: String): List<String> {
        val words = description
            .lowercase(Locale.getDefault())
            .split("\\W+".toRegex())
            .filter { it.isNotEmpty() && it !in stopWords }

        val wordFrequency = words.groupingBy { it }.eachCount()

        return wordFrequency.entries
            .sortedByDescending { it.value }
            .map { it.key }
            .take(10) // Adjust the number of keywords to extract
    }
}