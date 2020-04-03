import numpy as np
import random
import matplotlib.pyplot as plt

fig = 1

def fx(x):
    return (x*x)-1

def dfdx(x):
    return 2*x

def gradientDescent(x, color, alpha, iters):
  _ = plt.figure(fig)
  #fig = fig+1
  for _ in range(iters):
    currentX = x
    x = currentX - alpha * dfdx(currentX)
    plt.plot(x, fx(x), color)
  return x


def randomVicinity(x, color, alpha, iters):
  _ = plt.figure(fig)
  #fig = fig+1
  for _ in range(iters):
    randStep = random.uniform(-1*alpha, alpha)
    
    if fx(x+randStep) < fx(x):
        x = x + randStep
        plt.plot(x, fx(x), color)
  return x

x = 1
x = gradientDescent(x, 'r.', 1, 1000)
print("Minimum at " + str(x))

fig = fig+1

x = 1
x = gradientDescent(x, 'g.', 0.1, 1000)
print("Minimum at " + str(x))

fig = fig+1

x = 1
x = gradientDescent(x, 'b.', 0.01, 1000)
print("Minimum at " + str(x))

fig = fig+1

x = 1
x = randomVicinity(x, 'r.', 1, 1000)
print("R1 Minimum at " + str(x))

fig = fig+1

x = 1
x = randomVicinity(x, 'g.', 0.1, 1000)
print("R0.1 Minimum at " + str(x))

fig = fig+1

x = 1
x = randomVicinity(x, 'b.', 0.01, 1000)
print("R0.01 Minimum at " + str(x))

plt.show()
