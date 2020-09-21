package ddori.spring.domain.common.code.enumMapper;

import com.google.common.collect.Multimap;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EnumMapper {
    private Map<String, List<EnumMapperValue>> factory = new HashMap<>();
    private Map<String, List<String>> viewGroup = new HashMap<>();
    private Map<String, List<EnumMapperGroupValue>> factoryGroup = new HashMap<>();

    public EnumMapper() {
        try{
            this.resourceLog();
        }catch (Exception ex) {};


        //System.err.println(" ############################## EnumMapper 시작");
        System.out.println(" ############################## EnumMapper 시작");
        Set<Class> classes = this.getClasses("ddori.spring.domain.common.code.lesson");
        this.setEnumMapper(classes, "lesson");

        classes = this.getClasses("ddori.spring.domain.common.code.display");
        this.setEnumMapper(classes, "display");
        /*
        classes = this.getClasses("kr.smartscore.gplace.domain.common.code.lesson");
        this.setEnumMapper(classes, "lesson");

        classes = this.getClasses("kr.smartscore.gplace.domain.common.code.member");
        this.setEnumMapper(classes, "member");

        classes = this.getClasses("kr.smartscore.gplace.domain.common.code.place");
        this.setEnumMapper(classes, "place");

        classes = this.getClasses("kr.smartscore.gplace.domain.common.code.ticket");
        this.setEnumMapper(classes, "ticket");
         */
        System.out.println(" EnumMapper 끝");
    }
    private List<EnumMapperValue> toEnumMapperValues(Class<? extends EnumMapperType> e){

        return Arrays
                .stream(e.getEnumConstants())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }

    public void putViewGroup(String key, List<String> list) {
        viewGroup.put(key, list);
    }

    public Map<String, List<EnumMapperValue>> getViewGroup(List<String> list) {
        Map<String, List<EnumMapperValue>> result = new HashMap<>();

        for (String view : list) {
            result.putAll(get(viewGroup.get(view)));
        }
        return result;
    }

    public Map<String, List<EnumMapperValue>> getViewGroup(String key) { return get(viewGroup.get(key)); }

    private List<EnumMapperGroupValue> toEnumMapperGroupValues(Class<? extends EnumMapperGroupType> e){

        return Arrays
                .stream(e.getEnumConstants())
                .map(EnumMapperGroupValue::new)
                .collect(Collectors.toList());
    }

    public void put(String key, Class<? extends EnumMapperType> e){
        factory.put(key, toEnumMapperValues(e));
    }

    public void putGroup(String key, Class<? extends EnumMapperGroupType> e){
        factoryGroup.put(key, toEnumMapperGroupValues(e));
    }

    public List<EnumMapperValue> get(String key) {
        return factory.get(key);
    }

    public Map<String, List<EnumMapperValue>> get(List<String> keys){
        if (keys == null || keys.size() == 0)
            return new LinkedHashMap<>();

        return keys.stream()
                .collect(Collectors.toMap(Function.identity(), key -> factory.get(key)));
    }

    public List<EnumMapperGroupValue> getGroup(String name) {
        return factoryGroup.get(name);
    }

    public List<EnumMapperValue> getGroup(String name, String key) {
        List<EnumMapperGroupValue> list =  factoryGroup.get(name);
        for (EnumMapperGroupValue group : list) {
            if (group.getCode().equals(key))
                return group.getGroup();
        }
        return null;
    }
    public Map<String, List<EnumMapperGroupValue>> getGroup(List<String> keys){
        if (keys == null || keys.size() == 0)
            return new LinkedHashMap<>();

        return keys.stream()
                .collect(Collectors.toMap(Function.identity(), key -> factoryGroup.get(key)));
    }
    public Map<String, List<EnumMapperValue>> getAll(){
        return factory;
    }


    public void setEnumMapper(Set<Class> classes, String viewName){
        if (classes == null) return;
        List<String> list = new ArrayList<String>();
        classes.forEach((e) -> {
            if (e.getSimpleName().contains("Group"))
                this.putGroup(e.getSimpleName(), e);
            else {
                this.put(e.getSimpleName(), e);
                list.add(e.getSimpleName());
            }
        });

        if (list.size() > 0)
            this.putViewGroup(viewName, list);
    }

    public void resourceLog() throws IOException {
        /*
        System.out.println(" ############################## resourceLog 시작");
        Enumeration<URL> resources = Thread.currentThread()
                .getContextClassLoader().getResources("kr/smartscore/gplace/domain/common/code/*.*");
        while (resources.hasMoreElements()) {
            URL url1 = resources.nextElement();
            System.out.printf("Resource URL: %s\n", url1);
            Scanner scanner = new Scanner((InputStream)url1.getContent());
            String s = scanner.useDelimiter("\\A").next();
            System.out.printf("Resource Content: %s\n", s);
            scanner.close();
        }
         */
    }

    public Set<Class> getClasses(String packageName){

        System.out.println(" ############################## getClasses 시작");

        Set<Class> classes = new HashSet<Class>();
        String packageNameSlash = "./" + packageName.replace(".", "/");
        //String packageNameSlash = packageName.replace(".", "/");
        /*
        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);
        System.out.println(" ############################## URL" + directoryURL);
        if(directoryURL == null){
            System.err.println("Could not retrive URL resource : " + packageNameSlash);
            return null;
        }

        String directoryString = directoryURL.getFile();
        if(directoryString == null){
            System.err.println("Could not find directory for URL resource : " + packageNameSlash);
            return null;
        }

        URL url = this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));
        System.out.println(" ############################## url" + url.getPath());

        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends EnumMapperType>> allClasses = reflections.getSubTypesOf(EnumMapperType.class);

        // File directory = new File(directoryString);
        File directory = new File(url.getFile());
        System.out.println(" ############################## directory " + directory.getAbsolutePath());

        if(directory.exists()){
            String[] files = directory.list();
            for(String fileName : files){
                if(fileName.endsWith(".class")){
                    fileName = fileName.substring(0, fileName.length() - 6);  // remove .class
                    try{
                        Class c = Class.forName(packageName + "." + fileName);
                        if(!Modifier.isAbstract(c.getModifiers())) // add a class which is not abstract
                            classes.add(c);
                    } catch (ClassNotFoundException e){
                        System.err.println(packageName + "." + fileName + " does not appear to be a valid class");
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.err.println(packageName + " does not appear to exist as a valid package on the file system.");
        }
        */

        Reflections reflections = new Reflections(packageName);
        /*
        Set<String> key = reflections.getStore().keySet();
        key.forEach(name -> {
            System.out.println(name);
        });

        Multimap<String, String> map = reflections.getStore().get(SubTypesScanner.class.getSimpleName());
        final Iterator<String> iter = map.keySet().iterator();
        while ( iter.hasNext() ){
            String s = iter.next();
            System.out.println(s + " / " + map.get(s) );
        }
        */
        Set<Class<? extends EnumMapperType>> allMapper = reflections.getSubTypesOf(EnumMapperType.class);
        allMapper.forEach(name -> {
            classes.add(name);
        });
        Set<Class<? extends EnumMapperGroupType>> allGroup = reflections.getSubTypesOf(EnumMapperGroupType.class);
        allGroup.forEach(name -> {
            classes.add(name);
        });

        System.out.println(" ############################## directory " + classes.size());
        return classes;
    }
}
