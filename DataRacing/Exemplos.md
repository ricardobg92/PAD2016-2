# Programação de Alto Desempenho
Aluno: Matheus Ronsani de Figueiredo e Ricardo Bianchim Gomes    
Professora Andrea Schwertner Charão  
2016/2  
## 1. Exemplos de códigos com data race:

|                                                         |                                                                                                                                                                                                                          | 
|---------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| Programa (link)                                         | Comentários                                                                                                                                                                                                              | 
| [Exemplo1.java](Exemplos/exemplo1.java)                 | Caso clássico de Data Race (DR). Duas threads compartilham um mesmo contador cnt. Uma variável Y recebe o valor de cnt, o qual já pode ter sido alterado pela outra thread, gerando um resultado incorreto.              | 
| [Exemplo2.cpp](Exemplos/exemplo2.cpp)                   | Ocorre DR em torno da variável "owner", o que pode fazer com que outras threads furem o bloqueio. Interessante.                                                                                                          | 
| [Exemplo2 corrigido](Exemplos/exemplo2_correto.cpp)     |                                                                                                                                                                                                                          | 
| [Exemplo3.cpp](Exemplos/exemplo3.cpp)                   | Data race quando várias threads incrementam a mesma variável mMoney dentro de seus laços de repetição.                                                                                                                   | 
| [Exemplo4.java](Exemplos/exemplo4.java)                 | Similar ao exemplo 1, porém, faz-se uso de diversas threads (um array de threads).                                                                                                                                       | 
| [Exemplo5.c](Exemplos/exemplo5.c)                       | A primeira operação dentro do parallel for está OK, não gera DR. Porém, existe DR na soma e incremento da variável "sum", logo abaixo.                                                                                   | 
| [Exemplo6.java](Exemplos/exemplo6.java)                 | Caso interessante de data race "benigno".  O data race ocorre ao se invocar s.hashCode, pois a classe String não implementa este método de forma sincronizada, nem mesmo calcula o hash no momento da criação da String. | 
| [Prime_omp.c](Exemplos/prime_omp.c)                     | DR ocorre em duas operações, no incremento do índice de acesso ao vetor, o que leva à possibilidade de DR no acesso à posição do vetor em si (linhas 47 e 48).                                                           | 
| [Prime_pthread.c](Exemplos/prime_pthread.c)             | DR ocorre em torno dos contadores "i" dos laços de repetição, que é comum entre as threads. Também ocorre em torno do índice do vetor, e no acesso ao vetor em si, como no ex. anterior                                  | 
| [Programa1.c](Exemplos/programa1.c)                     | Data race ocorre em torno da variável total, que é incrementado pelas threads sem bloqueio.                                                                                                                              | 
| [OMP Last private](Exemplos/fig7-7-first-lastprivate.c) | Haveria aqui data racing? Aparentemente não.  

Consulte 'referencias.md' para obter os links de origem dos programas.                                                                                                                                                                           | 
