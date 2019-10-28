#include <iostream>			// cout
#include <conio.h>			// _getch
#include <chrono>			// _getch
#include <time.h>			// _getch

using namespace std;		// cout
using namespace chrono;

int NUM_RUNS = 100000;
int REGISTER_WINDOWS = 6;

int calls = 0;
int depth = 0;
int maxDepth = 0;

int overflows = 0;
int underflows = 0;

int windowsInUse = 0;

void overflowCheck() {
	calls++;
	depth++;
	if (depth > maxDepth) {
		maxDepth = depth;
	}
	if (windowsInUse == REGISTER_WINDOWS) {
		overflows++;
	}
	else {
		windowsInUse++;
	}
}

void underflowCheck() {
	depth--;
	if (windowsInUse == 2) {
		underflows++;
	}
	else {
		windowsInUse--;
	}
}

int ackermannWithChecks(int x, int y) {
	overflowCheck();
	int ret;
	if (x == 0) {
		ret = y + 1;
	}
	else if (y == 0) {
		ret = ackermannWithChecks(x - 1, 1);
	}
	else {
		ret = ackermannWithChecks(x - 1, ackermannWithChecks(x, y - 1));
	}
	underflowCheck();
	return ret;
}

int ackermann(int x, int y) {
	if (x == 0) {
		return y + 1;
	}
	else if (y == 0) {
		return ackermann(x - 1, 1);
	}
	else {
		return ackermann(x - 1, ackermann(x, y - 1));
	}
}


int main(int argc, char* argv[]) {
	
	ackermannWithChecks(3, 6);
	printf("Number of Calls: %d\nMaximum Register Window Depth: %d\nOverflows: %d\nUnderflows: %d\n", calls, maxDepth, overflows, underflows);

	volatile int x = 3;
	int i = 0;
	auto start = high_resolution_clock::now();
	while (i < NUM_RUNS) {
		ackermann(x, 6);
		i++;
	}
	auto end = high_resolution_clock::now();

	long double dur = duration_cast<nanoseconds>(end - start).count();

	cout << "Took " << dur << " nanoseconds\n";

	double time = dur / (double)NUM_RUNS;

	cout << "Average " << time << " nanoseconds\n";

	return 0;
}