# (╯°□°）╯︵ ┻━┻      ┬─┬ ノ( ゜-゜ノ)
CNN: acyclic graph of "layers"
Layers are selected from list of std layers

Image has multiple channels: (rgb, or random info abt a pixel)

CNN convolution is done with different conv. kernels (ie multichannel)

- must work with existing data structures
- take into account locality of data access and multiple available processor cores in algorithm
    - locality of data == caching matrix values (can reduce computation time by a fair bit but means    reordering for loop iterations)
    - work with any amount of threads (master thread & offload parallell sections to other threads if available)
- work with different sizes:
    - image_height == image_width: 16..512
    - kernel order: 1, 3, 5, or 7
    - number of channels: 32..2048 (always powers of 2)
    - number of kernels: 32..2048 (always powers of 2)
    - nz_ratio: 20..1000

# Q: is pragma thread count optimised by default ?

# Pragma 
- vars are shared by default
- critical keyword for mutual exclusion of vars
- private(var_name) keyword for local copy
- share(var_name) for explicitly shared (is by default tho)
- reduction(+:sum) if trying to reduce set of vars into a single

- 	#pragma omp simd safelen(10) : Use SIMD: array operations in batch
### scheduling options:
- static: evenly divide work in threads 
- dynamic: work queue & threads take from queue
- guided: kinda like dynamic but more tuning

### Other
Parallel constructs are costly (0.5 -2.5 microseconds), can use conditions if work is small
like:
```
#pragma omp parallel for if ( n > 128 )
```

# SSE
Library w/ operations such as loading data from memory (aligned/unaligned), or operations such as add/sub, multiply, min max... 
Also supports comparisons

# (╯°□°）╯︵ ┻━┻      ┬─┬ ノ( ゜-゜ノ)

```
  To submit the lab, please send me an email with the code, the report, and
  your slides as attachments. Please make sure that your email has a subject
  line with the following:
  HEDGEHOD Samuel Petit Luke Hackett
```