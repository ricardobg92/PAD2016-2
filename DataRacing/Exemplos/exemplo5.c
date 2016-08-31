  //trecho obtido de http://stackoverflow.com/questions/26998183/how-do-i-deal-with-a-data-race-in-openmp
  
  int* input = (int*) malloc (sizeof(int)*snum);
  int sum = 0;
  int i;
  for(i=0;i<snum;i++){
      input[i] = i+1;
  }
  #pragma omp parallel for schedule(static)
  for(i=0;i<snum;i++)
  {
      int* tmpsum = input+i;
	  sum += *tmpsum;
  }