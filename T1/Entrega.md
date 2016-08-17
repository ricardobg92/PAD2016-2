# Programação de Alto Desempenho
Aluno: Ricardo Bianchim Gomes    
Professora Andrea Schwertner Charão  
2016/2  
## 1. Programa escolhido:
 O programa escolhido para o desenvolvimento do trabalho é o resolvedor do problema das N-rainhas (n-queens puzzle) [nqueens da suíte BOTS](https://pm.bsc.es/projects/bots/downloads/5).    
 O programa identifica, em um tabuleiro de xadrez NxN as posições possíveis de se colocar rainhas, de forma que nenhuma rainha ataque qualquer uma das demais.  
 ```C
 void nqueens (int n, int j, char *a, int *solutions)
{
	int i,res;

	if (n == j) {
		/* good solution, count it */
		*solutions = 1;
		return;
	}
	*solutions = 0;
     	/* try each possible position for queen <j> */
	for (i = 0; i < n; i++) {
		a[j] = (char) i;
		if (ok(j + 1, a)) {
		       	nqueens(n, j + 1, a,&res);
			*solutions += res;
		}
	}
}
void find_queens (int size)
{
	char *a;

	total_count=0;
	a = alloca(size * sizeof(char));
	bots_message("Computing N-Queens algorithm (n=%d) ", size);
	nqueens(size, 0, a, &total_count);
        bots_message(" completed!\n");
}
```
 O algoritmo inicialmente aloca a matriz de posições "a". Em seguida, uma rainha é colocada em uma posição inicial da matriz. A partir daí, é realizada a chamada 
 recursiva para inserir a próxima rainha. A recursão ocorre para inserção de cada nova rainha. Em cada nova inserção, todas as posições possíveis são verificadas, até que seja encontrado uma coordenada que satisfaça os 
 critérios do problema. O algoritmo termina quando todas as rainhas foram inseridas, satisfazendo a regra do problema.

## 2. Tempos de execução
O programa escolhido foi executado com um problema de tamanho N=14, de forma sequencial e de forma paralela, com 2 threads.  
A forma paralela do programa conta com seis implementações diferentes:
- A primeira utiliza tasks, sem cut-off; 
- A segunda, tasks com cut-off pragma-if; 
- A terceira, tied tasks com cut-off pragma-if;
- A quarta, task com cut-off manual;
- A quita, tied task com cut-off manual;
- A sexta, tied task se cut-off.

Foram aferidas 5 execuções da versão serial do programa e 5 para cada uma das versões paralelas disponíveis. Os tempos obtidos são exibidos pela tabela abaixo.

| Cenário | Sequencial  | OMP, 2T, Tasks, no cut-off | OMP, 2T, Task, pragma-if (3) (cut-off) | OMP, 2T, Tied Task, pragma-if cut-off | OMP, 2T, Task, manual cut-off | OMP, Tied task, 2T, manual cut-off | OMP, 2T, Tied task, no cut-off |
|---------|-------------|----------------------------|----------------------------------------|---------------------------------------|-------------------------------|------------------------------------|--------------------------------|
| T1      | 43,086043s  | 56,826880s                 | 28,897547s                             | 28,295714s                            | 21,745830s                    | 21,693391s                         | 56,039581s                     |
| T2      | 43,081420s  | 56,928729s                 | 28,991055s                             | 28,770920s                            | 21,721496s                    | 21,651938s                         | 56,114453s                     |
| T3      | 43,092464s  | 56,724682s                 | 28,872625s                             | 28,361784s                            | 21,758576s                    | 21,686000s                         | 56,674636s                     |
| T4      | 43,080113s  | 57,156046s                 | 28,963390s                             | 29,324633s                            | 21,779140s                    | 21,645910s                         | 56,471143s                     |
| T5      | 44,470533s  | 56,903965s                 | 28,751617s                             | 28,252780s                            | 21,713869s                    | 21,690747s                         | 56,531366s                     |
| MÉDIA   | 43,3621146s | 56,9080604s                | 28,8952468s                            | 28,6011662s                           | 21,7437822s                   | 21,6735972s                        | 56,3662358s                    |
| Speedup | 1           | 0,761967                   | 1,5006                                 | 1,516096                              | 1,99423                       | 2,000688                           | 0,769293                       |

Como pode ser observado, existem algumas implementações paralelas do programa descolhido que apresentam desempenho inferior à versão sequencial. O melhor speedup é obtido pela versão paralela que utiliza tied task, com cut-off manual. Speedup, segundo Foster (1995), é uma medida de ganho de desempenho de um algoritmo paralelo, dada pela razão entre o tempo de execução sequencial e o tempo de execução paralela de um código. Ou seja: Speedup = Tseq/Tparalelo.

## 3. Análise
A análise a seguir refere-se apenas para o caso "OMP, Tied Task, 2T, manual cut-off".  

Versão paralela do mesmo algoritmo:
```C
	#ifndef FORCE_TIED_TASKS
	void nqueens(int n, int j, char *a, int *solutions, int depth)
	#else
	void nqueens(int n, int j, char *a, int depth)
	#endif
	{
	#ifndef FORCE_TIED_TASKS
		int *csols;
	#endif
		int i;
		if (n == j) {
			/* good solution, count it */
	#ifndef FORCE_TIED_TASKS
			*solutions = 1;
	#else
			mycount++;
	#endif
			return;
		}
	#ifndef FORCE_TIED_TASKS
		*solutions = 0;
		csols = alloca(n*sizeof(int));
		memset(csols,0,n*sizeof(int));
	#endif
			/* try each possible position for queen <j> */
		for (i = 0; i < n; i++) {
			if ( depth < bots_cutoff_value ) {
				#pragma omp task untied
				{
					/* allocate a temporary array and copy <a> into it */
					char * b = alloca(n * sizeof(char));
					memcpy(b, a, j * sizeof(char));
					b[j] = (char) i;
					if (ok(j + 1, b))
	#ifndef FORCE_TIED_TASKS
							nqueens(n, j + 1, b,&csols[i],depth+1);
	#else
							nqueens(n, j + 1, b,depth+1);
	#endif
				}
			} else {
				a[j] = (char) i;
				if (ok(j + 1, a))
	#ifndef FORCE_TIED_TASKS
						nqueens_ser(n, j + 1, a,&csols[i]);
	#else
						nqueens_ser(n, j + 1, a);
	#endif
			}
		}
		#pragma omp taskwait
	#ifndef FORCE_TIED_TASKS
		for ( i = 0; i < n; i++) *solutions += csols[i];
	#endif
	}
```
A versão paralela do programa cria tarefas de execução através da chamada #pragma omp task untied. Cada tarefa realiza uma parte das verificações de posicionamento
das rainhas. Ainda, quando o cut-off é especificado na compilação, cada tarefa executa um nivel máximo de profundidade de recursão. Esta profundidade é controlada pelo valor depth. 
Através da divisão de trabalho e controle de profundidade de recursão, obteve-se ganho de desempenho apresentado na tabela.  
Vale ressaltar que, quando o parâmetro de cut-off não é especificado, a versão paralela possui desempenho inferior ao da versão serial. Isto se deve às operações de alocação
de memória extras presentes no código paralelo.  

## 4. Referências
Foster, I. (1995) “Designing and Building Parallel Programs”