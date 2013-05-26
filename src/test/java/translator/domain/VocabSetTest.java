package translator.domain;


import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class VocabSetTest {
    @Test
    public void shouldConstruct() {
        List<String> lines = new ArrayList<String>();
        lines.add("#Comment");
        lines.add("fr=en");
        lines.add("le professeur=the teacher");
        lines.add("le garçon=the boy");
        VocabSet vocabSet = new VocabSet("description", lines);
        assertThat(vocabSet.getDescription(), equalTo("description"));
        assertThat(vocabSet.getFrom().getLanguage(), equalTo("fr"));
        assertThat(vocabSet.getTo().getLanguage(), equalTo("en"));
        assertThat(vocabSet.getVocab().size(), equalTo(2));
        assertThat(vocabSet.getVocab(), hasItem(Pair.of("le professeur","the teacher")));
        assertThat(vocabSet.getVocab(), hasItem(Pair.of("le garçon","the boy")));
    }
}
