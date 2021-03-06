package translator.domain;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VocabSet {
    private String description;
    private Locale from;
    private Locale to;
    private List<Vocab> vocab = new ArrayList<Vocab>();

    public VocabSet(File file) throws IOException {
        this(file.getName().substring(0, file.getName().lastIndexOf('.')), IOUtils.readLines(new BOMInputStream(new FileInputStream(file))));
    }

    public VocabSet(String description, List<String> lines) {
        this.description = description;
        for (String line: lines) {
            if (line.startsWith("#"))
                continue;
            String split[] = line.split("=");
            if (from == null) {
                from = Locale.forLanguageTag(split[0]);
                to = Locale.forLanguageTag(split[1]);
            } else {
                vocab.add(new Vocab(split));
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public Locale getFrom() {
        return from;
    }

    public Locale getTo() {
        return to;
    }

    public List<Vocab> getVocab() {
        return vocab;
    }
}
