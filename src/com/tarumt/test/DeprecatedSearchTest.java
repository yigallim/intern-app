package com.tarumt.test;

import com.tarumt.utility.common.Strings;
import com.tarumt.utility.search.FuzzySearch;

import java.util.*;

public class DeprecatedSearchTest {

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        double wLev = 0.5;
        double wCos = 0.5;

        // Typo multi-word queries with subtler typos, sentences using only corrected words in different sequence with added words at start or end
        testCases.add(new TestCase("Multi-word query test cases (different sequence, no gap)"));
        testCases.add(new TestCase("machien learnign algoirthms", "algorithms learning machine improved", 0.6, wLev, wCos, "Typo in query: 'i' to 'ie', 'i' to 'ig', 'r' to 'ri'"));
        testCases.add(new TestCase("cluod computnig soltuions", "solutions computing cloud emerged", 0.6, wLev, wCos, "Typo in query: 'o' to 'uo', 'i' to 'ni', 'i' to 'io'"));
        testCases.add(new TestCase("artifical intellgence ap", "showcased intelligence applications artificial", 0.6, wLev, wCos, "Typo in query: 'i' to 'ia', 'i' to 'ig', 'p' to 'ap'"));
        testCases.add(new TestCase("blockchian transatcion securtiy", "security transaction blockchain expanded", 0.6, wLev, wCos, "Typo in query: 'a' to 'ai', 'c' to 'ct', 'i' to 'ti'"));
        testCases.add(new TestCase("qauntum compuet advancemets", "advancements computer quantum discussed", 0.6, wLev, wCos, "Typo in query: 'u' to 'au', 'u' to 'ue', 'n' to 'ne'"));
        testCases.add(new TestCase("data scinece trennds", "trends science data reviewed", 0.6, wLev, wCos, "Typo in query: 'a' to 'at', 'e' to 'ec', 'd' to 'dd'"));
        testCases.add(new TestCase("neuarl netowrk modles", "solved models network neural", 0.6, wLev, wCos, "Typo in query: 'r' to 'ra', 'o' to 'ow', 'e' to 'le'"));
        testCases.add(new TestCase("cyebr secuirity polices", "policies security cyber updated", 0.6, wLev, wCos, "Typo in query: 'y' to 'ye', 'u' to 'ui', 'i' to 'ie'"));
        testCases.add(new TestCase("big datta analyitcs", "enhanced analytics data big", 0.6, wLev, wCos, "Typo in query: 'g' to 'ig', 't' to 'tt', 'i' to 'ti'"));
        testCases.add(new TestCase("deep divee anlaysis", "analysis dive deep revealed", 0.6, wLev, wCos, "Typo in query: 'p' to 'ep', 'e' to 'ee', 'y' to 'ay'"));

        // Typo multi-word queries with different word sequences in sentences
        testCases.add(new TestCase("Multi-word query test cases (different sequence, gapped)"));
        // Typo multi-word queries with subtler typos and different word sequences in sentences
        testCases.add(new TestCase("machien learnign algoirthms", "Learning algorithms in machine systems improved outcomes", 0.6, wLev, wCos, "Typo in query: 'i' to 'ie', 'i' to 'ig', 'r' to 'ri'"));
        testCases.add(new TestCase("cluod computnig soltuions", "Solutions for computing in cloud platforms grew", 0.6, wLev, wCos, "Typo in query: 'o' to 'uo', 'i' to 'ni', 'i' to 'io'"));
        testCases.add(new TestCase("artifical intellgence ap", "Intelligence applications of artificial tech were presented", 0.6, wLev, wCos, "Typo in query: 'i' to 'ia', 'i' to 'ig', 'p' to 'ap'"));
        testCases.add(new TestCase("blockchain transatcion securtiy", "Security for transactions using blockchain expanded", 0.6, wLev, wCos, "Typo in query: 'a' to 'ai', 'c' to 'ct', 'i' to 'ti'"));
        testCases.add(new TestCase("qauntum compute advancemets", "Advancements in computer quantum tech were shared", 0.6, wLev, wCos, "Typo in query: 'u' to 'au', 'u' to 'ue', 'n' to 'ne'"));
        testCases.add(new TestCase("data scinece trennds", "Trends in science data for 2025 were reviewed", 0.6, wLev, wCos, "Typo in query: 'a' to 'at', 'e' to 'ec', 'd' to 'dd'"));
        testCases.add(new TestCase("neuarl netowrk models", "Models of network neural systems solved issues", 0.6, wLev, wCos, "Typo in query: 'r' to 'ra', 'o' to 'ow', 'e' to 'le'"));
        testCases.add(new TestCase("cyebr secuirity polices", "Policies on security cyber threats were revised", 0.6, wLev, wCos, "Typo in query: 'y' to 'ye', 'u' to 'ui', 'i' to 'ie'"));
        testCases.add(new TestCase("big datta analyitcs", "Analytics using data big enhanced performance", 0.6, wLev, wCos, "Typo in query: 'g' to 'ig', 't' to 'tt', 'i' to 'ti'"));
        testCases.add(new TestCase("deep divee anlaysis", "Analysis from dive deep uncovered trends", 0.6, wLev, wCos, "Typo in query: 'p' to 'ep', 'e' to 'ee', 'y' to 'ay'"));

