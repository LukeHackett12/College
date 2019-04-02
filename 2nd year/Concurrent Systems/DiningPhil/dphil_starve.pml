#define NUM 5 /* Number of philosophers, and forks! */

/* We want to record which philosopher is holding which fork */
/*  fork[NUM][NUM] where fork[p][f] is true if 'p' holds 'f' */

bool Fork[NUM*NUM]; /* 2-d arrays not supported, so ... */
bool Thinking[NUM]; 

#define THINKING(i) Thinking[i]
#define FORK(p,f) Fork[NUM*p+f]
#define leftFork(p) (p%NUM)
#define rightFork(p) ((p+1)%NUM)
#define myForkOnly(p,f) !(    FORK(((p+1)%NUM),f) \
                           || FORK(((p+2)%NUM),f) \
                           || FORK(((p+3)%NUM),f) \
                           || FORK(((p+4)%NUM),f) )


active [NUM] proctype phil()
{ int p, lfork, rfork ;
  p = _pid;
  lfork = leftFork(p);
  rfork = rightFork(p);

  think: printf("P%d thinks..\n",_pid);
  Thinking[p] = true;

  atomic {
    if 
    ::(myForkOnly(p, lfork)) -> firstfork:  FORK(p,lfork) = true;
    fi;
    if
    ::(myForkOnly(p, rfork)) -> secondfork: FORK(p,rfork) = true;
    fi;
    assert(myForkOnly(p,lfork));
    assert(myForkOnly(p,rfork));
    Thinking[p] = false;
  }

  progress_eat: printf("P%d eats!\n",_pid);
  dropfork1: FORK(p,lfork) = false;
  dropfork2: FORK(p,rfork) = false;
  goto think

  ltl starving { [] ( (THINKING(_pid)) -> <> (!THINKING(_pid)) ) }
}
/*
01000
00100
00010
00000
10001
*/

