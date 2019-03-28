cmake_minimum_required(VERSION 3.12)
project(P4-distr C)

set(CMAKE_C_STANDARD 99)

add_executable(P4-distr swtch.c proc.c main.c)