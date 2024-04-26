package se.lexicon.exceptions.workshop;

import java.io.IOException;
import java.util.List;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class Main {

	public static void main(String[] args) throws IOException {

        List<String> maleFirstNames = CSVReader_Writer.getMaleFirstNames();
        List<String> femaleFirstNames = CSVReader_Writer.getFemaleFirstNames();

        CSVReader_Writer.getLastNames();
        List<String> lastNames;
        NameService nameService;
        try {
            lastNames = CSVReader_Writer.getLastNames();


            nameService = new NameService(maleFirstNames, femaleFirstNames, lastNames);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Person test = nameService.getNewRandomPerson();
        System.out.println(test);

    }

}