        // Multi-word query test cases
        testCases.add(new TestCase("Multi-word query test cases (same sequence, no gap)"));
        // Multi-word query test cases with typos in queries
        testCases.add(new TestCase("clod computting", "The rise of cloud computing has transformed how businesses operate in the digital era", 0.6, wLev, wCos, "Typo in query: missing 'u', repeated 't'"));
        testCases.add(new TestCase("mashine learnng", "Machine learning algorithms helped predict trends in the massive dataset we analyzed", 0.6, wLev, wCos, "Typo in query: 'c' to 's', missing 'i', missing 'i'"));
        testCases.add(new TestCase("artifical intellignce", "The artificial intelligence conference showcased innovative applications across industries", 0.6, wLev, wCos, "Typo in query: 'i' to 'a', 'e' to 'i', extra 'n'"));
        testCases.add(new TestCase("depp div analisys", "We conducted a deep dive analysis to uncover insights from the complex financial reports", 0.6, wLev, wCos, "Typo in query: repeated 'p', missing 'e', 'y' to 'i'"));
        testCases.add(new TestCase("blokchian techology", "Blockchain technology is revolutionizing secure transactions in the fintech sector", 0.6, wLev, wCos, "Typo in query: 'c' to 'k', missing 'n', 'n' to 'l'"));
        testCases.add(new TestCase("quantm compuing", "The seminar on quantum computing explored advancements in modern computational methods", 0.6, wLev, wCos, "Typo in query: missing 'u', 't' to 'u'"));
        testCases.add(new TestCase("datta scienc trnds", "Data science trends for 2024 were discussed at the annual tech symposium", 0.6, wLev, wCos, "Typo in query: repeated 't', missing 'e', missing 'e'"));
        testCases.add(new TestCase("nural netwrok", "Neural networks are essential for solving complex problems in AI research", 0.6, wLev, wCos, "Typo in query: missing 'e', swapped 'o' and 'r'"));
        testCases.add(new TestCase("cybre secrity threts", "Cyber security threats are increasing, requiring robust defense strategies for companies", 0.6, wLev, wCos, "Typo in query: 'e' to 'r', missing 'u', missing 'a'"));
        testCases.add(new TestCase("bgi daat analitics", "The team used big data analytics to improve decision-making processes", 0.6, wLev, wCos, "Typo in query: 'i' to 'g', repeated 'a', 'y' to 'i'"));

        // One-to-one word query test case
        testCases.add(new TestCase("One-to-one word query test case"));
        testCases.add(new TestCase("book", "cook", 0.6, wLev, wCos, "Typo in query: missing 'u', repeated 't'"));
        testCases.add(new TestCase("sun", "snu", 0.6, wLev, wCos, "Typo in query: 'c' to 's', missing 'i', missing 'i'"));
        testCases.add(new TestCase("big", "bgi", 0.6, wLev, wCos, "Typo in query: repeated 'p', missing 'e', 'y' to 'i'"));
        testCases.add(new TestCase("cloud", "clud", 0.6, wLev, wCos, "Typo in query: 'i' to 'a', 'e' to 'i', extra 'n'"));

