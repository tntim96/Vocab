package translator.domain;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class VocabSetTest {
    @Test
    public void shouldConstruct() {
        List<String> lines = new ArrayList<String>();
        lines.add("#Comment");
        lines.add("fr=en");
        lines.add("le professeur=the teacher");
        lines.add("le garçon=the boy");
        lines.add("l'âge=the age=m");
        VocabSet vocabSet = new VocabSet("description", lines);
        assertThat(vocabSet.getDescription(), equalTo("description"));
        assertThat(vocabSet.getFrom().getDisplayName(), equalTo("French"));
        assertThat(vocabSet.getFrom().getLanguage(), equalTo("fr"));
        assertThat(vocabSet.getTo().getDisplayName(), equalTo("English"));
        assertThat(vocabSet.getTo().getLanguage(), equalTo("en"));
        assertThat(vocabSet.getVocab().size(), equalTo(3));
    }
}
