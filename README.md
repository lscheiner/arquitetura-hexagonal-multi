# arquitetura-hexagonal-multi
Hexagonal multi

criacao dos modulos - usar gitbash
- parent 

mvn archetype:generate -DgroupId=br.com.scheiner -DartifactId=scheiner-parent

adicionar 

<packaging>pom</packaging>

no parent

sub-projetos

mvn archetype:generate -DgroupId=br.com.scheiner -DartifactId=api
mvn archetype:generate -DgroupId=br.com.scheiner -DartifactId=application