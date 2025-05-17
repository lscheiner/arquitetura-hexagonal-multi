
# Arquitetura Hexagonal Multi

## Criação dos módulos (usando Git Bash)

### 1. Criar o módulo *parent*

Execute o comando abaixo para gerar o módulo *parent*:

    mvn archetype:generate -DgroupId=br.com.scheiner -DartifactId=scheiner-parent

### 2. Configurar o `pom.xml` do módulo *parent*

No arquivo `pom.xml` do módulo *parent*, adicione a seguinte configuração para definir o empacotamento como POM:

    <packaging>pom</packaging>

### 3. Criar os subprojetos

Entre no diretório `scheiner-parent` e crie os subprojetos executando os comandos:

    mvn archetype:generate -DgroupId=br.com.scheiner -DartifactId=api
    mvn archetype:generate -DgroupId=br.com.scheiner -DartifactId=application

---
