cmake_minimum_required(VERSION 3.12)
project(bloom C)

set(CMAKE_C_STANDARD 99)

add_executable(bloom main.c bitset.c)
target_link_libraries(bloom m)