package com.example.springimport.configuration;

import com.example.springimport.bean.Student;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class DemoDeferredImportSelector  implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("DemoDeferredImportSelector-----------------------");
        return new String[]{Student.class.getName()};
    }

    @Override
    public Class<? extends Group> getImportGroup() {
        return DemoGroup.class;
    }

    static class DemoGroup implements Group{
        private final List<Entry> imports = new ArrayList<>();
        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            System.out.println(metadata+ " ------------");
//            执行importSelector的selectImport方法；
            for (String str :  selector.selectImports(metadata)) {
                System.out.println(str + " ------------");
                imports.add(new Entry(metadata, str));
            }
        }

        @Override
        public Iterable<Entry> selectImports() {
            System.out.println("demogroup select import ");
            return imports;
        }
    }

}