        // Exact match test cases
        testCases.add(new TestCase("Exact match test cases"));
        testCases.add(new TestCase("ice", "The cold ice melted slowly in the warm sunny afternoon", 0.6, wLev, wCos, "Exact match single word"));
        testCases.add(new TestCase("ice cube", "I dropped an ice cube while preparing a refreshing summer drink", 0.6, wLev, wCos, "Exact match two words"));
        testCases.add(new TestCase("hello", "Hello everyone welcome to the annual gathering of friends and family", 0.6, wLev, wCos, "Exact match at start"));
        testCases.add(new TestCase("world", "Exploring the vast world requires courage and an adventurous spirit", 0.6, wLev, wCos, "Exact match in middle"));
        testCases.add(new TestCase("test case", "This is a test case scenario designed for evaluating software", 0.6, wLev, wCos, "Exact match multi-word"));
        testCases.add(new TestCase("start", "Start the engine before the race begins on this sunny day", 0.6, wLev, wCos, "Exact match at start"));
        testCases.add(new TestCase("end", "We reached the end after a long journey through mountains", 0.6, wLev, wCos, "Exact match at end"));
        testCases.add(new TestCase("a b c", "Learning a b c helps kids understand the basics of language", 0.6, wLev, wCos, "Exact match three words"));
        testCases.add(new TestCase("nothing", "There was nothing to see in the vast empty desert", 0.6, wLev, wCos, "Exact match in middle"));
        testCases.add(new TestCase("match", "This will match perfectly with the theme of our event", 0.6, wLev, wCos, "Exact match in middle"));

        // Typo test cases
        testCases.add(new TestCase("Typo test cases"));
        testCases.add(new TestCase("hallo", "Hello everyone how are you doing on this fine morning", 0.4, wLev, wCos, "Typo: single character difference"));
        testCases.add(new TestCase("worl", "The world is vast and full of mysteries waiting", 0.4, wLev, wCos, "Typo: missing character"));
        testCases.add(new TestCase("tesst", "This test case was challenging but fun to solve", 0.4, wLev, wCos, "Typo: repeated character"));
        testCases.add(new TestCase("icee", "I ate ice cream during the hot summer afternoon", 0.4, wLev, wCos, "Typo: extra character"));
        testCases.add(new TestCase("ise", "The ice melted quickly under the bright scorching sun", 0.4, wLev, wCos, "Typo: wrong character"));
        testCases.add(new TestCase("helo", "Hello there welcome to this amazing event we planned", 0.4, wLev, wCos, "Typo: missing character"));
        testCases.add(new TestCase("wrold", "The world peace initiative gained support from many", 0.4, wLev, wCos, "Typo: swapped characters"));
        testCases.add(new TestCase("crem", "Ice cream was served at the party last night", 0.4, wLev, wCos, "Typo: missing character"));
        testCases.add(new TestCase("teest", "This test was difficult for students new to programming", 0.4, wLev, wCos, "Typo: repeated character"));
        testCases.add(new TestCase("matc", "The match ended with an unexpected twist in score", 0.4, wLev, wCos, "Typo: missing character"));

        // Case insensitive test cases
        testCases.add(new TestCase("Case insensitive test cases"));
        testCases.add(new TestCase("ICE", "The ice was slippery on the frozen lake today", 0.6, wLev, wCos, "Case insensitive: uppercase query"));
        testCases.add(new TestCase("Hello", "Hello everyone please take your seats for the show", 0.6, wLev, wCos, "Case insensitive: mixed case query"));
        testCases.add(new TestCase("world", "The WORLD is changing rapidly with new technology", 0.6, wLev, wCos, "Case insensitive: uppercase sentence"));
        testCases.add(new TestCase("Test Case", "This test case is part of our evaluation process", 0.6, wLev, wCos, "Case insensitive: mixed case multi-word"));
        testCases.add(new TestCase("iCe CrEaM", "Ice cream was the highlight of the summer party", 0.6, wLev, wCos, "Case insensitive: alternating case"));
        testCases.add(new TestCase("END", "We reached the end of the long winding road", 0.6, wLev, wCos, "Case insensitive: uppercase query"));
        testCases.add(new TestCase("Start", "Start preparing for the upcoming event this weekend", 0.6, wLev, wCos, "Case insensitive: mixed case query"));
        testCases.add(new TestCase("nothing", "NOTHING was left after the storm swept through town", 0.6, wLev, wCos, "Case insensitive: uppercase sentence"));
        testCases.add(new TestCase("Match", "The match between the teams was thrilling to watch", 0.6, wLev, wCos, "Case insensitive: mixed case query"));
        testCases.add(new TestCase("ABC", "Learning ABC is essential for young children in school", 0.6, wLev, wCos, "Case insensitive: uppercase query"));

