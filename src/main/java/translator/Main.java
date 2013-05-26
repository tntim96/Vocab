package translator;

import org.apache.commons.lang3.tuple.Pair;
import translator.domain.VocabSet;

import java.io.File;
import java.util.Locale;

//àçéèêîù œ
public class Main {
    private Downloader downloader = new Downloader();

    public static void main(String[] args) throws Exception {
        new Main().go();
    }

    private void go() throws Exception {
        File srcDir = new File("src/main/resources");
//        File targetDir = new File("out/mp3");
        File targetDir = new File("e:/French");
        for (File file : srcDir.listFiles()) {
            VocabSet vocabSet = new VocabSet(file);
            vocabSet.getDescription();
            for (int i = 0; i < vocabSet.getVocab().size(); i++) {
                Pair<String, String> pair = vocabSet.getVocab().get(i);
                File mp3Dir = new File(targetDir, vocabSet.getDescription());
                mp3Dir.mkdirs();
                createMp3File(vocabSet.getFrom(), i + 1, mp3Dir, "a " + pair.getLeft());
                createMp3File(vocabSet.getTo(), i + 1, mp3Dir, "b " + pair.getRight());
            }
        }
    }

    private void createMp3File(Locale locale, int i, File mp3Dir, String text) {
        String fileName = "%02d - %s.mp3";
        downloader.downloadMp3(text, locale, new File(mp3Dir, String.format(fileName, i, text)));
    }
}
