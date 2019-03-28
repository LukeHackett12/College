// ONLY MODIFY CODE BELOW THE NEXT OCCURENCE OF THE FOLLOWING LINE !
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#include "types.h"
#include "defs.h"
#include <stdio.h>
#include <malloc.h>
#include <limits.h>
#include "proc.h"

#define NCPU 1

struct cpu cpus[NCPU];
int ncpu;

void printstate(enum procstate pstate) { // DO NOT MODIFY
    switch (pstate) {
        case UNUSED   :
            printf("UNUSED");
            break;
        case EMBRYO   :
            printf("EMBRYO");
            break;
        case SLEEPING :
            printf("SLEEPING");
            break;
        case RUNNABLE :
            printf("RUNNABLE");
            break;
        case RUNNING  :
            printf("RUNNING");
            break;
        case ZOMBIE   :
            printf("ZOMBIE");
            break;
        default       :
            printf("????????");
    }
}

// Per-CPU process scheduler.
// Each CPU calls scheduler() after setting itself up.
// Scheduler never returns.  It loops, doing:
//  - choose a process to run
//  - swtch to start running that process
//  - eventually that process transfers control
//      via swtch back to the scheduler.

// local to scheduler in xv6, but here we need them to persist outside,
// because while xv6 runs scheduler as a "coroutine" via swtch,
// here swtch is just a regular procedure call.
struct proc *p;
struct cpu *c = cpus;

// +++++++ ONLY MODIFY BELOW THIS LINE ++++++++++++++++++++++++++++++
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

int timesRun[NPROC];
int initialised;

void initTimes() {
    for (int i = 0; i < NPROC; i++) {
        timesRun[i] = 0;
    }
    initialised = 1;
}


void scheduler(void) {
    int runnableFound; // DO NOT MODIFY/DELETE

    if (!initialised) initTimes();

    c->proc = 0;

    runnableFound = 1; // force one pass over ptable

    while (runnableFound) { // DO NOT MODIFY
        // Enable interrupts on this processor.
        // sti();
        runnableFound = 0; // DO NOT MODIFY
        // Loop over process table looking for process to run.
        // acquire(&ptable.lock);
        int lowest = INT_MAX;
        int lowestIndex = 0;
        for(int i = 0;  i < NPROC; i++) {
            if((timesRun[i]  < lowest) && (ptable.proc[i].state == RUNNABLE)){
                runnableFound = 1;
                lowest = timesRun[i];
                lowestIndex = i;
            }
        }

        if(!runnableFound)
            continue;

        timesRun[lowestIndex] = timesRun[lowestIndex] + 1;

        p = &ptable.proc[lowestIndex];
        c->proc = p;
        p->state = RUNNING;

        swtch(p);
        c->proc = 0;
    }
    printf("No RUNNABLE process!\n");
}