        // Punctuation test cases
        testCases.add(new TestCase("Punctuation test cases"));
        testCases.add(new TestCase("ice", "The ice, melted slowly in the warm sunny afternoon", 0.6, wLev, wCos, "Punctuation: comma after word"));
        testCases.add(new TestCase("hello", "Hello! Welcome to the grand opening of our store", 0.6, wLev, wCos, "Punctuation: exclamation mark"));
        testCases.add(new TestCase("test case", "This test case; was tricky but fun to solve", 0.6, wLev, wCos, "Punctuation: semicolon in middle"));
        testCases.add(new TestCase("end", "We reached the end. After a long tiring journey", 0.6, wLev, wCos, "Punctuation: period before word"));
        testCases.add(new TestCase("start", "Start, now before the deadline passes this evening", 0.6, wLev, wCos, "Punctuation: comma after word"));
        testCases.add(new TestCase("word", "What is the word? I need to know soon", 0.6, wLev, wCos, "Punctuation: question mark"));
        testCases.add(new TestCase("ice cream", "Ice cream! Everyone shouted as the truck arrived", 0.6, wLev, wCos, "Punctuation: exclamation mark"));
        testCases.add(new TestCase("hello", "Hello. How are you doing on this fine day", 0.6, wLev, wCos, "Punctuation: period after word"));
        testCases.add(new TestCase("test", "This test, was harder than expected for many students", 0.6, wLev, wCos, "Punctuation: comma after word"));
        testCases.add(new TestCase("nothing", "There was nothing; left after the chaos subsided", 0.6, wLev, wCos, "Punctuation: semicolon after word"));

        // Edge cases
        testCases.add(new TestCase("Edge cases"));
        testCases.add(new TestCase("", "There was nothing interesting to see in the desert", 0.6, wLev, wCos, "Edge case: empty query"));
        testCases.add(new TestCase("test", "   ", 0.6, wLev, wCos, "Edge case: sentence with spaces"));
        testCases.add(new TestCase("   ", "This sentence has words but query is spaces", 0.6, wLev, wCos, "Edge case: query with spaces"));
        testCases.add(new TestCase("a", "A single letter can be tricky to match here", 0.6, wLev, wCos, "Edge case: single letter query"));
        testCases.add(new TestCase("abcde", "The abcde sequence appeared in the coded message", 0.6, wLev, wCos, "Edge case: five-letter query"));
        testCases.add(new TestCase("a b", "A B appeared in the text of the book", 0.6, wLev, wCos, "Edge case: two-word query"));
        testCases.add(new TestCase("a b c", "A B C was the code to unlock it", 0.6, wLev, wCos, "Edge case: three-word query"));
        testCases.add(new TestCase("a b c d", "A B C D opened the secret door", 0.6, wLev, wCos, "Edge case: four-word query"));
        testCases.add(new TestCase("a b c d e", "A B C D E was the final clue", 0.6, wLev, wCos, "Edge case: five-word query"));
        testCases.add(new TestCase("xyz", "Nothing matches here in this long winding sentence", 0.6, wLev, wCos, "Edge case: no match"));

