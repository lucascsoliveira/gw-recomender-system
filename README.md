# Guesswork: Sistema de recomendação de notícias

---

Trabalho apresentado à disciplina de **Comércio Eletrônico** como critério de avaliação parcial no período de 2014/2.

###Contexto
Um site de notícias deseja que toda vez que um novo artigo seja escrito sejam recomendados artigos antigos que possuem conteúdo relacionado.
###Objetivo
Desenvolver um sistema de recomendação baseado em conteúdo para sugestão de notícias.

---------

### Critérios
- **K-Nearest Neighbors:** O sistema deverá recomendar as **k** notícias mais próximas ao novo artigo.
- **Classe:** Usando os *K-Nearest Neighbors*, o sistema deverá predizer a classe do artigo e recomendar **n** reportagens aleatórias pertencentes à esta classe.

---------

### Simplificações do trabalho
1. Todos os dados já estão “limpos”;
2. Não será necessário pré-processamento;
3. Existem somente duas classes;
4. Como a dimensionalidade é pequena, tudo pode ser feito online.

---

### Descrição geral do sistema
#### Entrada:
- Cada artigo está armazenado em um arquivo **.txt**;
- **Arquivo train-XX.txt:** contém uma lista de arquivos .txt com notícias e, para cada arquivo, a classe associada. Estes arquivos são usados como base de conhecimento;
- **Arquivo test-XX.txt:** contém uma lista de arquivos .txt com notícias e, para cada arquivo, a classe associada. Estes arquivos representam notícias novas e as suas classes só devem ser usadas para verificação;
- **Parâmetros:** K e N.

#### Saída:
- Para cada notícia do arquivo *test-XX.txt*, exibir na tela os nomes de 4 arquivos vindos do arquivo *train-XX.txt*, cada um com uma notícia recomendada.

#### Conjunto de dados:
- **1(um) trivial:** 2 documentos brinquedo de cada classe [para debug];
- **1(um) interessante:** 10 documentos de cada classe, cada um com cerca de 200 palavras.