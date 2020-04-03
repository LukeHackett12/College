ProbRewardMatrices = {
  'exercise': {
    'fit': {
      'fit': [0.9*0.99, 8],
      'unfit': [0.9*0.01, 8],
      'dead': [0.1, 0]
    },
    'unfit': {
      'fit': [0.9*0.2, 0],
      'unfit': [0.9*0.8, 0],
      'dead': [0.1, 0]
    },
    'dead': {
      'fit': [0, 0],
      'unfit': [0, 0],
      'dead': [1, 0]
    }
  },
  'relax': {
    'fit': {
      'fit': [0.99*0.7, 10],
      'unfit': [0.99*0.3, 10],
      'dead': [0.01, 0]
    },
    'unfit': {
      'fit': [0, 5],
      'unfit': [0.99*1, 5],
      'dead': [0.01, 0]
    },
    'dead': {
      'fit': [0, 0],
      'unfit': [0, 0],
      'dead': [1, 0]
    }
  }
}

def q0(s,a):
  return (ProbRewardMatrices[a][s]['fit'][0]*ProbRewardMatrices[a][s]['fit'][1]
  + ProbRewardMatrices[a][s]['unfit'][0]*ProbRewardMatrices[a][s]['unfit'][1]
  + ProbRewardMatrices[a][s]['dead'][0]*ProbRewardMatrices[a][s]['dead'][1])

def Vn(g,n,s):
  return max(q(g,n,s,'exercise'), q(g,n,s,'relax'))

def q(g,n,s,a):
  q0res = q0(s,a)

  if n == 0:
    return q0res
  else:
    return (q0res + g*(
                ProbRewardMatrices[a][s]['fit'][0]*Vn(g,n-1,'fit')
              + ProbRewardMatrices[a][s]['unfit'][0]*Vn(g,n-1,'unfit')
              + ProbRewardMatrices[a][s]['dead'][0]*Vn(g,n-1,'dead')))

#usage 
#q(g,n,s,'exercise')
#q(g,n,s,'relax')

print(q(0.5,10,'fit','exercise'))
print(q(0.5,10,'fit','relax'))