        // Overlapping test cases
        testCases.add(new TestCase("Overlapping test cases"));
        testCases.add(new TestCase("ice", "Ice appeared twice ice in this cold winter tale", 0.6, wLev, wCos, "Overlapping: single word"));
        testCases.add(new TestCase("ice cube", "Ice cube ice cube chilled the drink very well", 0.6, wLev, wCos, "Overlapping: two words"));
        testCases.add(new TestCase("hello", "Hello hello hello echoed through the empty vast hall", 0.6, wLev, wCos, "Overlapping: repeated word"));
        testCases.add(new TestCase("test case", "Test case test case appeared in the document twice", 0.6, wLev, wCos, "Overlapping: two words"));
        testCases.add(new TestCase("a b c", "A b c a b c repeated in the sequence", 0.6, wLev, wCos, "Overlapping: three words"));
        testCases.add(new TestCase("word", "Word word word word echoed in the speech", 0.6, wLev, wCos, "Overlapping: repeated single word"));
        testCases.add(new TestCase("ice cream", "Ice cream ice cream was served to everyone", 0.6, wLev, wCos, "Overlapping: two words"));
        testCases.add(new TestCase("start now", "Start now start now shouted the coach loudly", 0.6, wLev, wCos, "Overlapping: two words"));
        testCases.add(new TestCase("end", "End end end marked the trilogy's conclusion", 0.6, wLev, wCos, "Overlapping: repeated word"));
        testCases.add(new TestCase("match", "Match match match happened in the tournament", 0.6, wLev, wCos, "Overlapping: repeated word"));

        // Numbers and special characters test cases
        testCases.add(new TestCase("Numbers and special characters test cases"));
        testCases.add(new TestCase("123", "The number 123 was written on the old dusty board", 0.6, wLev, wCos, "Numbers: numeric query"));
        testCases.add(new TestCase("test123", "Test123 appeared in the username list of the server", 0.6, wLev, wCos, "Alphanumeric: mixed query"));
        testCases.add(new TestCase("ice-cream", "Ice cream was served at the festival last weekend", 0.6, wLev, wCos, "Special char: hyphen in query"));
        testCases.add(new TestCase("hello@world", "Hello world was the message on the screen display", 0.6, wLev, wCos, "Special char: @ in query"));
        testCases.add(new TestCase("test", "Test#123 was the code for the locked door", 0.6, wLev, wCos, "Special char: # in sentence"));
        testCases.add(new TestCase("2023", "The year 2023 marked a new era for technology", 0.6, wLev, wCos, "Numbers: four-digit query"));
        testCases.add(new TestCase("user_1", "User_1 logged into the system late last night", 0.6, wLev, wCos, "Special char: underscore in query"));
        testCases.add(new TestCase("code", "Code$%^ was hidden in the encrypted message", 0.6, wLev, wCos, "Special char: symbols in sentence"));
        testCases.add(new TestCase("12345", "Number 12345 was the key to the secret vault", 0.6, wLev, wCos, "Numbers: five-digit query"));
        testCases.add(new TestCase("data", "Data!!! exploded on the screen during the presentation", 0.6, wLev, wCos, "Special char: multiple exclamation marks"));

        // No match test cases
        testCases.add(new TestCase("No match test cases"));
        testCases.add(new TestCase("xyz", "This sentence has no match for the query given", 0.6, wLev, wCos, "No match: unrelated query"));
        testCases.add(new TestCase("hello", "Nothing relates to this query in the sentence", 0.6, wLev, wCos, "No match: no related words"));
        testCases.add(new TestCase("ice cream", "There is no dessert in this long story", 0.6, wLev, wCos, "No match: unrelated multi-word"));
        testCases.add(new TestCase("test", "Nothing to see here just a random sentence", 0.9, wLev, wCos, "No match: high threshold"));
        testCases.add(new TestCase("abc", "Nothing matches this query in the given text", 0.6, wLev, wCos, "No match: short query"));
        testCases.add(new TestCase("query", "This sentence is unrelated to the search term", 0.6, wLev, wCos, "No match: single word"));
        testCases.add(new TestCase("not here", "This sentence has no relation to the query", 0.6, wLev, wCos, "No match: two words"));
        testCases.add(new TestCase("missing", "This sentence lacks any relevant terms for matching", 0.6, wLev, wCos, "No match: single word"));
        testCases.add(new TestCase("a b c", "No match exists in this sentence for query", 0.6, wLev, wCos, "No match: three words"));
        testCases.add(new TestCase("none", "This sentence contains no matches for the term", 0.6, wLev, wCos, "No match: single word"));

