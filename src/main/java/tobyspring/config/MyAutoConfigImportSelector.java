package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        List<String> autoConfigs = new ArrayList<>();

        ImportCandidates candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        for (String candidate : candidates) {
            autoConfigs.add(candidate);
        }

        return autoConfigs.toArray(new String[0]);
        // java8
        // ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

//        return autoConfigs.stream().toArray(String[]::new);
//        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);

        // stream
        // return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

//        return new String[] {
//                "tobyspring.config.autoconfig.DispatcherServletConfig",
//                "tobyspring.config.autoconfig.TomcatWebServerConfig"
//        };
    }
}
