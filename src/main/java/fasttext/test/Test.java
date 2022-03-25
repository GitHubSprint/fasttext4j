package fasttext.test;

import fasttext.FastText;
import fasttext.FastTextPrediction;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sławomir Kostrzewa
 */
public class Test {
    
    private final static float FTHRESHOLD = 0f;
    
    private final static Logger logger = LoggerFactory.getLogger(Test.class);  
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        
        String filePath = "D:\\Vectra\\Bot\\mailbot\\vectra-email-body-model.bin";  
        
        FastText ftmodel = FastText.loadModel(filePath);
        
        
        
        
        File testFile = new File("D:\\Vectra\\Bot\\mailbot\\out\\vectra-email-body-model-2022-03-01.txt");
        
        List<String> testLines = Files.readAllLines(testFile.toPath(), StandardCharsets.UTF_8);
        
        int l = 0;
        int m = 0;
        for(String line: testLines)
        {
            int endLabel = line.indexOf(" ");
            String testLabel = line.substring(0, endLabel);
                        
            
            String testLine = line.substring(endLabel); 
          
            if(testLine == null || testLine.length() < 5)
                continue;
            
            List<FastTextPrediction> result = ftmodel.predictAll(Arrays.asList(testLine.split(" ")),FTHRESHOLD);
                                   
            //for(int i=0; i<3;i++)
            {            
                //logger.info(result.get(i).label() + " " + result.get(i).probability());
                
                int iScore = (int) (result.get(0).probability() * 100); 
                
                if(iScore > 50)
                {
                    System.out.println(testLabel + "\t" + testLine);
                    
                    System.out.println(result.get(0).label()  + " " + result.get(0).probability());
                    System.out.println("");
                    
                    m++;
                }
            }
                        
            l++;
            
            
        }
        
        System.out.println("Max found: " + m);
        System.out.println("Lines: " + testLines.size());
        
//        List<FastTextPrediction> result = ftmodel.predictAll(Arrays.asList("Poraz kolejny zgłaszam brak internetu. Proszę nie odpisywać żeby zresetować modem bo to nic nie daje. Proszę o przysłanie techników albo wymianę modemu. Na 30 dni w miesiącu wychodzi na to że 10 dni nie mam internetu.".split(" ")),FTHRESHOLD);
//        System.out.println(result.get(0).label() + " " + result.get(0).probability());      
        
        
        
        
//        for(int i=0; i<result.size();i++)
//        {            
//            logger.info(result.get(i).label() + " " + result.get(i).probability());
//        }
        
        
    }
    
    
    
    static String splitCamelCase(String s) {
        return s.replaceAll(
           String.format("%s|%s|%s",
              "(?<=[A-Z])(?=[A-Z][a-z])",
              "(?<=[^A-Z])(?=[A-Z])",
              "(?<=[A-Za-z])(?=[^A-Za-z])"
           ),
           " "
        );
}
}