        // Threshold variation test cases
        testCases.add(new TestCase("Threshold variation test cases"));
        testCases.add(new TestCase("ice", "The ice melted slowly in the warm afternoon sun", 0.9, wLev, wCos, "High threshold: exact match"));
        testCases.add(new TestCase("ice", "The ice melted slowly in the warm afternoon sun", 0.1, wLev, wCos, "Low threshold: exact match"));
        testCases.add(new TestCase("ise", "The ice melted slowly in the warm afternoon sun", 0.9, wLev, wCos, "High threshold: typo"));
        testCases.add(new TestCase("ise", "The ice melted slowly in the warm afternoon sun", 0.4, wLev, wCos, "Moderate threshold: typo"));
        testCases.add(new TestCase("hello", "Hello everyone welcome to the grand summer festival", 0.5, wLev, wCos, "Moderate threshold: exact match"));
        testCases.add(new TestCase("world", "The world is vast and full of wonders", 0.8, wLev, wCos, "High threshold: exact match"));
        testCases.add(new TestCase("test", "This test case was tricky but fun", 0.2, wLev, wCos, "Low threshold: exact match"));
        testCases.add(new TestCase("ice cream", "Ice cream was served at the sunny beach", 0.7, wLev, wCos, "High threshold: exact match"));
        testCases.add(new TestCase("start", "Start the engine before the race begins", 0.3, wLev, wCos, "Low threshold: exact match"));
        testCases.add(new TestCase("end", "We reached the end after a long journey", 0.6, wLev, wCos, "Moderate threshold: exact match"));

        // Mixed test cases
        testCases.add(new TestCase("Mixed test cases"));
        testCases.add(new TestCase("ice 123", "The ice 123 block was carved for the exhibit", 0.6, wLev, wCos, "Mixed: number and word"));
        testCases.add(new TestCase("hello world", "Hello world appeared on the screen during boot", 0.6, wLev, wCos, "Mixed: two words"));
        testCases.add(new TestCase("test-case", "This test case was part of the evaluation", 0.6, wLev, wCos, "Mixed: hyphenated query"));
        testCases.add(new TestCase("12345", "The code 12345 unlocked the door to freedom", 0.6, wLev, wCos, "Mixed: five-digit number"));
        testCases.add(new TestCase("a b c d", "A B C D was the secret passcode", 0.6, wLev, wCos, "Mixed: four-letter query"));
        testCases.add(new TestCase("ice!", "Ice! Everyone shouted as the rink opened", 0.6, wLev, wCos, "Mixed: punctuation in query"));
        testCases.add(new TestCase("hello123", "Hello123 was the username for the new account", 0.6, wLev, wCos, "Mixed: alphanumeric query"));
        testCases.add(new TestCase("word space", "Word space appeared in the text editor", 0.6, wLev, wCos, "Mixed: two words"));
        testCases.add(new TestCase("test@now", "Test now ran smoothly despite the initial hiccups", 0.6, wLev, wCos, "Mixed: special char in query"));
        testCases.add(new TestCase("end123", "The end123 marked the conclusion of the saga", 0.6, wLev, wCos, "Mixed: word and number"));

        // Similarity scores test cases
        testCases.add(new TestCase("Similarity scores test cases"));
        testCases.add(new TestCase("software engineer", "software developer", 0.6, wLev, wCos, "Similar job titles"));
        testCases.add(new TestCase("cat", "bat", 0.6, wLev, wCos, "Single word, one letter difference"));
        testCases.add(new TestCase("programming is fun", "coding is enjoyable", 0.6, wLev, wCos, "Similar meaning, different words"));
        testCases.add(new TestCase("hello world", "hello there", 0.6, wLev, wCos, "Partial match"));
        testCases.add(new TestCase("", "", 0.6, wLev, wCos, "Empty strings"));
        testCases.add(new TestCase("data", "database", 0.6, wLev, wCos, "Substring match"));
        testCases.add(new TestCase("quick brown fox", "lazy dog sleeps", 0.6, wLev, wCos, "Unrelated sentences"));
        testCases.add(new TestCase("software engineer", "We need a software engineer to develop applications", 0.6, wLev, wCos, "Exact match in sentence"));
        testCases.add(new TestCase("data analyst", "Looking for a skilled data analyst with SQL experience", 0.6, wLev, wCos, "Exact match in sentence"));
        testCases.add(new TestCase("project manager", "The project manager oversees team tasks", 0.6, wLev, wCos, "Exact match in sentence"));
        testCases.add(new TestCase("web developer", "Hiring a software engineer for web applications", 0.6, wLev, wCos, "Partial match"));
        testCases.add(new TestCase("machine learning", "AI and data science roles available", 0.6, wLev, wCos, "No direct match"));
        testCases.add(new TestCase("", "This is a sample sentence with no query", 0.6, wLev, wCos, "Empty query"));
        testCases.add(new TestCase("quick fox", "The quick brown fox jumps over the lazy dog", 0.6, wLev, wCos, "Partial match in longer sentence"));

