cmake_minimum_required(VERSION 3.12)
project(Bloom_Filter C)

set(CMAKE_C_STANDARD 99)

add_executable(Bloom_Filter main.c bloom.h bloom.c)