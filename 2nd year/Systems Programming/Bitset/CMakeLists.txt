cmake_minimum_required(VERSION 3.12)
project(bitset C)

set(CMAKE_C_STANDARD 99)

add_executable(bitset main.c bitset.c)
target_link_libraries(bitset m)