        // Print table header
        System.out.printf("%-5s %-30s %-60s %-50s %-10s %-8s %-8s %-70s%n",
                "No", "Query", "Sentence", "Description", "Threshold", "wLev", "wCos", "Matches");
        System.out.println(new String(new char[200]).replace('\0', '-'));

        // Run test cases and print results
        int testCounter = 1;
        for (TestCase test : testCases) {
            if (test.getQuery() == null && test.getDescription() != null) {
                System.out.println();
                System.out.println("Test Cases => " + test.getDescription());
                System.out.println();
                continue;
            }
            List<String> matches = FuzzySearch.findFuzzyMatches_v3(test.getQuery(), test.getSentence(), test.getThreshold());
            System.out.printf("%-5d %-30s %-60s %-50s %-10.2f %-8.2f %-8.2f %-70s%n",
                    testCounter++,
                    Strings.truncate(test.getQuery(), 30),
                    Strings.truncate(test.getSentence(), 60),
                    Strings.truncate(test.getDescription(), 50),
                    test.getThreshold(),
                    test.getwLev(),
                    test.getwCos(),
                    Strings.truncate(matches.toString(), 70));
        }

        System.out.println(new String(new char[200]).replace('\0', '-'));

        testCases.clear();
        testCases.add(new TestCase("Exact match short test cases"));
        testCases.add(new TestCase("ice", "ice", 0.6, wLev, wCos, "Exact match: one word"));
        testCases.add(new TestCase("dog", "dog runs", 0.6, wLev, wCos, "Exact match: two words"));
        testCases.add(new TestCase("cat", "cat sits", 0.6, wLev, wCos, "Exact match: two words"));
        testCases.add(new TestCase("sun", "bright sun shines", 0.6, wLev, wCos, "Exact match: three words"));
        testCases.add(new TestCase("moon", "moon glows", 0.6, wLev, wCos, "Exact match: two words"));
        testCases.add(new TestCase("star", "star twinkles", 0.6, wLev, wCos, "Exact match: two words"));
        testCases.add(new TestCase("sky", "blue sky", 0.6, wLev, wCos, "Exact match: two words"));
        testCases.add(new TestCase("rain", "rain falls", 0.6, wLev, wCos, "Exact match: two words"));
        testCases.add(new TestCase("snow", "snow melts", 0.6, wLev, wCos, "Exact match: two words"));
        testCases.add(new TestCase("wind", "wind blows", 0.6, wLev, wCos, "Exact match: two words"));

