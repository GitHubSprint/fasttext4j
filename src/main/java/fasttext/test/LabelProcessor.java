
package fasttext.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Sławomir Kostrzewa
 */
public class LabelProcessor {
    
    
    public static void main(String[] args) throws IOException {
        File testFile = new File("D:\\Vectra\\Bot\\mailbot\\out\\vectra-email-body-model.txt");
        
        List<String> testLines = Files.readAllLines(testFile.toPath(), StandardCharsets.UTF_8);
        
        List<String> labels = new ArrayList<>(); 
        
        for(String line: testLines)
        {
            int endLabel = line.indexOf(" ");
            String testLabel = line.substring(0, endLabel);
            labels.add(testLabel);
        }
        
        HashSet<String> uniqueLabels = new HashSet<>(labels);
        
        for(String l: uniqueLabels)
        {
            System.out.println(l);
        }
        
    }
    
}
