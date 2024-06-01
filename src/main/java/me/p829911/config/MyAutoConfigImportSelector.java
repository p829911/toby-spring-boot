package me.p829911.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

  private final ClassLoader classLoader;

  public MyAutoConfigImportSelector(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    List<String> autoConfigs = new ArrayList<>();
    ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);
    //    for (String candidate : ImportCandidates.load(me.p829911.config.MyAutoConfiguration.imports.class, classLoader)) {
    //      autoConfigs.add(candidate);
    //    }
    return autoConfigs.toArray(String[]::new);
    //    return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
    //    return autoConfigs.toArray(new String[0]);
    //    Iterable<String> candidates = ImportCandidates.load(me.p829911.config.MyAutoConfiguration.imports.class,
    // classLoader);
    //    return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
  }
}
