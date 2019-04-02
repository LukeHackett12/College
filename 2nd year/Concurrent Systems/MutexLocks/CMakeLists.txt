cmake_minimum_required(VERSION 3.12.3)
project(MutexLocks C)

set(CMAKE_C_STANDARD 99)

add_executable(MutexLocks practical2.c )
target_link_libraries( MutexLocks pthread )