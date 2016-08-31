   //código obtido de https://docs.oracle.com/cd/E19205-01/820-0619/gdvwv/index.html
   
   #include <stdio.h>
   #include <math.h>
   #include <omp.h>

   #define THREADS 4
   #define N 3000

   int primes[N];
   int pflag[N];

  int is_prime(int v)
  {
      int i;
      int bound = floor(sqrt ((double)v)) + 1;
      
      for (i = 2; i < bound; i++) {
          /* No need to check against known composites */ 
          if (!pflag[i]) 
              continue;
          if (v % i == 0) { 
              pflag[v] = 0;
              return 0;
          }
      }
      return (v > 1); 
  }

  int main(int argn, char **argv)
  {
      int i;
      int total = 0;

  #ifdef _OPENMP
      omp_set_num_threads(THREADS);
      omp_set_dynamic(0);
  #endif

      for (i = 0; i < N; i++) {
          pflag[i] = 1; 
      }
      
      #pragma omp parallel for
      for (i = 2; i < N; i++) {
          if ( is_prime(i) ) {
              primes[total] = i;
              total++;
          }
      }
      printf("Number of prime numbers between 2 and %d: %d\n",
             N, total);
      for (i = 0; i < total; i++) {
          printf("%d\n", primes[i]);
      }

  return 0;
  } 