package org.apache.lucene.demo;


import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

public class SimpleMetrics {

    public static void main(String[] args) throws Exception {

        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("index")));
        Analyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser("contents", analyzer);
        Scanner scan = new Scanner(System.in);

        System.out.println("Please input term for simple metrics or press enter to quit: ");
        String input = scan.nextLine().trim();

        while(input.length() != 0) {
            
            Query query = parser.parse(input);
            Term termInstance = new Term("contents", query.toString("contents"));
            long termFreq = reader.totalTermFreq(termInstance);
            long docFreq = reader.docFreq(termInstance);
            System.out.println("Document Frequency: " + docFreq);
            System.out.println("Term Frequency: " + termFreq);

            System.out.println("Please input another term or press enter to quit: ");
            input = scan.nextLine().trim();
        }
    }
}