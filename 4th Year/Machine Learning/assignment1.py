# id:20-12258-100 

import math
from math import cos
import sys
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn import linear_model
from sklearn.metrics import r2_score

def normalise(fs):
  meanF = sum(fs) / len(fs)
  stdF = max(fs) - min(fs)

  return [(f - meanF)/stdF for f in fs]

def h(thetaZero, thetaOne, x):
  return thetaZero + (thetaOne * x)

def innerCost(x,y,thetaZero,thetaOne):
  return (h(thetaZero, thetaOne, x)-y)**2

def costFunction(xs, ys, m, thetaZero, thetaOne):
  return (1/len(ys))*sum([(innerCost(xs[i][0],ys[i][0],thetaZero,thetaOne)) for i in range(0,len(ys))])

def gradientDescent(xs, ys, lr, maxIters):
  thetaZero = 0
  thetaOne = 0
  m = len(ys)
  numIters = 0

  diff = sys.maxsize
  prevCost = costFunction(xs, ys, m, thetaZero, thetaOne)
  costHistory.append(prevCost)
  while abs(diff) > 0.001 and numIters < maxIters:
    deltaZero = ((-2*lr)/m) * sum([h(thetaZero, thetaOne, xs[i][0])-ys[i][0] for i in range(0,m)])
    deltaOne = ((-2*lr)/m) * sum([(h(thetaZero, thetaOne, xs[i][0])-ys[i][0])*xs[i][0] for i in range(0,m)])

    thetaZero += deltaZero
    thetaOne += deltaOne

    newCost = costFunction(xs, ys, m, thetaZero, thetaOne)
    costHistory.append(newCost)

    diff = newCost - prevCost
    prevCost = newCost
    numIters += 1

  return np.array([thetaZero, thetaOne])

def plotResult():
  fig,(ax1, ax2) = plt.subplots(2, figsize = (12,6))
  ax1.scatter(xs, ys, s = 8)
  ax1.plot([xs[0], xs[-1]], [h(thetaZero, thetaOne, newXs[0]), h(thetaZero, thetaOne, newXs[-1])], color='red')
  ax2.plot([i for i in range(0, len(costHistory))], costHistory)
  ax1.set_title ('Gradient descent')
  ax1.set(xlabel='Feature', ylabel='Outcome')
  ax2.set_title ('Cost over time: lr = {} max iterations = {}'.format(lr, maxIters), y=-1)
  ax2.set(xlabel='Iteration', ylabel='Cost')
  plt.show()

df = pd.read_csv("/Users/lukehackett/Documents/College/4th Year/Machine Learning/week1.csv" , comment='#')

xs = np.array(df.iloc[:,0]); xs = xs.reshape(-1,1)
ys = np.array(df.iloc[:,1]); ys = ys.reshape(-1,1)

# Normalise xs:
newXs = np.array(normalise(xs))

# Gradient Descent it:
lr = 0.1
maxIters = 1000
costHistory = []
thetaZero, thetaOne = gradientDescent(newXs, ys, lr, maxIters)
cost = costFunction(newXs, ys, len(ys), thetaZero, thetaOne)
print("theta0: {} theta1: {} cost: {}, numIters: {}".format(thetaZero, thetaOne, cost, len(costHistory)))
plotResult()

# Cost for baseline model
meanY = sum(ys) / len(ys)
cost = costFunction(newXs, ys, len(ys), meanY, 0)
print('cost for baseline model: {}'.format(cost))
print('meanY: {}'.format(meanY))

# Scikit learn version
regr = linear_model.LinearRegression()

regr.fit(newXs, ys)
yPred = regr.predict(newXs)
print('thetaZero, thetaOne:', regr.intercept_, regr.coef_)
print('Coefficient of determination: %.2f'
      % r2_score(ys, yPred))

thetaZero, thetaOne = [regr.intercept_, regr.coef_]

plt.scatter(xs, ys, s = 8)
plt.plot(xs, yPred, color='red')
plt.title("Scikit Gradient Descent")
plt.show()