        // Typo match test cases
        testCases.add(new TestCase("Typo short test cases"));
        testCases.add(new TestCase("dgo", "dog", 0.4, wLev, wCos, "Typo: swapped letters"));
        testCases.add(new TestCase("cta", "cat", 0.4, wLev, wCos, "Typo: swapped letters"));
        testCases.add(new TestCase("snu", "sun", 0.4, wLev, wCos, "Typo: swapped letters"));
        testCases.add(new TestCase("mooon", "moon", 0.4, wLev, wCos, "Typo: repeated letter"));
        testCases.add(new TestCase("starr", "star", 0.4, wLev, wCos, "Typo: repeated letter"));
        testCases.add(new TestCase("sk", "sky", 0.4, wLev, wCos, "Typo: missing letter"));
        testCases.add(new TestCase("rian", "rain", 0.4, wLev, wCos, "Typo: wrong letter"));
        testCases.add(new TestCase("snw", "snow", 0.4, wLev, wCos, "Typo: missing letter"));
        testCases.add(new TestCase("windd", "wind", 0.4, wLev, wCos, "Typo: repeated letter"));
        testCases.add(new TestCase("sno", "snow", 0.4, wLev, wCos, "Typo: missing letter"));
        testCases.add(new TestCase("dgo", "dog barks loud", 0.4, wLev, wCos, "Typo: swapped letters, three words"));
        testCases.add(new TestCase("cta", "cat leaps up", 0.4, wLev, wCos, "Typo: swapped letters, three words"));
        testCases.add(new TestCase("snu", "sun glows warm", 0.4, wLev, wCos, "Typo: swapped letters, three words"));
        testCases.add(new TestCase("mooon", "moon shines bright", 0.4, wLev, wCos, "Typo: repeated letter, three words"));
        testCases.add(new TestCase("starr", "star sparkles above", 0.4, wLev, wCos, "Typo: repeated letter, three words"));
        testCases.add(new TestCase("sk", "sky turns blue", 0.4, wLev, wCos, "Typo: missing letter, three words"));
        testCases.add(new TestCase("rian", "rain drips down", 0.4, wLev, wCos, "Typo: wrong letter, three words"));
        testCases.add(new TestCase("snw", "snow falls soft", 0.4, wLev, wCos, "Typo: missing letter, three words"));
        testCases.add(new TestCase("windd", "wind gusts hard", 0.4, wLev, wCos, "Typo: repeated letter, three words"));
        testCases.add(new TestCase("sno", "snow piles up", 0.4, wLev, wCos, "Typo: missing letter, three words"));
        testCases.add(new TestCase("brdi", "bird flies high", 0.4, wLev, wCos, "Typo: swapped letters, three words"));
        testCases.add(new TestCase("tre", "tree grows tall", 0.4, wLev, wCos, "Typo: missing letter, three words"));
        testCases.add(new TestCase("lak", "lake shimmers clear", 0.4, wLev, wCos, "Typo: missing letter, three words"));
        testCases.add(new TestCase("hil", "hill rolls green", 0.4, wLev, wCos, "Typo: missing letter, three words"));
        testCases.add(new TestCase("paht", "path curves ahead", 0.4, wLev, wCos, "Typo: swapped letters, three words"));
        testCases.add(new TestCase("clod", "cloud floats by", 0.4, wLev, wCos, "Typo: wrong letter, three words"));
        testCases.add(new TestCase("fsh", "fish darts quick", 0.4, wLev, wCos, "Typo: missing letter, three words"));
        testCases.add(new TestCase("rokk", "rock rests firm", 0.4, wLev, wCos, "Typo: repeated letter, three words"));
        testCases.add(new TestCase("snd", "sand shifts under", 0.4, wLev, wCos, "Typo: missing letter, three words"));
        testCases.add(new TestCase("wav", "wave rolls in", 0.4, wLev, wCos, "Typo: missing letter, three words"));

        // Run test cases and print results
        for (TestCase test : testCases) {
            if (test.getQuery() == null && test.getDescription() != null) {
                System.out.println("\n=== " + test.getDescription() + " ===\n");
                continue;
            }
            List<String> matches = FuzzySearch.findFuzzyMatches_v3(test.getQuery(), test.getSentence(), test.getThreshold());
        }
    }


    public static class TestCase {
        private String query;
        private String sentence;
        private double threshold;
        private double wLev;
        private double wCos;
        private String description;

        public TestCase(String query, String sentence, double threshold, double wLev, double wCos, String description) {
            this.query = query;
            this.sentence = sentence;
            this.threshold = threshold;
            this.wLev = wLev;
            this.wCos = wCos;
            this.description = description;
        }

        public TestCase(String description) {
            this.description = description;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getSentence() {
            return sentence;
        }

        public void setSentence(String sentence) {
            this.sentence = sentence;
        }

        public double getThreshold() {
            return threshold;
        }

        public void setThreshold(double threshold) {
            this.threshold = threshold;
        }

        public double getwLev() {
            return wLev;
        }

        public void setwLev(double wLev) {
            this.wLev = wLev;
        }

        public double getwCos() {
            return wCos;
        }

        public void setwCos(double wCos) {
            this.wCos = wCos;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}