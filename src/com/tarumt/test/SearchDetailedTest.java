package com.tarumt.test;

import com.tarumt.utility.search.FuzzySearch;

import java.util.ArrayList;
import java.util.List;

public class SearchDetailedTest {
    public static void main(String[] args) {
        List<SearchTest.TestCase> testCases = new ArrayList<>();

        double wLev = 0.5;
        double wCos = 0.5;

        testCases.add(new SearchTest.TestCase("clod", "cloud", 0.4, wLev, wCos, "short"));
        testCases.add(new SearchTest.TestCase("sk", "sky", 0.4, wLev, wCos, "short"));
        testCases.add(new SearchTest.TestCase("sun", "snu", 0.4, wLev, wCos, "short"));

        testCases.add(new SearchTest.TestCase("clod computting", "The rise of cloud computing has transformed how businesses operate in the digital era", 0.4, wLev, wCos, "Typo in query: missing 'u', repeated 't'"));
        testCases.add(new SearchTest.TestCase("mashine learnng", "Machine learning algorithms helped predict trends in the massive dataset we analyzed", 0.4, wLev, wCos, "Typo in query: 'c' to 's', missing 'i', missing 'i'"));
        testCases.add(new SearchTest.TestCase("artifical intellignce", "The artificial intelligence conference showcased innovative applications across industries", 0.4, wLev, wCos, "Typo in query: 'i' to 'a', 'e' to 'i', extra 'n'"));
        testCases.add(new SearchTest.TestCase("depp div analisys", "We conducted a deep dive analysis to uncover insights from the complex financial reports", 0.4, wLev, wCos, "Typo in query: repeated 'p', missing 'e', 'y' to 'i'"));
        testCases.add(new SearchTest.TestCase("blokchian techology", "Blockchain technology is revolutionizing secure transactions in the fintech sector", 0.4, wLev, wCos, "Typo in query: 'c' to 'k', missing 'n', 'n' to 'l'"));
        testCases.add(new SearchTest.TestCase("quantm compuing", "The seminar on quantum computing explored advancements in modern computational methods", 0.4, wLev, wCos, "Typo in query: missing 'u', 't' to 'u'"));
        testCases.add(new SearchTest.TestCase("datta scienc trnds", "Data science trends for 2024 were discussed at the annual tech symposium", 0.4, wLev, wCos, "Typo in query: repeated 't', missing 'e', missing 'e'"));
        testCases.add(new SearchTest.TestCase("nural netwrok", "Neural networks are essential for solving complex problems in AI research", 0.4, wLev, wCos, "Typo in query: missing 'e', swapped 'o' and 'r'"));
        testCases.add(new SearchTest.TestCase("cybre secrity threts", "Cyber security threats are increasing, requiring robust defense strategies for companies", 0.4, wLev, wCos, "Typo in query: 'e' to 'r', missing 'u', missing 'a'"));
        testCases.add(new SearchTest.TestCase("bgi daat analitics", "The team used big data analytics to improve decision-making processes", 0.4, wLev, wCos, "Typo in query: 'i' to 'g', repeated 'a', 'y' to 'i'"));

        testCases.add(new SearchTest.TestCase("machien learnign algoirthms", "algorithms learning machine improved", 0.4, wLev, wCos, "Typo in query: 'i' to 'ie', 'i' to 'ig', 'r' to 'ri'"));
        testCases.add(new SearchTest.TestCase("cluod computnig soltuions", "solutions computing cloud emerged", 0.4, wLev, wCos, "Typo in query: 'o' to 'uo', 'i' to 'ni', 'i' to 'io'"));
        testCases.add(new SearchTest.TestCase("artifical intellgence ap", "showcased intelligence applications artificial", 0.4, wLev, wCos, "Typo in query: 'i' to 'ia', 'i' to 'ig', 'p' to 'ap'"));
        testCases.add(new SearchTest.TestCase("blockchian transatcion securtiy", "security transaction blockchain expanded", 0.4, wLev, wCos, "Typo in query: 'a' to 'ai', 'c' to 'ct', 'i' to 'ti'"));
        testCases.add(new SearchTest.TestCase("qauntum compuet advancemets", "advancements computer quantum discussed", 0.4, wLev, wCos, "Typo in query: 'u' to 'au', 'u' to 'ue', 'n' to 'ne'"));
        testCases.add(new SearchTest.TestCase("data scinece trennds", "trends science data reviewed", 0.4, wLev, wCos, "Typo in query: 'a' to 'at', 'e' to 'ec', 'd' to 'dd'"));
        testCases.add(new SearchTest.TestCase("neuarl netowrk modles", "solved models network neural", 0.4, wLev, wCos, "Typo in query: 'r' to 'ra', 'o' to 'ow', 'e' to 'le'"));
        testCases.add(new SearchTest.TestCase("cyebr secuirity polices", "policies security cyber updated", 0.4, wLev, wCos, "Typo in query: 'y' to 'ye', 'u' to 'ui', 'i' to 'ie'"));
        testCases.add(new SearchTest.TestCase("big datta analyitcs", "enhanced analytics data big", 0.4, wLev, wCos, "Typo in query: 'g' to 'ig', 't' to 'tt', 'i' to 'ti'"));
        testCases.add(new SearchTest.TestCase("deep divee anlaysis", "analysis dive deep revealed", 0.4, wLev, wCos, "Typo in query: 'p' to 'ep', 'e' to 'ee', 'y' to 'ay'"));

        for (SearchTest.TestCase test : testCases) {
            if (test.getQuery() == null && test.getDescription() != null) {
                System.out.println("\n=== " + test.getDescription() + " ===\n");
                continue;
            }
            List<String> matches = FuzzySearch.findFuzzyMatches_v2(test.getQuery(), test.getSentence(), test.getThreshold());
        }

    }
}
