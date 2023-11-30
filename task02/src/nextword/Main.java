package nextword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static Map<String, Map<String, Integer>> map = new HashMap<>();
    
    public static void main(String[] args) {
        
        FileFilter filter = new FileFilter() { 
        public boolean accept(File f) { 
            return f.getName().endsWith("txt"); 
            } 
        };
        
        File file = new File(args[0]);
        File[] files = file.listFiles(filter);

        for (File f : files) {

            try (FileReader fr = new FileReader(f)) {
                BufferedReader br = new BufferedReader(fr);
                
                List<String[]> list = new ArrayList<>();
                list = br.lines()
                    .map(word -> word.replaceAll("[^\\sa-zA-Z0-9]", "").toLowerCase().trim().split(" "))
                    .filter(line -> line.length > 0)
                    .collect(Collectors.toList());
                    
                for (String[] str : list) {

                    for (int i = 0; i < str.length - 1; i++) {
                        if (map.containsKey(str[i])) {
                            Map<String, Integer> innerMap = map.get(str[i]);
                            if (innerMap.containsKey(str[i+1])) {
                                int num = innerMap.get(str[i+1]);
                                innerMap.put(str[i+1], num++);
                            } else {
                                innerMap.put(str[i+1], 1);
                            }
                        map.put(str[i], innerMap);

                        } else {
                            Map<String, Integer> innerMap = new HashMap<>();
                            innerMap.put(str[i+1], 1);
                            map.put(str[i], innerMap);
                        }
                    }
                }
            
                for (String key : map.keySet()) {
                    System.out.println(key);
                    Collection<Integer> nums = map.get(key).values();
                    double sum = 0;
                    for (int i : nums) {
                        sum += Double.valueOf(i);
                    }

                    for (String k : map.get(key).keySet()) {
                        double idx = map.get(key).get(k); 
                        System.out.printf("\t%s, %.2f\n", k, idx/sum);
                    }
                }

            } catch (IOException e) {
                System.out.println("Error!");
            }
        }
    }
}

