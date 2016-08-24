# Programação de Alto Desempenho
Alunos: Matheus Ronsani de Figueiredo e Ricardo Bianchim Gomes    
Professora Andrea Schwertner Charão  
2016/2  
## Data Racing:
 Data racing é um termo utilizado para designar uma situação onde existem duas ou mais threads que tentam acessar o mesmo dado compartilhado.  
 As condições para existir data racing são as seguintes: 
<<<<<<< HEAD
- Existência de duas ou mais threads de um mesmo processo, que acessam uma mesma região de memória; 
- pelo menos um dos acessos é do tipo escrita; 
=======
- Existência de duas ou mais threads de um mesmo processo, que acessam uma mesma região de memória e; 
- pelo menos um dos acessos é do tipo escrita e; 
>>>>>>> origin/master
- as threads não usam nenhum tipo de bloqueio para controlar o acesso àquela região de memória;

Na ocorrência destas três condições, a ordem em que as operações são computadas pode influenciar no resultado final do programa e levar a resultados indesejados.

## 4. Referências
Sun Studio 12: Thread analyzer user guide. Disponível em https://docs.oracle.com/cd/E19205-01/820-0619/geojs/index.html, acesso em agosto/2016.  
