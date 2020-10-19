from matplotlib.pyplot import xlabel
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import pandas
from sklearn.linear_model import LogisticRegression
from sklearn.svm import LinearSVC
from scipy.special import expit
from sklearn.model_selection import train_test_split

df = pd.read_csv("/Users/lukehackett/Documents/College/4th Year/Machine Learning/week2.csv" , comment='#')
X1s = df.iloc[:,0]
X2s = df.iloc[:,1]
xs = np.column_stack((X1s,X2s))
ys = df.iloc[:,2]

# A
# i)

plt.scatter(X1s, X2s, s = 6, c = ys)
plt.xlabel("X1s")
plt.ylabel("X2s")
plt.legend()
plt.title("Poynts")

# ii)
'''
X1Train, X1Test = train_test_split(X1s, test_size=0.5, random_state=47)
X2Train, X2Test = train_test_split(X2s, test_size=0.5, random_state=48)

xsTrain = np.column_stack((X1Train, X2Train))
'''

X_train,X_test,y_train,y_test = train_test_split(xs,ys,test_size=0.5,random_state=0)
logreg = LogisticRegression(solver='liblinear', random_state=0)
logreg.fit(X_train, y_train)
print("intercept: {}, coef: {}".format(logreg.intercept_, logreg.coef_))

predColor = ['','red','blue']

yPreds = logreg.predict(X_test)
colors = []

for i in yPreds:
  colors.append(predColor[i])

coefs = logreg.coef_[0]
a = -coefs[0] / coefs[1]
xPoints = np.linspace(-1, 1)
yPoints = a * xPoints - (logreg.intercept_[0]) / coefs[1]

#plt.plot(xPoints, yPoints, 'k-', c='gray')

#plt.scatter(*zip(*X_test), s=4, c=colors)

# B
# i)

linsvc = LinearSVC(C=0.001)
linsvc.fit(X_train, y_train)
print("intercept: {}, coef: {}".format(linsvc.intercept_, linsvc.coef_))

coefs = linsvc.coef_[0]
a = -coefs[0] / coefs[1]
xPoints = np.linspace(-1, 1)
yPoints = a * xPoints - (linsvc.intercept_[0]) / coefs[1]

#plt.plot(xPoints, yPoints, 'k-', c='red')

# C
# i)

X1sq = pandas.Series([x**2 for x in X1s])
X2sq = pandas.Series([x**2 for x in X2s])

xsq = np.column_stack((X1s, X2s, X1sq,X2sq))

logreg = LogisticRegression(solver='liblinear', random_state=0)
logreg.fit(xsq, ys)
print("intercept: {}, coef: {}".format(logreg.intercept_, logreg.coef_))

yPreds = logreg.predict(xsq)
colors = []

predColor = ['','red','blue']
for i in yPreds:
  colors.append(predColor[i])

#plt.scatter(*zip(*xs), marker='1', c=colors)
plt.show()