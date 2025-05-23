package br.com.scheiner.domain.architecture;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

class UsuarioRepositoryAccessTest {

    @Test
    void somente_UsuarioServiceImpl_pode_usar_UsuarioRepository() {
        JavaClasses classes = new ClassFileImporter().importPackages("br.com.scheiner.domain");

        ArchRuleDefinition.noClasses()
        .that().doNotHaveFullyQualifiedName("br.com.scheiner.domain.service.impl.UsuarioServiceImpl")
        .should().dependOnClassesThat()
        .haveFullyQualifiedName("br.com.scheiner.domain.service.UsuarioRepository")
        .because("Nenhuma outra classe, exceto UsuarioServiceImpl que implementa as regras de negócio, deve usar diretamente UsuarioRepository")
        .check(classes);
    }
}
