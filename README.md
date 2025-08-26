# CP4 - TDD & Testes Unitários (FIAP)

Este repositório contém a entrega do **Checkpoint 4** da disciplina de Qualidade de Software, cujo objetivo é aplicar **TDD (Test-Driven Development)** e **Testes Unitários com JUnit 5** em um projeto **Java + Maven**.

---

## 📝 Enunciado
- Desenvolver uma classe `Apdex` para cálculo e classificação do índice Apdex.  
- Criar testes unitários na classe `ApdexTest` com cobertura de todos os cenários de classificação.  
- Utilizar Maven e JUnit 5 (Jupiter).  
- O número total de amostras deve ser igual ao **RM do aluno**.  
- As classes devem conter o **nome completo e RM na primeira linha**.  
- Os testes devem rodar com sucesso no IntelliJ (JDK 17).

---

## 📂 Estrutura do Projeto
```
CP4-QA/
 ├── src/
 │   ├── main/
 │   │   └── java/
 │   │       └── br/com/fiap/Apdex.java        # Implementação principal
 │   └── test/
 │       └── java/
 │           └── br/com/fiap/ApdexTest.java    # Testes unitários (JUnit 5)
 ├── pom.xml                                   # Configuração Maven
 └── README.md
```

---

## ⚙️ Tecnologias Utilizadas
- **Java 17**
- **Maven 3+**
- **JUnit 5 (Jupiter API, Params, Engine)**
- **IntelliJ IDEA** (recomendado pela FIAP)

---

## ▶️ Como Executar

### 1. Clonar o repositório
```bash
git clone https://github.com/seuusuario/CP4-QA.git
cd CP4-QA
```

### 2. Rodar os testes pelo Maven
```bash
mvn clean test
```

### 3. Rodar os testes pelo IntelliJ
- Abrir o projeto no IntelliJ (importar como **Maven Project**).  
- Abrir `ApdexTest.java`.  
- Clicar no botão verde ▶️ ao lado da classe ou método de teste.  

---

## ✅ Resultados

Todos os **24 testes unitários** passaram com sucesso:

- Classificações: **Excelente, Bom, Razoável, Ruim, Inaceitável**
- Cálculo Apdex baseado no **RM554983**
- Testes de exceções para casos inválidos

Exemplo de saída no IntelliJ:

```
Tests passed: 24 of 24
Process finished with exit code 0
```

---

## 👩‍💻 Autora
**Raphaela — RM554983**  

