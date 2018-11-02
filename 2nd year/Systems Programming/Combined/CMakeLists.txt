cmake_minimum_required(VERSION 3.12)
project(Combined C)

set(CMAKE_C_STANDARD 99)

add_executable(Combined main.c bitset.c bitset.h bloom.h bloom.c)