package com.example.springimport.configuration;

import com.example.springimport.bean.Student;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * 方式一： 直接导入普通类
 */
//@Import(Student.class)
//    @Import(DemoImportSelector.class)

//@Import(DemoImportBeanDefinitionRegistry.class)
@Import(DemoDeferredImportSelector.class)
@Component

public class DemoImport {
}
